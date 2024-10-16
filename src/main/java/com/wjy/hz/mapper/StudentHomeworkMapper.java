package com.wjy.hz.mapper;

import com.wjy.hz.model.dto.StudentHomeworkDto;
import com.wjy.hz.model.entity.HomeworkEntity;
import com.wjy.hz.model.entity.UserHomeworkEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentHomeworkMapper {

    @Select("select * from user_homeworks")
    List<UserHomeworkEntity> all();
}
