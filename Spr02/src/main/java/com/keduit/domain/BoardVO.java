package com.keduit.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BoardVO {

    private Long bno;
    private String title;
    private String content;
    private String writer;

//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date regdate;

//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updatedate;
}
