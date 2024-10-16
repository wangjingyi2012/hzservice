package com.wjy.hz.service.impl;

import com.wjy.hz.mapper.BlogMapper;
import com.wjy.hz.mapper.CommentMapper;
import com.wjy.hz.mapper.HomeworkMapper;
import com.wjy.hz.mapper.StudentMapper;
import com.wjy.hz.model.dto.*;
import com.wjy.hz.model.entity.*;
import com.wjy.hz.service.BlogService;
import com.wjy.hz.service.HomeworkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    @Resource
    HomeworkMapper homeworkMapper;

    @Resource
    StudentMapper studentMapper;

    @Override
    public List<HomeworkEntity> allHomeworks() {
        return homeworkMapper.allHomeworks();
    }

    @Override
    public StudentHomeworkDto getHomeworkOfUser(Integer sid, Integer hid) {
        // 查询学生信息
        StudentEntity student = studentMapper.selectById(sid);
        // 查询作业信息
        HomeworkEntity homework = homeworkMapper.selectById(hid);
        // 查询学生的作业提交信息
        UserHomeworkEntity userHomework = homeworkMapper.getBySid(hid, sid);

        if (student == null || homework == null) {
            System.out.println("未登录或作业不存在");
            return null;
        }

        // 创建 DTO 对象并填充基本信息
        StudentHomeworkDto dto = new StudentHomeworkDto();
        dto.setSid(student.getId());
        dto.setHid(homework.getId());
        dto.setHomeworkName(homework.getName());
        dto.setHomeworkContent(homework.getContent());
        dto.setHomeworkEndtime(homework.getEndtime());
        dto.setHomeworkScore(homework.getScore());

        // 判断作业是否还可提交
        LocalDateTime now = LocalDateTime.now();
        if (homework.getEndtime() != null && homework.getEndtime().isAfter(now)) {
            dto.setHomeworkStatus("可提交");
        } else {
            dto.setHomeworkStatus("提交已截止");
        }

        // 判断学生是否提交了作业
        if (userHomework == null) {
            // 未提交作业
            dto.setSubmitStatus("未提交");
            dto.setMyComment(null);
            dto.setTeacherTip(null);
            dto.setSubmitTime(null);
            dto.setStudentScore(null);
        } else {
            // 已提交作业
            // 检查老师是否批阅
            if (userHomework.getScoretime() != null) {
                dto.setSubmitStatus("已批阅");
            } else {
                dto.setSubmitStatus("已提交");
            }
            dto.setMyComment(userHomework.getComment());
            dto.setTeacherTip(userHomework.getTip());
            dto.setSubmitTime(userHomework.getStime());
            dto.setStudentScore(userHomework.getScore());
        }

        return dto;
    }

    @Override
    public Boolean submitHomework(String fileUrl, Integer sid, Integer hid) {
        return null;
    }


}
