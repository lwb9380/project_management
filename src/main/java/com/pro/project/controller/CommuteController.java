package com.pro.project.controller;

import com.pro.project.auth.AuthInfo;
import com.pro.project.dto.*;
import com.pro.project.entity.Menu;
import com.pro.project.repository.MenuRepository;
import com.pro.project.service.DeptService;
import com.pro.project.service.MailService;
import com.pro.project.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
@Slf4j
public class CommuteController {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    StuService stuService;

    @Autowired
    MailService mailService;

    @Autowired
    DeptService deptService;
    @GetMapping("/resetall")
    public String makego(HttpServletRequest request){
        stuService.resetall();

        return "send";
        //초기화임
    }
    @GetMapping("/mail/sendmail/{empno}")
    public String sendmail(@PathVariable int empno, Model model){
        model.addAttribute("empno", empno);

        return "send";

        //부서원에게 다이렉트 메일 보내기

    }

    @PostMapping("/sendgo")
    public String sendTest(@RequestParam("empno") int empno, @RequestParam("title") String title){
        System.out.println("============================================");

        System.out.println(title);
        System.out.println(empno);
        return "sendgo";

        //위에서 보내면 오는 컨트롤러 테스트용
    }


    @GetMapping("/start")
    public String startpage(HttpServletRequest request,Model model){


        HttpSession session=request.getSession();
        Long num=(Long)session.getAttribute("user");
        int empno=num.intValue();

        String authority=stuService.getAuthority(empno);
        int deptno=stuService.getDeptNo(empno);
        Working working1=stuService.getlogininfo(empno);  //오늘의 근무 상태를 일단 가져옴 empno로
        Date last=working1.getLastday(); //근무 상태에 있는 마지막으로 로그인 한 날을조회
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastday=sdf.format(last);
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
        Date toda=new Date();  //오늘 날짜를 조회
        LocalDate today2 = LocalDate.now();
        DayOfWeek currentDayOfWeek = today2.getDayOfWeek();
        List<Menu> todayMenu = menuRepository.findByYearAndMonthAndDay(
                today2.getYear(), today2.getMonthValue(), today2.getDayOfMonth()
        );
        String today=sdf.format(toda);
        String todaysday=sdf2.format(toda);
        //두 날짜를 데이트포맷으로 깔끔하게 정리함 원활한 비교를 위해


        if(!lastday.equals(today)){
            stuService.resetworking(empno);
            stuService.updatelastday(today,empno);
        }
        //마지막 로그인 날자와 오늘 날자가 다르면 working 테이블을 초기화 함
        Working working=stuService.getlogininfo(empno);

        String newtoday="day"+todaysday;
        //휴가 여부 조회
        String vacation="";


        try{
            vacation= stuService.checkvacation(newtoday,empno);
            if(vacation.equals("휴가")){
                stuService.updatevacation(empno);


            }if(vacation.equals("무급")){

                stuService.updatevacation2(empno);

            }
        } catch (Exception e){

        }

        //만약 오늘이 한 달 스케쥴 테이블에 기록된 휴가 날이라면 vacation update로 dept와 working 테이블에 휴가 정보를 업데이트함


        System.out.println("=================================");
        System.out.println(vacation);

        String doweek=stuService.getdoweek();
        //오늘이 무슨 요일인지 받아옴


        System.out.println("====================== doweek");
        System.out.println(doweek);


        String result="";
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue();


            Day day=stuService.typecheck(empno,month);




        int type=0; //디폴트값


        if(day==null){
            type=2;
        } else {
            switch (doweek){
                case "월요일": type=day.getMonday();
                    break;
                case "화요일": type=day.getTuesday();
                    break;
                case "수요일": type=day.getWednesday();
                    break;
                case "목요일": type=day.getThursday();
                    break;
                case "금요일": type=day.getFriday();
                    break;
            }
        }



        switch (type){
            case 1: result="8시 출근 5시 퇴근";
                break;
            case 2: result="9시 출근 6시 퇴근";
                break;
            case 3: result="10시 출근 7시 퇴근";
                break;
        }


        List<Dept> list=stuService.deptlist(deptno);  //부서번호로 전체 부서원 리스트 받아옴
        int numberToRemove = empno;   //전체 부서원 리스트에서 본인을 제외하기 위한 변수

        Iterator<Dept> iterator = list.iterator();
        while (iterator.hasNext()) {
            Dept dept = iterator.next();
            if (numberToRemove == dept.getEmpno()) {
                iterator.remove();
            }
        }// 이터레이터로 반복문을 돌려서 내 부서 리스트에 로그인 한 본인의 정보가 들어가지 않게 함. 본인은 메인페이지에 크게 나오기 때문
        String dayOfWeekName = currentDayOfWeek.toString(); // 요일 이름 가져오기
        String deptname = list.get(0).getDeptname();
        Long preWorkCount = deptService.countPreWorkByDeptNo((long) deptno);  //출근전 카운트
        Long workCount = deptService.countWorkByDeptNo((long) deptno); //출근 카운트
        Long leaveCount = deptService.countLeaveByDeptNo((long) deptno); //퇴근 카운트
        Long commuteCount = deptService.countCommuteByDeptNo((long) deptno); //외출 카운트
        Long vacationCount = deptService.countVacationByDeptNo((long) deptno); //휴가 카운트
        Long countTotal = deptService.countTotal((long) deptno); //카운트의 총합



        try{
            List<Notice> list3=stuService.getdeptNotice(deptno);

            if(list3.size()>3){
                for(int i=list3.size();i>3;i--){
                    list3.remove(0);
                }
            }
                
            for(int i=0;i<list3.size();i++){
                if(list3.get(i).getDeptno()==500){
                    list3.get(i).setWitch("전체 공지");
                }else {
                    list3.get(i).setWitch("부서 공지");
                }
            }

            System.out.println(list3);

            model.addAttribute("list3",list3);
        } catch (Exception e){

        }



        String job=stuService.getjob(empno);

        model.addAttribute("job",job);
        model.addAttribute("authority",authority);
        model.addAttribute("deptname", deptname);
        model.addAttribute("list", list);
        model.addAttribute("result", result);
        model.addAttribute("working", working);
        model.addAttribute("vacation", vacation);
        // 여기부터 ( 유비의 count )
        model.addAttribute("deptno", deptno);
        model.addAttribute("preWorkCount", preWorkCount);
        model.addAttribute("workCount", workCount);
        model.addAttribute("leaveCount", leaveCount);
        model.addAttribute("commuteCount", commuteCount);
        model.addAttribute("vacationCount", vacationCount);
        model.addAttribute("countTotal",countTotal);
        // 여기부터 (범)
        List<Dept> emplist = mailService.selectEmpnoList(deptno);

        model.addAttribute("sessionemp", empno);
        model.addAttribute("emplist", emplist);


        List<MailDto> mail = mailService.mailList(empno);
        model.addAttribute("mail", mail);

        // 여기부터 (민)
        model.addAttribute("menus", todayMenu);
        model.addAttribute("notmenus", todayMenu.isEmpty());
        model.addAttribute("currentDayOfWeek", dayOfWeekName);

        System.out.println("=======================");
        System.out.println(result);
        return "hi";

    }

