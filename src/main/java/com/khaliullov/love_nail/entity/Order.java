package com.khaliullov.love_nail.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khaliullov.love_nail.repo.MasterRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.sql.JDBCType;
import java.time.*;
@ComponentScan
@Entity
@Getter
@Setter
@Table(name = "\'Order\'")
public class Order {



    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonProperty
    private String name;
    @JsonProperty
    @Column(name = "selected_service")
    private String nameOfService;
    @JsonProperty
    @Column(name = "service_date")
    private LocalDate date;
    @JsonProperty
    @Column(name = "service_time")
    private LocalTime time;
    @JsonProperty
    @ManyToOne(cascade = CascadeType.ALL)
    private Master master;
    @JsonProperty
    private int secretKey;

    @ManyToOne(cascade = CascadeType.ALL)
    private Client client;

    @Enumerated
    @Column(name = "order_status")
    private Status status;



    public Order() {
    }

    public Order(String name, String nameOfService, String date, String time, Master master, int secretKey, Client client, Status status) {
        this.name = name;
        this.nameOfService = nameOfService;
        this.date = convertDate(date);
        this.time = convertTime(time);
        this.master = master;
        this.secretKey = secretKey;
        this.client = client;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameOfService='" + nameOfService + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", master='" + master + '\'' +
                ", secretKey=" + secretKey +
                ", client=" + client +
                '}';
    }

    private LocalDate convertDate(String date){
        if (!date.isBlank()){
            String dat[] = date.split("\\.");
            return LocalDate.of(Integer.parseInt(dat[2]), Integer.parseInt(dat[1]), Integer.parseInt(dat[0]));
        }else return null;
    }

    private LocalTime convertTime(String time){
        if (!time.isBlank()){
            String tim[] = time.split(":");
            return LocalTime.of(Integer.parseInt(tim[0]), Integer.parseInt(tim[1]));
        }else return null;
    }

}
