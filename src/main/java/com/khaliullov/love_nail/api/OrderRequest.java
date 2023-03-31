package com.khaliullov.love_nail.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khaliullov.love_nail.config.JacksonConfiguration;
import com.khaliullov.love_nail.entity.Customer;
import com.khaliullov.love_nail.entity.Master;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@RequiredArgsConstructor
@Entity(name = "`order`")
public class OrderRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    private Customer customer;
//    private Master master;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;
    private String service;
    private String comment;
}
