package com.khaliullov.love_nail.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany
    private List<Order> orders;

    private long chatId;



    public Client() {
        this.orders = new ArrayList<>();
    }

    public Client(String name, Order order, long chatId) {
        if (this.orders == null) {
            this.orders = new ArrayList<>();
        }
        this.name = name;
        this.orders.add(order);
        this.chatId = chatId;
    }
}
