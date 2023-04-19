package com.khaliullov.love_nail.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
@Entity
@Slf4j
public class Master {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private long chatId;

    @OneToMany
    private List<Order> orders;


    public Master() {
    }

    public Master(String name, Long chatId) {
        this.name = name;
        this.chatId = chatId;
        this.orders = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Master{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", chatId=" + chatId +
                ", orders=" + orders +
                '}';
    }
}
