package com.wjy.hz.mapper.zd;

import com.wjy.hz.model.dto.zd.AttendanceDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AttendanceMapper {

    @Select("SELECT r.id, p.name, p.avatar, r.type, r.attendance, h.hname, h.havatar, r.result, r.kda " +
            "FROM zd_person_record r " +
            "JOIN zd_person p ON r.person = p.id " +
            "LEFT JOIN zd_heros h ON r.hero = h.id " +
            "LIMIT #{offset}, #{size}")
    List<AttendanceDto> getAttendanceList(int offset, int size);
}