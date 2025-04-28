package com.wjy.hz.service.impl;

import com.wjy.hz.mapper.zd.AttendanceMapper;
import com.wjy.hz.model.dto.zd.AttendanceDto;
import com.wjy.hz.service.ZDService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ZDServiceImpl implements ZDService {

    @Resource
    private AttendanceMapper attendanceMapper;

    @Override
    public List<AttendanceDto> getAttendanceList(int page, int size) {
        int offset = (page - 1) * size;
        return attendanceMapper.getAttendanceList(offset, size);
    }
}
