package com.wjy.hz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentInputDto {

    private long sid;

    private String content;

    private long blogId;

    private String anymous;

    private String anyname;

}
