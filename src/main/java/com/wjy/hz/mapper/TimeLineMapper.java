package com.wjy.hz.mapper;

import com.wjy.hz.model.entity.TimelineEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface TimeLineMapper {


    @Select("select * from timelines where sid = #{sid}")
    List<TimelineEntity> timelineBySid(@Param("sid") long studentId);

    @Insert("INSERT INTO timelines (title, content, happend, sid, action) VALUES (#{title}, #{content}, #{happend}, #{sid}, #{action})")
    int addAction(@Param("sid") long sid, @Param("title") String title, @Param("content") String content, @Param("happend") LocalDateTime happend, @Param("action") String action);

    @Select("SELECT COUNT(*) FROM timelines WHERE sid = #{sid} AND happend >= #{startOfDay} AND happend <= #{endOfDay} AND action = '签到'")
    Integer countTodaySign(@Param("sid") long sid, @Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);


}