    @PostMapping("/makecommute")
    @ResponseBody
    public String processData(HttpServletRequest request) {

        //출근 버튼 누르면 출근을 기록하기 위함


        HttpSession session=request.getSession();
        Long num=(Long)session.getAttribute("user");
        int empno=num.intValue();

        int deptno=stuService.getDeptNo(empno);
        LocalDateTime start=LocalDateTime.now();
        String starttime=start.toString();
        String tttime=starttime.substring(11, 16);
        System.out.println("================================"+tttime);

        String responseData=tttime;

        String ischul=stuService.ischul1(empno);
        //오늘의 중복 출근 여부를 찾음


        String doweek=stuService.getdoweek();
        //오늘이 무슨 요일인지 받아옴

        String result="";
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue();
        Day day=stuService.typecheck(empno,month);

        //오늘 받아온 요일을 9월 스케쥴표에서 검색해서 오늘이 어떤 근무형태인지 switch case로 결정함

        int type=0; //디폴트값

        switch (doweek){
            case "월요일": type=day.getMonday();
                break;
            case "화요일": type=day.getTuesday();
                break;
            case "수요일": type=day.getWednesday();
                break;
            case "목요일": type=day.getThursday();
                break;
            case "금요일": type=day.getFriday();
                break;
        }

        String newtoday=" ";
        String vacation="기본";
        LocalTime hometime = LocalTime.of(18, 0, 0);
        LocalTime halfcometime=LocalTime.of(9,0,0);
        if(type==1){
            hometime=LocalTime.of(17,0,0);
            halfcometime=LocalTime.of(8,0,0);
        } else if (type==2) {
            hometime=LocalTime.of(18,0,0);
            halfcometime=LocalTime.of(9,0,0);
        } else if (type==3) {
            hometime=LocalTime.of(19,0,0);
            halfcometime=LocalTime.of(10,0,0);
        }

        LocalDateTime now = LocalDateTime.now();
        LocalTime time = now.toLocalTime();

        if(time.isAfter(halfcometime)){
            stuService.updatetardy(empno,month);
        } //요일에서 출근 시간 받아오고 출근 눌렀을 때의 시간이 출근 시간보다 늦으면 지각 체크


        if (ischul.equals("o")||ischul.equals("oo")){

            responseData="이미출근";
        } else {


            try{
                newtoday="day"+LocalDate.now().getDayOfMonth();
                vacation= stuService.checkvacation(newtoday,empno);

            }catch (Exception e){
                vacation="아님";
            }

            if(vacation.equals("오전 반차")){
                LocalTime tardytime=halfcometime.plusHours(5);
                String halrtime=time.toString().substring(0,5);

                if(time.isAfter(tardytime)){
                    stuService.updateWork(halrtime,empno);

                } else {
                    stuService.updateWork(halfcometime.toString(),empno);
                    stuService.resettardy(empno);
                }



            } else{

                stuService.updateWork(tttime,empno);
            }

        }

        return responseData;

        //중복 출근을 했을 경우 이미 출근을 리턴해서 자바스크립트에서 이미 출근했다고 alert 시킴. 그게 아니라면 오늘 출근 날짜를 기록하고 출근했다는 기록을 각 db에 남김

    }

