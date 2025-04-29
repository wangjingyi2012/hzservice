package com.wjy.hz.service.impl;

import com.wjy.hz.mapper.zd.AttendanceMapper;
import com.wjy.hz.model.dto.zd.AttendanceDto;
import com.wjy.hz.model.entity.zd.ZDHeroEntity;
import com.wjy.hz.model.entity.zd.ZDPersonEntity;
import com.wjy.hz.service.ZDService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ZDServiceImpl implements ZDService {

    @Resource
    private AttendanceMapper attendanceMapper;

    @Override
    public List<AttendanceDto> getAttendanceList(int page, int size, Integer persionId) {
        int offset = (page - 1) * size;
        return attendanceMapper.getAttendanceList(offset, size, persionId);
    }

    @Override
    public List<ZDPersonEntity> getAllPerson() {
        return attendanceMapper.getAllPerson();
    }

    @Override
    public List<ZDHeroEntity> getAllHeros() {
        return attendanceMapper.getAllHeros();
    }

    @Override
    public int addAttendance(int person, String result, String kda, int hero, String type) {
        // 获取当前时间
        LocalDateTime attendance = LocalDateTime.now();
        return attendanceMapper.addAttendance(attendance, person, result, kda, hero, type);
    }
}