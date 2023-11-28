package com.wjy.hz.service;

import com.wjy.hz.model.dto.ExamStemDto;

import java.util.List;

public interface StemService {

    List<ExamStemDto> paperStems(long paperId);

}
