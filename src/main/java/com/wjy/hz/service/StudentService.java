package com.wjy.hz.service;

import com.wjy.hz.model.dto.StudentDto;
import com.wjy.hz.model.dto.UserExamDto;
import com.wjy.hz.model.entity.ExamEntity;
import com.wjy.hz.model.entity.TimelineEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {

    StudentDto login(String number, String password);

    StudentDto infoById(long id);

    boolean saveUsername(long id, String username);

    List<TimelineEntity> timeLine(long studentId);

    int sign(long sid);

    List<UserExamDto> userExams(long studentId);

    int uploadAvator(long sid, MultipartFile file);

    List<StudentDto> allStudents();

    List<StudentDto> allReviewStudents(Integer hid);

}
