package com.wjy.hz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewHomeworkDto {

    private Integer sid;

    private Integer hid;

    private Integer score;

    private String tip;
}
