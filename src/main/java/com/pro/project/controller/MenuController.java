package com.pro.project.controller;

import com.pro.project.entity.Menu;
import com.pro.project.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

@Controller
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/today")
    public String getTodayMenu(Model model) {
        LocalDate today = LocalDate.now();
        DayOfWeek currentDayOfWeek = today.getDayOfWeek();
        List<Menu> todayMenu = menuRepository.findByYearAndMonthAndDay(
                today.getYear(), today.getMonthValue(), today.getDayOfMonth()
        );
        System.out.println(LocalDateTime.now().getMonthValue());
        String dayOfWeekName = currentDayOfWeek.toString(); // 요일 이름 가져오기
        model.addAttribute("menus", todayMenu);
        model.addAttribute("notmenus", todayMenu.isEmpty());
        model.addAttribute("currentDayOfWeek", dayOfWeekName);
        return "Menu/today";
    }
    @GetMapping("/weekly")
    public String showMonthlyMenu(Model model) {

        LocalDate today = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int currentWeekNumber = today.get(weekFields.weekOfWeekBasedYear());

        // DB에서 현재 주차에 해당하는 주간 메뉴를 불러오기
        List<Menu> weeklyMenu = menuRepository.findByWeekNum(currentWeekNumber);


        model.addAttribute("weeklyMenu", weeklyMenu);
        System.out.println(weeklyMenu);
        return "Menu/weekly";
    }



}

