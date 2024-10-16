package com.wjy.hz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewProgressPublicDto {

    private String username;

    // 已阅、排除中
    private String status;

    private Integer score;// 评分

    private String homeworkName;

    private LocalDateTime scoretime; // 评分时间



}
