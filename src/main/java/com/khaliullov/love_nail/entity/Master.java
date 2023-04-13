package com.khaliullov.love_nail.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
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
