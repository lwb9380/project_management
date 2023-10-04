package com.pro.project.controller;

import com.pro.project.dto.Dept;
import com.pro.project.dto.MailDto;
import com.pro.project.entity.Mail;
import com.pro.project.repository.MailRepository;
import com.pro.project.service.MailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Log4j2
public class SampleController {

    @Autowired
    MailRepository mailRepository;

    @Autowired
    MailService mailService;

    @GetMapping("/mailList/{empno}")
    public String mailList(@PathVariable int empno,
                           @RequestParam(defaultValue = "0") String page,
                           @RequestParam(defaultValue = "10") int pageSize,
                           Model model) {
        List<MailDto> mail = mailService.mailList(empno);
        int a = mail.size();
        model.addAttribute("size", a);
        int pageNumber;

        try {
            pageNumber = Integer.parseInt(page);
        } catch (NumberFormatException e) {
            pageNumber = 0;
        }
        Page<Mail> mailPage = mailService.getMailPage(empno, pageNumber, pageSize);
        model.addAttribute("mails", mailPage);
        return "mailList";
    }


    @GetMapping("/mailDetail/{id}")
    public String mailDetail(@PathVariable Long id, Model model) {
        List<MailDto> mail = mailService.selectMailDetail(id);
        model.addAttribute("mails", mail);
        return "mailDetail";
    }

    @GetMapping("/deptSendMail/{empno}")
    public String deptSendMail(@PathVariable int empno, Model model){
        model.addAttribute("empno", empno);
        return "deptSendMail";
    }

    @PostMapping("/deptSendMail")
    public String deptSendMail2(@RequestParam("content") String content,
                                @RequestParam("empno") int empno,
                                @RequestParam("title") String title,
                                Model model) {
        LocalDateTime date = LocalDateTime.now();
        String sName = "25";
        mailService.sendMail(content, date, empno, sName, title);
        return "sendSuccess";
    }


    @GetMapping("/sendMail")
    public String sendMail( Model model) {
        List<Dept> deptList = mailService.selectDeptList();
        model.addAttribute("deptList", deptList);
        return "sendMail";
    }

    @PostMapping("/sendMail")
    public String sendMail(@RequestParam("content") String content,
                           @RequestParam("title") String title,
                           Model model) {
        List<Dept> list = mailService.selectDeptList();
        LocalDateTime date = LocalDateTime.now();
        String sName = "30";
        model.addAttribute("deptList", list);
        mailService.sendMail(content, date, 30, sName, title);
        return "sendSuccess";
    }

    @GetMapping("getEmpnosByDept")
    @ResponseBody
    public List<Dept> getEmpnosByDept(@RequestParam("dept") String dept) {
        List<Dept> empnos = mailService.selectEmpnoList(dept);
        return empnos;
    }

    @GetMapping("mailDelete")
    public String mailDelete(@RequestParam("id") Long id,
                             @RequestParam("empno") int empno) {
        mailService.mailDelete(id);
        return "redirect:/mailList/" + empno;
    }

    @PostMapping("deleteMails")
    public void mailsDelete(@RequestBody List<Long> selectedMailIds,
                                              Model model) {
            mailService.deleteMailsByIds(selectedMailIds);
    }

}
