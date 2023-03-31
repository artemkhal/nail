package com.khaliullov.love_nail;

import com.khaliullov.love_nail.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
@Slf4j
public class Controller {

    @Autowired
    OrderService orderService;


    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    @PostMapping("/")
    public @ResponseBody void getForm(@RequestParam String name, @RequestParam String select,
                        @RequestParam String date, @RequestParam String time){
        log.info(name);
        log.info(select);
        log.info(date);
        log.info(time);
//        log.info(key);



    }
}
