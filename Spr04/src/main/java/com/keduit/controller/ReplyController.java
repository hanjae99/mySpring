package com.keduit.controller;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyPageDTO;
import com.keduit.domain.ReplyVO;
import com.keduit.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reply/")
@Log4j
@AllArgsConstructor
public class ReplyController {

    private ReplyService service;

    @PostMapping(value = "/new", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> create(@RequestBody ReplyVO rVO){

        log.info("...ReplyVO: " + rVO);
        long insertCount = service.register(rVO);
        log.info("...ReplyInsertCount: " + insertCount);

        return insertCount == 1
                ? new ResponseEntity<String>("success", HttpStatus.OK)
                : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    더미데이터 넣는 코드 테스트
    @PostMapping(value = "/newList", consumes = "application/json",
            produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> createList(@RequestBody List<ReplyVO> list){
        log.info("...newList...");

        for (ReplyVO item : list){
            if (service.register(item) == 0){
                return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @GetMapping(value = "/{rno}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReplyVO> get(@PathVariable("rno") Long rno){
        log.info("...get: " + rno);

        return new ResponseEntity<ReplyVO>(service.get(rno), HttpStatus.OK);
    }

    @GetMapping(value = "/pages/{bno}/{page}" ,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReplyPageDTO> getList(@PathVariable("bno") Long bno,
                                                @PathVariable("page") int page){
        log.info("...getList: " + bno + ", " + page);

        Criteria cri = new Criteria(page, 10);
        log.info("...cri: " + cri);

        return new ResponseEntity<>(service.getListPage(cri, bno), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{rno}",
        produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno){

        log.info("...remove: " + rno);

        return service.remove(rno)
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
//
//    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
//        value = "/{rno}",
//        consumes = "application/json",
//        produces = {MediaType.TEXT_PLAIN_VALUE})

    @PutMapping(
            value = "/{rno}",
            consumes = "application/json",
            produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> modify(
            @RequestBody ReplyVO rVO,
            @PathVariable("rno") Long rno
    ){
        rVO.setRno(rno);
        log.info("...modify rno: " + rno);
        log.info("...modify vo: " + rVO);

        return service.modify(rVO)
                ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
