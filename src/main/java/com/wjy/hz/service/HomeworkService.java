package com.wjy.hz.service;

import com.wjy.hz.model.dto.*;
import com.wjy.hz.model.entity.HomeworkEntity;
import com.wjy.hz.model.entity.UserHomeworkEntity;

import java.util.List;

public interface HomeworkService {

    List<HomeworkEntity> allHomeworks();

    StudentHomeworkDto getHomeworkOfUser(Integer sid, Integer hid);

    Boolean submitHomework(String fileUrl, Integer sid, Integer hid);

}
