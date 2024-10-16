package com.wjy.hz.mapper;

import com.wjy.hz.model.entity.HomeworkEntity;
import com.wjy.hz.model.entity.UserHomeworkEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HomeworkMapper {

    @Select("select * from homeworks")
    List<HomeworkEntity> allHomeworks();

    @Select("select * from user_homeworks where sid = #{sid} and hid = #{hid} limit 1")
    UserHomeworkEntity getBySid(@Param("hid") Integer hid, @Param("sid") Integer sid);

    @Select("select * from homeworks where id = #{id}")
    HomeworkEntity selectById(Long id);

    // 新增插入作业提交记录的方法
    @Insert("INSERT INTO user_homeworks (sid, hid, file, stime, comment, status) " +
            "VALUES (#{sid}, #{hid}, #{file}, #{stime}, #{comment}, #{status})")
    int insertUserHomework(UserHomeworkEntity userHomework);

    // 新增更新作业提交记录的方法
    @Update("UPDATE user_homeworks SET file = #{file}, stime = #{stime}, comment = #{comment}, status = #{status} " +
            "WHERE sid = #{sid} AND hid = #{hid}")
    int updateUserHomework(UserHomeworkEntity userHomework);
}
