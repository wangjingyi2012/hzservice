package com.wjy.hz.service.impl;

import com.wjy.hz.mapper.CourseMapper;
import com.wjy.hz.model.entity.CourseEntity;
import com.wjy.hz.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Resource
    CourseMapper courseMapper;

    @Override
    public List<CourseEntity> allCourses() {
        return courseMapper.courses();
    }
}
