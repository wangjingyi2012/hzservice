package com.wjy.hz.mapper;

import com.wjy.hz.model.entity.SettingEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SettingMapper {

    @Insert("insert into setting (`key`, value) VALUES (#{key}, #{value})")
    int addSetting(@Param("key") String key, @Param("value") String value);

    @Update("update setting set `value` = #{value} where `key` = #{key}")
    int set(@Param("key") String key, @Param("value") String value);

    @Select("select * from setting where `key` = #{key}")
    SettingEntity byKey(@Param("key") String key);

}
