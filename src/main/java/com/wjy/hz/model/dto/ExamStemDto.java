package com.wjy.hz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamStemDto {

    private long id;

    private String content;

    private String type;

    // 是否算分数
    private int op;

    List<ExamAnswerDto> answers;

}
