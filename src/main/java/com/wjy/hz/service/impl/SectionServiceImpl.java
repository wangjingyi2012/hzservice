package com.wjy.hz.service.impl;

import com.wjy.hz.mapper.SectionMapper;
import com.wjy.hz.model.entity.CourseEntity;
import com.wjy.hz.model.entity.SectionEntity;
import com.wjy.hz.service.SectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {

    @Resource
    SectionMapper sectionMapper;

    @Override
    public List<SectionEntity> courseSections(long courseId) {
        return sectionMapper.courseSections(courseId);
    }

    @Override
    public SectionEntity byId(long id) {
        return sectionMapper.byId(id);
    }
}
