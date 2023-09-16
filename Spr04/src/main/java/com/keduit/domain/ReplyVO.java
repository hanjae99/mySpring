package com.keduit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplyVO {

    private Long rno;
    private Long bno;
    private String reply;
    private String replyer;
    private Date replyDate;
    private Date updateDate;
}
