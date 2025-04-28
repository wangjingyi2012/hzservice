package com.wjy.hz.service;

import com.wjy.hz.model.dto.zd.AttendanceDto;
import com.wjy.hz.model.entity.zd.ZDHeroEntity;
import com.wjy.hz.model.entity.zd.ZDPersonEntity;

import java.util.List;

public interface ZDService {

    List<AttendanceDto> getAttendanceList(int page, int size);

    List<ZDPersonEntity> getAllPerson();

    List<ZDHeroEntity> getAllHeros();

    int addAttendance(int person, String result, String kda, int hero, String type);

}
