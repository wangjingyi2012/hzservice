package com.wjy.hz.service;

import com.wjy.hz.model.dto.zd.AttendanceDto;

import java.util.List;

public interface ZDService {

    List<AttendanceDto> getAttendanceList(int page, int size);

}
