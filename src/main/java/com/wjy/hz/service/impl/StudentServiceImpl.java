package com.wjy.hz.service.impl;

import com.wjy.hz.mapper.*;
import com.wjy.hz.model.dto.UserExamDto;
import com.wjy.hz.model.entity.ExamEntity;
import com.wjy.hz.model.entity.UserExamEntity;
import com.wjy.hz.model.enums.LogActions;
import com.wjy.hz.model.dto.StudentDto;
import com.wjy.hz.model.entity.SettingEntity;
import com.wjy.hz.model.entity.TimelineEntity;
import com.wjy.hz.service.FileStorageService;
import com.wjy.hz.service.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    StudentMapper studentMapper;

    @Resource
    GradeMapper gradeMapper;

    @Resource
    ClazzMapper clazzMapper;

    @Resource
    TimeLineMapper timeLineMapper;

    @Resource
    SettingMapper settingMapper;

    @Resource
    UserExamMapper userExamMapper;

    @Resource
    ExamMapper examMapper;

    @Resource
    FileStorageService fileStorageService;

    @Value("${file.upload-dir}")
    private String uploadDir;


    @Override
    public StudentDto login(String number, String password) {
        return Optional.ofNullable(studentMapper.login(number, password))
                .map(studentEntity -> {
                    StudentDto studentDto = new StudentDto();
                    studentDto.setId(studentEntity.getId());
                    studentDto.setUsername(studentEntity.getUsername());
                    studentDto.setRealname(studentEntity.getRealname());
                    studentDto.setNumber(studentEntity.getNumber());
                    studentDto.setAvator(studentEntity.getAvator());
                    studentDto.setMotto(studentEntity.getMotto());
                    studentDto.setGender(studentEntity.getGender());
                    studentDto.setProfile(studentEntity.getProfile());
                    studentDto.setLevel(studentEntity.getLevel());
                    studentDto.setPoints(studentEntity.getPoints());

                    Optional.ofNullable(gradeMapper.selectById(studentEntity.getGrade()))
                            .ifPresent(gradeEntity -> studentDto.setGradeName(gradeEntity.getName()));

                    Optional.ofNullable(clazzMapper.selectById(studentEntity.getClazz()))
                            .ifPresent(clazzEntity -> studentDto.setClazzName(clazzEntity.getName()));

                    return studentDto;
                })
                .orElse(null);
    }

    @Override
    public StudentDto infoById(long id) {
        return Optional.ofNullable(studentMapper.selectById(id))
//            StudentEntity studentEntity = studentMapper.selectById(id);
                .map(studentEntity -> {
                    StudentDto studentDto = new StudentDto();
                    studentDto.setId(studentEntity.getId());
                    studentDto.setUsername(studentEntity.getUsername());
                    studentDto.setRealname(studentEntity.getRealname());
                    studentDto.setNumber(studentEntity.getNumber());
                    studentDto.setAvator(studentEntity.getAvator());
                    studentDto.setMotto(studentEntity.getMotto());
                    studentDto.setGender(studentEntity.getGender());
                    studentDto.setProfile(studentEntity.getProfile());
                    studentDto.setLevel(studentEntity.getLevel());
                    studentDto.setPoints(studentEntity.getPoints());

                    Optional.ofNullable(gradeMapper.selectById(studentEntity.getGrade()))
                            .ifPresent(gradeEntity -> studentDto.setGradeName(gradeEntity.getName()));

                    Optional.ofNullable(clazzMapper.selectById(studentEntity.getClazz()))
                            .ifPresent(clazzEntity -> studentDto.setClazzName(clazzEntity.getName()));

                    return studentDto;
                })
                .orElse(null);
    }

    @Override
    public boolean saveUsername(long id, String username) {
        return studentMapper.saveUsername(username, id) > 0;
    }

    @Override
    public List<TimelineEntity> timeLine(long studentId) {
        return timeLineMapper.timelineBySid(studentId);
    }

    @Override
    public int sign(long sid) {
        SettingEntity settingEntity = settingMapper.byKey("COULD_SIGN");
        if (settingEntity == null || settingEntity.getValue().equals("NO")) {
            return 0;
        }

        // 获取当前日期的开始和结束时间
        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);

        // 检查是否已有当天的签到记录
        Integer count = timeLineMapper.countTodaySign(sid, startOfDay, endOfDay);
        if (count == null || count == 0) {
            // 如果当天没有签到记录，则插入新的签到记录
            return timeLineMapper.addAction(sid, "学习", "签到", LocalDateTime.now(), LogActions.Sign.getCode());
        } else {
            // 如果已经有签到记录，则不执行插入操作
            return 0;
        }
    }


    @Override
    public List<UserExamDto> userExams(long studentId) {
        List<UserExamEntity> ues = userExamMapper.studentExamIds(studentId);
        List<UserExamDto> userExamDtoList = new ArrayList<>();
        ues.forEach(ue -> {
            UserExamDto dto = new UserExamDto();
            ExamEntity examEntity = examMapper.byId(ue.getExam());
            dto.setId(ue.getExam());
            dto.setName(examEntity.getName());
            dto.setProfile(examEntity.getProfile());
            dto.setStart(examEntity.getStart());
            dto.setEnd(examEntity.getEnd());
            dto.setPaper(examEntity.getPaper());
            dto.setCourse(examEntity.getCourse());
            dto.setScore(ue.getScore());
            LocalDateTime now = LocalDateTime.now();
            if (now.isAfter(examEntity.getStart()) && now.isBefore(examEntity.getEnd())) {
                dto.setStatus("未开始");
            } else if (now.isBefore(examEntity.getStart())) {
                dto.setStatus("进行中");
            } else if (now.isAfter(examEntity.getEnd())) {
                dto.setStatus("已结束");
            }
            userExamDtoList.add(dto);
        });
        return userExamDtoList;
    }


    @Override
    public int uploadAvator(long sid, MultipartFile file) {
        String filename = fileStorageService.storeFile(file);
        String filepath = "http://www.hz-study-system.com/wjy/images/" + filename;
        return studentMapper.updateAvator(sid, filepath);
    }


}
