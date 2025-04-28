package com.wjy.hz.mapper.zd;

import com.wjy.hz.model.dto.zd.AttendanceDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AttendanceMapper {
    // AttendanceMapper.java
    @Select("SELECT r.id, p.name, r.type, r.attendance " +
            "FROM zd_person_record r " +
            "JOIN zd_person p ON r.person = p.id " +
            "LIMIT #{offset}, #{size}")
    List<AttendanceDto> getAttendanceList(int offset, int size);
}