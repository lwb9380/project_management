package com.pro.project.service;

import com.pro.project.dao.MailDao;
import com.pro.project.dto.Dept;
import com.pro.project.dto.MailDto;
import com.pro.project.entity.Mail;
import com.pro.project.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MailService {

    private final MailDao mailDao;

    private final MailRepository mailRepository;

    @Autowired
    public MailService(MailDao mailDao, MailRepository mailRepository) {
        this.mailDao = mailDao;
        this.mailRepository = mailRepository;
    }

    public List<MailDto> mailList(int empno) {
        List<MailDto> list = mailDao.mailList(empno);
        return list;
    }

    public List<MailDto> selectMailDetail(Long id) {
        List<MailDto> list = mailDao.selectMailDetail(id);
        return list;
    }

    public void mailDelete(Long id) {
        mailDao.mailDelete(id);
    }

    public void sendMail(String content, LocalDateTime date, int empno, String sName, String title) {
        mailDao.sendMail(content, date, empno, sName, title);
    }

    public List<Dept> selectDeptList() {
        return mailDao.selectDeptList();
    }

    public List<Dept> selectEmpnoList(String deptname) {
        return mailDao.selectEmpnoList(deptname);
    }

    // paging 처리

    public Page<Mail> getMailPage(int empno, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return mailRepository.findByEmpno(empno, pageRequest);
    }

    public void deleteMailsByIds(List<Long> mailIds) {
        for (Long mailId : mailIds) {
            mailRepository.deleteById(mailId);
        }
    }
}