package com.keduit.controller;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;
import com.keduit.domain.PageDTO;
import com.keduit.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
@Log4j
public class BoardController {

    private final BoardService service;

    // DateFormat 형식 지정
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(java.util.Date.class,
                new CustomDateEditor(dateFormat, false));
    }

//    @GetMapping("/list")
//    public void list(Model model){
//        log.info("...list...");
//        model.addAttribute("list", service.getList());
//    }
    @GetMapping("/list")
    public void list(Criteria cri, Model model){
        log.info("...list...");
        model.addAttribute("list", service.getList(cri));

        int total = service.getTotalCount(cri);
        model.addAttribute("pageMaker", new PageDTO(cri, total));
    }

//    처음 등록 화면으로 이동
    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/register")
    public String register(BoardVO bVO, RedirectAttributes rttr){
        log.info("...register...");
        log.info(bVO);
        long bno = service.register(bVO);
        rttr.addFlashAttribute("result", bno);

        return "redirect:/board/list";
    }

    @GetMapping({"/get", "/modify"})
    public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model){
        log.info("...get or modify...");
        model.addAttribute("board", service.get(bno));
    }

    @PostMapping("/modify")
    public String modify(BoardVO bVO, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){
        log.info("...modify...");
        log.info(bVO);

        if (service.modify(bVO)){
            log.info("업데이트 성공!");
            rttr.addFlashAttribute("result", "success");
        }else {
            log.info("업데이트 실패");
        }
        rttr.addAttribute("pageNum", cri.getPageNum());
        rttr.addAttribute("amount", cri.getAmount());
        rttr.addAttribute("type", cri.getType());
        rttr.addAttribute("keyword", cri.getKeyword());

        return "redirect:/board/list";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){
        log.info("...remove...");
        if (service.remove(bno)){
            log.info("삭제 성공!");
            rttr.addFlashAttribute("result", "success");
        }else {
            log.info("삭제 실패");
        }
        rttr.addAttribute("pageNum", cri.getPageNum());
        rttr.addAttribute("amount", cri.getAmount());
        rttr.addAttribute("type", cri.getType());
        rttr.addAttribute("keyword", cri.getKeyword());

        return "redirect:/board/list";
    }
}
