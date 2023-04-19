package com.khaliullov.love_nail.service;

import com.khaliullov.love_nail.entity.Master;
import com.khaliullov.love_nail.entity.Order;
import com.khaliullov.love_nail.entity.Status;
import com.khaliullov.love_nail.repo.MasterRepository;
import com.khaliullov.love_nail.repo.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HtmlService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MasterRepository masterRepository;

    public ResponseEntity<String> get(String name, String select, String date, String time, String masterName, int key) {
        try {
            Master master = masterRepository.findMasterByName(masterName);
            Order order = new Order(name, select, date, time, master, key, null, Status.CONFIRMED);
            orderRepository.save(order);
            log.info(order.toString());
        }catch (Exception e){
            return ResponseEntity.ok("{\"success\": false}");
        }
        return ResponseEntity.ok("{\"success\": true}");
    }
}
