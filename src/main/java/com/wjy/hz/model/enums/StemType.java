package com.wjy.hz.model.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StemType {

    Judge("judge"),
    Radio("radio"),
    Muilt("multi");

    private final String code;

}
