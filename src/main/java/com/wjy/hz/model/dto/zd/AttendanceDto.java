package com.wjy.hz.model.dto.zd;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttendanceDto {

    private int id;

    private String name;

    private String type;

    private LocalDateTime attendance;

}
