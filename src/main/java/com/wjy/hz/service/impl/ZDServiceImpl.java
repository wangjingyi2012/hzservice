package com.wjy.hz.service.impl;

import com.wjy.hz.mapper.zd.AttendanceMapper;
import com.wjy.hz.model.dto.zd.AttendanceDto;
import com.wjy.hz.model.entity.zd.ZDHeroEntity;
import com.wjy.hz.model.entity.zd.ZDPersonEntity;
import com.wjy.hz.service.ZDService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;


@Service
public class ZDServiceImpl implements ZDService {

    @Resource
    private AttendanceMapper attendanceMapper;

    @Override
    public List<AttendanceDto> getAttendanceList(int page, int size, Integer personId) {
        int offset = (page - 1) * size;
        // 参数顺序：offset, size, personId（与 XML 映射文件一致）
        size = Math.max(1, size);
        List<AttendanceDto> attendanceList = attendanceMapper.getAttendanceList(offset, size, personId);

        // 遍历列表，将 attendance 转换为 XXXX 年第 X 周
        for (AttendanceDto dto : attendanceList) {
            if (dto.getAttendance() != null) {
                LocalDate localDate = dto.getAttendance().toLocalDate();
                WeekFields weekFields = WeekFields.ISO;
                int weekNumber = localDate.get(weekFields.weekOfWeekBasedYear());
                int year = Year.from(localDate).getValue();
                String weekInfo = year + "年第" + weekNumber + "周";
                dto.setAttendanceInfo(weekInfo);
            }
        }

        return attendanceList;
    }

    @Override
    public List<ZDPersonEntity> getAllPerson() {
        return attendanceMapper.getAllPerson();
    }

    @Override
    public List<ZDHeroEntity> getAllHeros() {
        return attendanceMapper.getAllHeros();
    }

    @Override
    public int addAttendance(int person, String result, String kda, int hero, String type) {
        // 获取当前时间
        LocalDateTime attendance = LocalDateTime.now();
        return attendanceMapper.addAttendance(attendance, person, result, kda, hero, type);
    }
}