    @PostMapping("/takearest")
    @ResponseBody
    public String takeaRest(HttpServletRequest request){
        HttpSession session=request.getSession();
        Long num=(Long)session.getAttribute("user");
        int empno=num.intValue();


        int deptno=stuService.getDeptNo(empno);

        //외출신청

        String responseData="";
        String  working=stuService.checkisworking(empno);
        //현재 근무 정보를 받아옴
        System.out.println(working+"======================================");


        if(working.equals("퇴근")||working.equals("출근전")){
            responseData="이미";
            return responseData;
        }
        //현재 근무정보가 퇴근이면 외출 버튼 클릭 불가
        //만약 휴가 날이라면 애초에 외출 버튼이 안나옴

        if(!working.equals("외출")){
            try{
                stuService.updaterest(empno);
                responseData="성공";
            } catch (Exception e){
                responseData="오류";
            }  // 현재 외출중, or 퇴근이 아니라면 외출 성공


        } else if(working.equals("외출")){

            try{
                stuService.finishrest(empno);
                responseData="복귀";
            } catch (Exception e){
                responseData="오류";
            } //이미 외출중이라면 외출에서 복귀함
        }

        return responseData;
    }


    @PostMapping("/reset")
    public void reset(HttpServletRequest request){
        HttpSession session=request.getSession();
        Long num=(Long)session.getAttribute("user");
        int empno=num.intValue();


        int deptno=stuService.getDeptNo(empno);

        stuService.resetworking(empno);
        //초기화 버튼에서 돌아가는 초기화

    }

    @PostMapping("/requestextrawork")
    @ResponseBody
    public String requestextra(HttpServletRequest request){
        HttpSession session=request.getSession();
        Long num=(Long)session.getAttribute("user");
        int empno=num.intValue();

        Working working=stuService.getlogininfo(empno);
        
        if(working.getIschul().equals("oo")){
            String rett="이미퇴근";
            return rett;
        } else{
            if(working.getTardy().equals("o")){
                String error="지각";
                return error;
            } else{
                try{

                    String extra=stuService.isextratoday(empno);

                    if(extra.equals("x")){
                        stuService.requestextra(empno);
                        String rett="신청완료";
                        return rett;
                    } else if(working.getIschul().equals("oo")) {
                        String rett="이미퇴근";
                        return rett;
                    }else{

                        String rett="이미신청";
                        return rett;
                    }


                } catch (Exception e){

                    String error="신청 오류";
                    return error;
                }
            }
        }





    }

