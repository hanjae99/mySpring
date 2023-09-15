package com.keduit.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {

    private int pageNum;
    private int amount;

    // T, W, C, TC, TWC .. (title, content, writer.. 등 검색 조건 설정)
    private String type;
    private String keyword;


    public Criteria(){
        this(1, 10);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public String[] getTypeArr(){
        return type == null ? new String[] {} : type.split("");
    }
}
