package com.wjy.hz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogInputDto {

    private long sid;

    private String title;

    private String content;

    private String anymous;

}
