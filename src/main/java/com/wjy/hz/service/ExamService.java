package com.wjy.hz.service;

import com.wjy.hz.model.dto.UserExamAnsweredStemDto;
import com.wjy.hz.model.dto.UserExamInputDto;
import com.wjy.hz.model.entity.ExamEntity;
import com.wjy.hz.model.entity.PaperEntity;
import com.wjy.hz.model.entity.UserAnswerEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ExamService {

    ExamEntity findExamById(long examId);

    PaperEntity findExamPaper(long examId);

    Map<String, LocalDateTime> examTime(long examId);

    void saveAnswersAndCheck(UserExamInputDto dto);

    boolean isAnswered(long examId, long sid);

    List<UserExamAnsweredStemDto> userAnswers(long examId, long studentId);

}
