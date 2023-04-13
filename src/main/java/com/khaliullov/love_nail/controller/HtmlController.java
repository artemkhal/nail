package com.khaliullov.love_nail.controller;

import com.khaliullov.love_nail.entity.Order;
import com.khaliullov.love_nail.service.HtmlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
@Slf4j
public class HtmlController {


    @Autowired
    HtmlService service;

    @GetMapping("/")
    public String getIndex(){
        return "index";
    }


    @PostMapping("/post")
    public @ResponseBody ResponseEntity<String> getForm(
            @RequestParam String name,
            @RequestParam String select,
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam String masterName,
            @RequestParam int key
                        )
    {
//        log.info(new Order(name, select, date, time, masterName, key).toString());
        return service.get(name, select, date, time, masterName, key);
//        return ResponseEntity.ok("{\"success\": true}");
    }
}
