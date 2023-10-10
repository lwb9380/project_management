package com.pro.project.dao;

import com.pro.project.dto.Day;
import com.pro.project.dto.ScheduleRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScheduleDao {

    public void insertSchedule(int empno, int 월요일, int 화요일, int 수요일, int 목요일, int 금요일, int year, int month);

    public Day selectDaycheck(int empno);

    public void updateSchedule(int 월요일, int 화요일, int 수요일, int 목요일, int 금요일, int empno);

    public void insertScheduleRequest(int empno, int 월요일, int 화요일, int 수요일, int 목요일, int 금요일, int yaer, int month);

    public ScheduleRequest selectRequest(int empno);

    public void deleteSchedule(int empno);

    public void deleteScheduleRequest(int empno);
}
