package com.wjy.hz.service;

import com.wjy.hz.model.entity.CourseEntity;
import com.wjy.hz.model.entity.SectionEntity;

import java.util.List;

public interface SectionService {

    List<SectionEntity> courseSections(long courseId);

    SectionEntity byId(long id);

}
