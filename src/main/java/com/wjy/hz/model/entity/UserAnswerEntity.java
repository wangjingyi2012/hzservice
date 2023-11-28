package com.wjy.hz.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswerEntity {

    private long id;

    private long sid;

    private long stem;

    private int correct;
    
    private String input;

    private String ranswer;

    private long paper;

    private long exam;

}
