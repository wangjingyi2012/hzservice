package com.wjy.hz.model.entity.zd;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ZDPersonRecordEntity {

    private Integer id;

    private LocalDateTime attendance;

    private Integer person;

}
