package com.keduit.controller;

import com.keduit.domain.SampleDTO;
import com.keduit.domain.SampleDTOList;
import com.keduit.domain.TodoDTO;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(java.util.Date.class,
            new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping("")
    public String basic(){
        log.info("basic......");
        return "custom404";
    }

//    @GetMapping("/basicGet")
    @RequestMapping(value = "/basicGet", method = {RequestMethod.GET, RequestMethod.POST})
    public void basicGet(){
        log.info("basic post.....");
    }

    @GetMapping("/ex01")
    public String ex01(SampleDTO sDTO){
        log.info("ex01: " + sDTO);
        return "ex012345";
    }

    @GetMapping("/ex02")
    public String ex02(@RequestParam("name") String name, @RequestParam("age") int age){

        log.info("name: " + name);
        log.info("age: " + age);
        return "ex02";
    }

    @GetMapping("/ex02List")
    public String ex02List(@RequestParam("ids") ArrayList<String> ids){
        log.info("......ids: " + ids);
        return "ex02List";
    }

    @GetMapping("/ex02Array")
    public String ex02Array(@RequestParam("ids") String[] ids){
        log.info("array ids: " + Arrays.toString(ids));
        return "ex02Array";
    }

    @GetMapping("/ex02Bean")
    public void ex02Bean(SampleDTOList list){ /* 이름이 같으면 @RequestParam 생략 가능 */

        log.info("... list dtos: " + list);
    }

    @PostMapping("/ex03")
    public void ex03(TodoDTO todo){
        log.info("todo: " + todo);
    }

    @GetMapping("/ex04")
    public String ex04(SampleDTO sDTO, @ModelAttribute("page") int page){
        log.info("...dto: " + sDTO);
        log.info("...page: " + page);
        return "sample/ex04";
    }

    @GetMapping("/re1")
    public String re1(){
        log.info("...re1....");
        return "redirect:/sample/re2";
    }

    @GetMapping("/re2")
    public void re2(){
        log.info("....re2....");
    }

    @GetMapping("/ex06")
    public @ResponseBody SampleDTO ex06(){

        log.info("/ex06....");
        SampleDTO sdto = new SampleDTO();
        sdto.setAge(25);
        sdto.setName("박상현");
        return sdto;
    }

    @GetMapping("/ex07")
    public ResponseEntity<String> ex07(){
        log.info("/ex07.....");

        String msg = "{\"name\" : \"홍길동\"}";

        HttpHeaders header = new HttpHeaders();
        header.add("Content-type", "application/json;charset=UTF-8");
        return new ResponseEntity<String>(msg, header, HttpStatus.OK);
    }

    @GetMapping("/exUpload")
    public void exUpload(){
        log.info("exUpload..");
    }

    @PostMapping("/exUploadPost")
    public void exUploadPost(ArrayList<MultipartFile> files){
        files.forEach(file -> {
            log.info("-------------");
            log.info("name: " + file.getOriginalFilename());
            log.info("size: " + file.getSize());
            log.info("contentType: " +file.getContentType());
        });
    }
}
