package com.pro.project.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class PageResultDto<MailDto, EN> {

    private List<MailDto> dtoList;

    public PageResultDto(Page<EN> result, Function<EN, MailDto> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());
    }
}
