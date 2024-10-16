package com.wjy.hz.controller;

import com.wjy.hz.model.api.ApiResponse;
import com.wjy.hz.model.dto.ExamAnswerDto;
import com.wjy.hz.model.dto.StudentDto;
import com.wjy.hz.model.dto.UserExamInputDto;
import com.wjy.hz.model.entity.PaperEntity;
import com.wjy.hz.service.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class StudentController {

    @Resource
    StudentService studentService;

    @Resource
    CourseService courseService;

    @Resource
    SectionService sectionService;

    @Resource
    ExamService examService;

    @Resource
    StemService stemService;

    @GetMapping("/api/student/all-students")
    public String allStudents() {
        return ApiResponse.ok(studentService.allStudents());
    }

    @GetMapping("/api/student/user-info")
    public String userInfo(@RequestParam("sid") long sid) {
        StudentDto studentDto = studentService.infoById(sid);
        return studentDto != null ? ApiResponse.ok(studentDto) : ApiResponse.resNotFound();
    }

    @PostMapping("/api/student/save-username")
    public String saveUsername(@RequestParam("username") String username, @RequestParam("id") long id) {
        boolean result = studentService.saveUsername(id, username);
        return result ? ApiResponse.ok("okk") : ApiResponse.intError();
    }

    @GetMapping("/api/student/time-line")
    public String timeLine(@RequestParam("sid") long sid) {
        return ApiResponse.ok(studentService.timeLine(sid));
    }

    @PostMapping("/api/student/sign")
    public String sign(@RequestParam("sid") long sid) {
        return studentService.sign(sid) > 0 ? ApiResponse.ok(1) : ApiResponse.intForbidden();
    }

    @GetMapping("/api/student/courses")
    public String allCourses() {
        return ApiResponse.ok(courseService.allCourses());
    }

    @GetMapping("/api/student/sections")
    public String courseSections(@RequestParam("courseId") long courseId) {
        return ApiResponse.ok(sectionService.courseSections(courseId));
    }

    @GetMapping("/api/student/section-content")
    public String courseLearn(@RequestParam("sectionId") long sectionId) {
        return ApiResponse.ok(sectionService.byId(sectionId));
    }

    @GetMapping("/api/student/user-exams")
    public String examList(@RequestParam("sid") long sid) {
        return ApiResponse.ok(studentService.userExams(sid));
    }

    @GetMapping("/api/student/exam-paper-exams")
    public String stemLists(@RequestParam("examId") long examId) {
        PaperEntity paperEntity = examService.findExamPaper(examId);
        if (paperEntity == null) {
            return ApiResponse.resNotFound();
        }
        return ApiResponse.ok(stemService.paperStems(paperEntity.getId()));
    }

    @GetMapping("/api/student/exam-paper-exams-time")
    public String getExamTimes(@RequestParam("examId") long examId) {
        Map<String, LocalDateTime> times = examService.examTime(examId);
        return times != null ? ApiResponse.ok(times) : ApiResponse.resNotFound();
    }

    @GetMapping("/api/student/exam-info")
    public String getExam(@RequestParam("examId") long examId) {
        return ApiResponse.ok(examService.findExamById(examId));
    }

    @PostMapping("/api/student/submit-exam-answers")
    public String submitExamAnswers(@RequestBody UserExamInputDto answerDto) {
        examService.saveAnswersAndCheck(answerDto);
        return ApiResponse.ok("交卷成功");
    }

    @GetMapping("/api/student/is-joined-exam")
    public String isJoinedExam(@RequestParam("examId") long examId,@RequestParam("sid") long sid) {
        return examService.isAnswered(examId, sid) ? ApiResponse.ok(1) : ApiResponse.intForbidden();
    }

    @GetMapping("/api/student/exam-user-answered")
    public String userAnswered(@RequestParam("examId") long examId, @RequestParam("sid") long sid) {
        return ApiResponse.ok(examService.userAnswers(examId, sid));
    }





    

}
