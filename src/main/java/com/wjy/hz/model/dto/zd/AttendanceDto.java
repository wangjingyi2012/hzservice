package com.wjy.hz.model.dto.zd;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttendanceDto {

    private int id;

    private String name; // user's name

    private String avatar;// users' avatar

    private String type;

    private LocalDateTime attendance;

    private String hname; // hero's name

    private String havatar; // hero's avatar

    private String result; // match's result

    private String kda; // user's kda

}
