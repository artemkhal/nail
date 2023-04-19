package com.khaliullov.love_nail.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Entity
@Table(name = "'services'")
@Getter
@Setter
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Duration duration;
    private int price;

    public Service() {
    }

    public Service(String name) {
        this.name = name;
        switch (name){
            case "Item 1":{
                this.duration = Duration.ofMinutes(90);
                this.price = 1500;
                break;
            }
            case "Item 2":{
                this.duration = Duration.ofMinutes(60);
                this.price = 1200;
                break;
            }
            case "Item 3":{
                this.duration = Duration.ofMinutes(120);
                this.price = 2000;
            }
        }
    }
}


