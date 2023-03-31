package com.khaliullov.love_nail.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Customer {

    private int id;
    private String name;
    private String number;


}
