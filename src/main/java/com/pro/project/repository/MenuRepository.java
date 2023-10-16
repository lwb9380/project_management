package com.pro.project.repository;

import com.pro.project.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByDw(DayOfWeek dw);
    List<Menu> findByMonth(int month);


    List<Menu> findByYearAndMonthAndDay(int year, int Month, int dayOfMonth);
}
