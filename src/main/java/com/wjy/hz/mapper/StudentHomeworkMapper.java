package com.wjy.hz.mapper;

import com.wjy.hz.model.dto.StudentHomeworkDto;
import com.wjy.hz.model.entity.HomeworkEntity;
import com.wjy.hz.model.entity.UserHomeworkEntity;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface StudentHomeworkMapper {

    @Select("select * from user_homeworks")
    List<UserHomeworkEntity> all();


    @Update("UPDATE user_homeworks " +
            "SET score = #{score}, tip = #{tip}, status = #{status}, scoretime = #{scoretime} " +
            "WHERE hid = #{hid} AND sid = #{sid}")
    boolean reviewHomework(@Param("hid") Integer hid,
                           @Param("sid") Integer sid,
                           @Param("score") Integer score,
                           @Param("tip") String comment,
                           @Param("status") String status,
                           @Param("scoretime") LocalDateTime scoretime);
}
