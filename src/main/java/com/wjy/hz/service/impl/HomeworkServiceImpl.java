package com.wjy.hz.service.impl;

import com.wjy.hz.mapper.*;
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

    @Resource
    StudentHomeworkMapper studentHomeworkMapper;

    @Override
    public List<HomeworkEntity> allHomeworks() {
        return homeworkMapper.allHomeworks();
    }

    @Override
    public StudentHomeworkDto getHomeworkOfUser(Integer sid, Integer hid) {
        // 查询学生信息
        StudentEntity student = studentMapper.selectById(sid);
        // 查询作业信息
        HomeworkEntity homework = homeworkMapper.selectById(hid.longValue());
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
    public Boolean submitHomework(String fileUrl, Integer sid, Integer hid, String comment) {
        // 查询作业信息
        HomeworkEntity homework = homeworkMapper.selectById(hid.longValue());
        if (homework == null) {
            System.out.println("作业不存在");
            return false;
        }

        // 查询学生信息
        StudentEntity student = studentMapper.selectById(sid);
        if (student == null) {
            System.out.println("学生不存在");
            return false;
        }

        // 查询是否已经有提交记录
        UserHomeworkEntity existingSubmission = homeworkMapper.getBySid(hid, sid);

        // 如果没有记录，插入新的提交记录
        if (existingSubmission == null) {
            UserHomeworkEntity newSubmission = new UserHomeworkEntity();
            newSubmission.setSid(sid); // 设置学生ID
            newSubmission.setHid(hid); // 设置作业ID
            newSubmission.setFile(fileUrl); // 设置文件路径
            newSubmission.setStime(LocalDateTime.now()); // 设置提交时间
            newSubmission.setComment(comment); // 附言为空或前端传递的附言
            newSubmission.setStatus("已提交"); // 提交状态

            // 插入新提交记录
            homeworkMapper.insertUserHomework(newSubmission);
        } else {
            // 如果有记录，更新提交记录
            existingSubmission.setFile(fileUrl); // 更新文件路径
            existingSubmission.setStime(LocalDateTime.now()); // 更新提交时间
            existingSubmission.setComment(comment); // 更新附言，假设前端会传递附言
            existingSubmission.setStatus("已提交"); // 更新状态为已提交

            // 更新现有提交记录
            homeworkMapper.updateUserHomework(existingSubmission);
        }

        return true;
    }

    @Override
    public List<ReviewProgressPublicDto> reviewPublicProgress() {
        List<UserHomeworkEntity> allHomeworks = studentHomeworkMapper.all();
        List<ReviewProgressPublicDto> retLists = new ArrayList<>();
        allHomeworks.forEach(sh -> {
            StudentEntity student = studentMapper.selectById(sh.getSid());
            HomeworkEntity homework = homeworkMapper.selectById(sh.getHid());

            if (student != null && homework != null) {
                ReviewProgressPublicDto dto = new ReviewProgressPublicDto();
                dto.setUsername(student.getUsername());
                dto.setHomeworkName(homework.getName());
                dto.setScore(sh.getScore());
                dto.setScoretime(sh.getScoretime());
                if (sh.getScore() != null && sh.getScoretime() != null) {
                    dto.setStatus("已阅");
                } else {
                    dto.setStatus("排队中");
                }
                retLists.add(dto);
            }
        });
        return retLists;
    }


}
