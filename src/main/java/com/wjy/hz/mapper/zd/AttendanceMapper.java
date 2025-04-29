package com.wjy.hz.mapper.zd;

import com.wjy.hz.model.dto.zd.AttendanceDto;
import com.wjy.hz.model.entity.zd.ZDHeroEntity;
import com.wjy.hz.model.entity.zd.ZDPersonEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface AttendanceMapper {

    @Select("SELECT r.id, p.name, p.avatar, r.type, r.attendance, h.hname, h.havatar, r.result, r.kda " +
            "FROM zd_person_record r " +
            "JOIN zd_person p ON r.person = p.id " +
            "LEFT JOIN zd_heros h ON r.hero = h.id " +
            "WHERE r.person = #{personId} " +  // personId 为 null 时，MyBatis 会忽略此条件（查询所有）
            "LIMIT #{offset}, #{size}")
    List<AttendanceDto> getAttendanceList(
            @Param("personId") Integer personId,
            @Param("offset") int offset,
            @Param("size") int size
    );

    @Select("select * from zd_person")
    List<ZDPersonEntity> getAllPerson();

    @Insert("INSERT INTO zd_person_record (attendance, person, result, kda, hero, type) " +
            "VALUES (#{attendance}, #{person}, #{result}, #{kda}, #{hero}, #{type})")
    int addAttendance(@Param("attendance") LocalDateTime attendance,
                      @Param("person") int person,
                      @Param("result") String result,
                      @Param("kda") String kda,
                      @Param("hero") int hero,
                      @Param("type") String type);


    @Select("SELECT * FROM zd_heros")
    List<ZDHeroEntity> getAllHeros();

}