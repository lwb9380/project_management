package com.pro.project.service;

import com.pro.project.dao.SampleDao;
import com.pro.project.dto.Day;
import com.pro.project.dto.Dept;
import com.pro.project.dto.VacationDto;
import com.pro.project.dto.Working;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Service
public class StuService {
    private final SampleDao sampledao;

    @Autowired
    public StuService(SampleDao sampledao) {
        this.sampledao = sampledao;
    }



    public String ischul1(int empno){
        return sampledao.chul1(empno);
    }


    public void updateWork(String cometime,int empno){
        sampledao.updateWork(cometime,empno);
        sampledao.updateWork2(empno);
    }

    public void updatego(String gotime, int empno){
        sampledao.updatego(gotime,empno);
        sampledao.updatego2(empno);
    }


    public Day typecheck(int empno, int month){
        return sampledao.typecheck(empno,month);
    }

    public Working getlogininfo(int empno){
        return sampledao.getlogininfo(empno);
    }

    public void resetworking(int empno){
        sampledao.resetworking(empno);
        sampledao.resetworking2(empno);
        sampledao.resettardy(empno);
    }

    public List<Dept> deptlist(int deptno){ return sampledao.deptlist(deptno); }

    public void updatelastday(String today, int empno){sampledao.updatelastday(today,empno); }

    public String getdoweek(){
        LocalDate today = LocalDate.now();

        // 요일을 가져와서 문자열로 변환
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        String todayAsString = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN);


        return todayAsString;
    }

    public void updaterest(int empno){sampledao.updaterest(empno);}

    public void finishrest(int empno){sampledao.finishrest(empno);}

    public String checkisworking(int empno){
        return sampledao.checkisworking(empno);
    }

    public String checkvacation(String day,int empno){
        return sampledao.checkvacation(day,empno);
    }

    public void updatevacation(int empno){
        sampledao.updatevacation(empno);
    }

    public String getAuthority(int empno) {return sampledao.getAuthority(empno); }

    public void updatetardy(int empno,int month) {sampledao.updatetardy(empno); sampledao.checktardy(empno,month); }

    public void updateworktime(int empno,int month){sampledao.updateworktime(empno,month);}

    public void updateworkself(int worktime,int empno,int month){sampledao.updateworkself(worktime,empno,month);}

    public String istardytoday(int empno){return sampledao.istardytoday(empno);}
    public void updateextrawork(int extratime,int empno, int month){sampledao.updateextrawork(extratime,empno,month);}

    public int getDeptNo(int empno){return sampledao.getDeptNo(empno);}

    public void resetall(){sampledao.resetall();  sampledao.resetall2();}

    public  List<VacationDto> getVacationRequest(int deptno){return sampledao.getVacationRequest(deptno);}

    public VacationDto getvacationone(int id){return sampledao.getvacationone(id);}

    public void updateDayVacation(String day,int empno,int month){sampledao.updateDayVacation(day,empno,month);}

    public void deleteVacationRequest(int id){sampledao.deleteVacationRequest(id);}

    public void harftime(String day,int empno, int month, String half){sampledao.halftime(day,empno,month,half);}

    public void resettardy(int empno){sampledao.resettardy(empno);}
}