    @PostMapping("/quitcommute")
    @ResponseBody
    public String quitcommute(@RequestParam("result") String resultValue,HttpServletRequest request){

        HttpSession session=request.getSession();
        Long num=(Long)session.getAttribute("user");
        int empno=num.intValue();


        int deptno=stuService.getDeptNo(empno);
        Working working=stuService.getlogininfo(empno);
        int month=LocalDateTime.now().getMonthValue();
        //퇴근임


        LocalDateTime start=LocalDateTime.now();

        String starttime=start.toString();
        String tttime=starttime.substring(11, 16);

        String responseData=tttime;


        if(working.getIsworking().equals("외출")){
            responseData="외출";
            return responseData;
        }

        //외출중에는 퇴근이 불가능함


        String ischul=stuService.ischul1(empno);
        LocalTime hometime = LocalTime.of(18, 0, 0);
        LocalDateTime now = LocalDateTime.now();
        LocalTime time = now.toLocalTime();

        if(resultValue.equals("9시 출근 6시 퇴근")){
            hometime = LocalTime.of(18, 0, 0);
        } else if (resultValue.equals("10시 출근 7시 퇴근")) {
            hometime=LocalTime.of(19,0,0);
        } else if (resultValue.equals("8시 출근 5시 퇴근")) {
            hometime=LocalTime.of(17,0,0);
        }

        //오늘 근무 타입에 따라 time 타입으로 퇴근시간을 잡음. 연산하기 위해서

        boolean canIgoHome=hometime.isBefore(time);

        //hometime(퇴근시간) 이 현재 시각보다 이전이라면 (아직 퇴근시간이 되지 않았다면) 퇴근 로직이 실행되지 않음


        if (ischul.equals("oo")){

            responseData="이미퇴근";
        } else if (!canIgoHome) {
            responseData="아직못감";
        } else {
            String tardy=stuService.istardytoday(empno);


            if(working.getExtrawork().equals("o")){
                int nowtime=time.getHour();
                int homeHour = hometime.getHour();
                int extraworktime=nowtime-homeHour;

                stuService.updateextrawork(extraworktime,empno,month);

            }

            if(!tardy.equals("o")){

                //여기다가 if 넣어서 연장근무 O면은 그거 넣어주기 내일
                stuService.updateworktime(empno,month);
            } else if(tardy.equals("o")){
                String commutetimestr=working.getCometime();
                LocalTime commutetime=LocalTime.parse(commutetimestr);
                // 시간 정보를 int로 추출
                int commuteHour = commutetime.getHour();
                int homeHour = hometime.getHour();





                // 두 시간 값의 차 계산
                int hourDifference = homeHour - commuteHour;
                if(hourDifference>8){
                    hourDifference=8;
                }
                stuService.updateworkself(hourDifference,empno,month);
            } //해당 if문은 지각을 하지 않았으면 8시간을 근무 기록 하고 오늘 지각을 했으면 퇴근시간-출근시간으로 실제 근무 시간만을 기록함.
            //지각하고 8시간 넘게 일해도 8시간만 기록됨
            //지각한 날은 연장 근무 기록이 불가능 하게 해둠.


                stuService.updatego(tttime,empno);
        }
        String newtoday="day"+LocalDate.now().getDayOfMonth();
        String vacation= stuService.checkvacation(newtoday,empno);
        System.out.println("==========================================");
        System.out.println(vacation);
        if(vacation.equals("오후 반차")){

            boolean canIgoHome2=time.isAfter(hometime.minusHours(5));
            System.out.println(canIgoHome2);
            if(canIgoHome2){
                stuService.updatego(hometime.toString(),empno);
                stuService.updateworktime(empno,month);
                responseData=tttime;
            } else{
                responseData="아직못감";
            }

        }

        return responseData;

    }




}
