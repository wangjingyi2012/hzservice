package com.wjy.hz.service.impl;

import com.wjy.hz.mapper.AnswerMapper;
import com.wjy.hz.mapper.PaperMapper;
import com.wjy.hz.mapper.PaperStemMapper;
import com.wjy.hz.mapper.StemMapper;
import com.wjy.hz.model.dto.ExamAnswerDto;
import com.wjy.hz.model.dto.ExamStemDto;
import com.wjy.hz.model.entity.AnswerEntity;
import com.wjy.hz.model.entity.PaperStemEntity;
import com.wjy.hz.model.entity.StemEntity;
import com.wjy.hz.service.StemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StemServiceImpl implements StemService {

    @Resource
    PaperStemMapper paperStemMapper;

    @Resource
    StemMapper stemMapper;

    @Resource
    AnswerMapper answerMapper;

    @Override
    public List<ExamStemDto> paperStems(long paperId) {
        List<PaperStemEntity> pses = paperStemMapper.paperStems(paperId);
        List<ExamStemDto> examStemList = new ArrayList<>();

        pses.forEach(pe -> {
            StemEntity stemEntity = stemMapper.byId(pe.getStem());
            List<ExamAnswerDto> examAnswers = new ArrayList<>();
            List<AnswerEntity> answerEntities = answerMapper.stemAnswers(pe.getStem());
            answerEntities.forEach(answerEntity -> {
                ExamAnswerDto ead = new ExamAnswerDto();
                ead.setId(answerEntity.getId());
                ead.setContent(answerEntity.getContent());
                ead.setStem(answerEntity.getStem());
                examAnswers.add(ead);
            });
            ExamStemDto es = new ExamStemDto();
            es.setId(stemEntity.getId());
            es.setType(stemEntity.getType());
            es.setContent(stemEntity.getContent());
            es.setOp(stemEntity.getOp());
            es.setAnswers(examAnswers);
            examStemList.add(es);
        });

        return examStemList;
    }
}
