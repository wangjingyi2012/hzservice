package com.wjy.hz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExamInputDto {

    private long sid;

    private long exam;

    private List<UserExamInputStemDto> answers;

}
