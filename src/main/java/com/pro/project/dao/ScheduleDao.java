package com.pro.project.dao;

import com.pro.project.dto.Day;
import com.pro.project.dto.ScheduleRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleDao {

    public void insertSchedule(int empno, int monday, int tuesday, int wednesday, int thursday, int friday, int year, int month);
    public Day selectDaycheck(int empno);
    public void updateSchedule(int monday, int tuesday, int wednesday, int thursday, int friday, int empno);
    public void insertScheduleRequest(int empno, int monday, int tuesday, int wednesday, int thursday, int friday, int year, int month);
    public ScheduleRequest selectRequest(int empno);
    public void deleteSchedule(int empno);
    public void deleteScheduleRequest(int empno);

    public ScheduleRequest checkDuplicate(int empno, int year, int month);

    public List<ScheduleRequest> scheduleRequestList(int empno);
}
