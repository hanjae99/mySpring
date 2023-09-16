package com.keduit.controller;

import com.keduit.domain.SampleVO;
import com.keduit.domain.Ticket;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {

    @GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
    public String getText() {
        log.info("MiME TYPE: " + MediaType.TEXT_PLAIN_VALUE);
        return "hello";
    }

    @GetMapping(value = "/getSample",
            produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            })
    public SampleVO getSample() {
        return new SampleVO(99, "은평구", "폴킴");
    }

    @GetMapping(value = "/getList")
    public List<SampleVO> getList() {
        return IntStream.range(1, 10)
                .mapToObj(i -> new SampleVO(i, i + "First", i + "Last"))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/getMap")
    public Map<String, SampleVO> getMap() {
        Map<String, SampleVO> map = new HashMap<>();

        map.put("first", new SampleVO(100, "부천", "창모"));
        map.put("second", new SampleVO(101, "수원", "페이커"));
        map.put("third", new SampleVO(102, "신림", "ChatGPT"));
        map.put("fourth", new SampleVO(103, "광명", "사랑꾼"));
        map.put("fifth", new SampleVO(104, "수유리", "해바라기"));

        return map;
    }

    @GetMapping(value = "/check", params = {"height", "weight"})
    public ResponseEntity<SampleVO> check(double height, double weight) {
        SampleVO vo = new SampleVO(0, height + "", weight + "");

        ResponseEntity<SampleVO> result = null;

        if (height < 150) {
            result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
        } else {
            result = ResponseEntity.status(HttpStatus.OK).body(vo);
        }
        return result;
    }

    @GetMapping("/product/{cat}/{pid}")
    public String[] getPath(
            @PathVariable("cat") String cat,
            @PathVariable("pid") Integer pid
    ) {
        return new String[]{"category: " + cat, "productId: " + pid};
    }

    @PostMapping("/ticket")
    public Ticket convert(@RequestBody Ticket ticket){
        log.info("...ticket..." + ticket);
        return ticket;
    }
}
