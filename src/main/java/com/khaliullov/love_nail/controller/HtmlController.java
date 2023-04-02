package com.khaliullov.love_nail.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
@Slf4j
public class HtmlController {



    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    @PostMapping("/post")
    public @ResponseBody ResponseEntity<String> getForm(@RequestParam String name,
                                                        @RequestParam String select,
                                                        @RequestParam String date,
                                                        @RequestParam String time,
                                                        @RequestParam String masterName,
                                                        @RequestParam String key)
    {


        log.info("----------------");
        log.info(name);
        log.info(select);
        log.info(date);
        log.info(time);
        log.info(masterName);
        log.info(key);

        return ResponseEntity.ok("{\"success\": true}");
    }
}
