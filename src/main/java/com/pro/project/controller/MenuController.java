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
import java.util.List;

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
        List<Menu> weeklyMenu = menuRepository.findByWeekNum(42);

        model.addAttribute("weeklyMenu", weeklyMenu);
        System.out.println(weeklyMenu);
        return "Menu/weekly";
    }



}

