package com.pro.project.dao;

import com.pro.project.dto.Dept;
import com.pro.project.dto.MailDto;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface MailDao {

    public List<MailDto> mailList(int empno);

    public List<MailDto> selectMailDetail(Long id);

    public void mailDelete(Long id);

    public void sendMail(String content, LocalDateTime date, int empno, String sName, String title);

    public List<Dept> selectDeptList();

    public List<Dept> selectEmpnoList(String deptname);


}
