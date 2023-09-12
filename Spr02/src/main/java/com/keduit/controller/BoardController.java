package com.keduit.controller;

import com.keduit.domain.BoardVO;
import com.keduit.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
@Log4j
public class BoardController {

    private final BoardService service;

    @GetMapping("/list")
    public void list(Model model){
        log.info("...list...");
        model.addAttribute("list", service.getList());
    }

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

    @GetMapping("/get")
    public void get(@RequestParam("bno") Long bno, Model model){
        log.info("...get...");
        model.addAttribute("board", service.get(bno));
    }

    @PostMapping("/modify")
    public String modify(BoardVO bVO, RedirectAttributes rttr){
        log.info("...modify...");
        log.info(bVO);

        if (service.modify(bVO)){
            log.info("업데이트 성공!");
            rttr.addFlashAttribute("result", "success");
        }else {
            log.info("업데이트 실패");
        }
        return "redirect:/board/list";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr){
        log.info("...remove...");
        if (service.remove(bno)){
            log.info("삭제 성공!");
            rttr.addFlashAttribute("result", "success");
        }else {
            log.info("삭제 실패");
        }
        return "redirect:/board/list";
    }
}
