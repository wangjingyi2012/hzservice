package com.wjy.hz.service.impl;

import com.wjy.hz.mapper.*;
import com.wjy.hz.model.dto.UserExamAnsweredStemAnswerDto;
import com.wjy.hz.model.dto.UserExamAnsweredStemDto;
import com.wjy.hz.model.dto.UserExamInputDto;
import com.wjy.hz.model.dto.UserExamInputStemDto;
import com.wjy.hz.model.entity.*;
import com.wjy.hz.model.enums.StemType;
import com.wjy.hz.service.ExamService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {

    @Resource
    ExamMapper examMapper;

    @Resource
    PaperMapper paperMapper;

    @Resource
    StemMapper stemMapper;

    @Resource
    AnswerMapper answerMapper;

    @Resource
    StudentMapper studentMapper;

    @Resource
    UserExamMapper userExamMapper;

    @Resource
    UserAnswerMapper userAnswerMapper;

    @Override
    public ExamEntity findExamById(long examId) {
        ExamEntity examEntity = examMapper.byId(examId);
        if (examEntity == null) {
            return null;
        }
        return examEntity;
    }

    public PaperEntity findExamPaper(long examId) {
        ExamEntity examEntity = examMapper.byId(examId);
        if (examEntity == null) {
            return null;
        }
        return paperMapper.byId(examEntity.getPaper());
    }

    @Override
    public Map<String, LocalDateTime> examTime(long examId) {
        ExamEntity examEntity = examMapper.byId(examId);
        if (examEntity == null) {
            return null;
        }
        Map<String, LocalDateTime> times = new HashMap<>();
        times.put("start", examEntity.getStart());
        times.put("end", examEntity.getEnd());
        return times;
    }


    @Override
    @Async
    public void saveAnswersAndCheck(UserExamInputDto dto) {
        PaperEntity paperEntity = findExamPaper(dto.getExam());
        if (paperEntity == null) {
            System.out.println("Paper is not found: " + dto.getExam());
            return;
        }

        // 参加过
        if (userExamMapper.isJoined(dto.getExam(), dto.getSid()) > 0) {
            return;
        }

        List<UserExamInputStemDto> inputs = dto.getAnswers();
        AtomicInteger rightAnswerCount = new AtomicInteger(0);

        inputs.forEach(input -> {
            UserAnswerEntity ua = new UserAnswerEntity();
            ua.setSid(dto.getSid());
            ua.setStem(input.getStem());
            ua.setInput(input.getInput());
            ua.setExam(dto.getExam());
            StemEntity stem = stemMapper.byId(input.getStem());

            if (stem.getType().equals(StemType.Radio.getCode()) || stem.getType().equals(StemType.Judge.getCode())) {
                AnswerEntity ra = answerMapper.radirAndJudgeAnswer(stem.getId());
                ua.setRanswer(String.valueOf(ra.getId()));
                if (input.getInput().equals(String.valueOf(ra.getId()))) {
                    ua.setCorrect(1);
                    rightAnswerCount.incrementAndGet();
                } else {
                    ua.setCorrect(0);
                }
            } else if (stem.getType().equals(StemType.Muilt.getCode())) {
                List<AnswerEntity> ras = answerMapper.multiAnswers(stem.getId());
                Set<String> correctAnswers = ras.stream()
                        .map(ra -> String.valueOf(ra.getId()))
                        .collect(Collectors.toSet());
                Set<String> studentAnswers = Arrays.stream(input.getInput().split(","))
                        .collect(Collectors.toSet());
                if (studentAnswers.equals(correctAnswers)) {
                    ua.setCorrect(1);
                    rightAnswerCount.incrementAndGet();
                } else {
                    ua.setCorrect(0);
                }
            }
            userAnswerMapper.insert(ua);
        });

        int stemCount = paperMapper.stemsCount(paperEntity.getId());
        int score = 0;
        if (stemCount > 0) {
            score = (int) Math.round((double) rightAnswerCount.get() / stemCount * 100);
        }

        UserExamEntity ue = new UserExamEntity();
        ue.setSid(dto.getSid());
        StudentEntity stu = studentMapper.selectById(dto.getSid());
        ue.setSrealname(stu.getRealname());
        ue.setExam(dto.getExam());
        ue.setClazz(stu.getClazz());
        ue.setScore(score);
        userExamMapper.updateScore(dto.getSid(), dto.getExam(), stu.getRealname(), stu.getClazz(), score);
    }

    @Override
    public boolean isAnswered(long examId, long sid) {
        return userExamMapper.isJoined(examId, sid) > 0;
    }

    @Override
    public List<UserExamAnsweredStemDto> userAnswers(long examId, long studentId) {
        List<UserExamAnsweredStemDto> retUses = new ArrayList<>();
        List<UserAnswerEntity> uses = userAnswerMapper.userAnswers(examId, studentId);
        uses.forEach(us -> {
            StemEntity stemEntity = stemMapper.byId(us.getStem());
            UserExamAnsweredStemDto usas = new UserExamAnsweredStemDto();
            usas.setId(us.getId());
            usas.setStemId(us.getStem());
            usas.setStemContent(stemEntity.getContent());
            usas.setType(stemEntity.getType());
            usas.setUserCorect(us.getCorrect());

            List<AnswerEntity> answers = answerMapper.stemAnswers(stemEntity.getId());
            List<UserExamAnsweredStemAnswerDto> uis = new ArrayList<>();
            answers.forEach(answer -> {
                UserExamAnsweredStemAnswerDto ui = new UserExamAnsweredStemAnswerDto();
                ui.setId(answer.getId());
                ui.setStemId(answer.getId());
                ui.setAnswerContent(answer.getContent());
                ui.setCorrect(answer.getCorrect());
                ui.setUserCheck(us.getInput().equals(String.valueOf(answer.getId())) ? 1 : 0);
                uis.add(ui);
            });
            usas.setAnswers(uis);
            retUses.add(usas);
        });
        return retUses;
    }


}
