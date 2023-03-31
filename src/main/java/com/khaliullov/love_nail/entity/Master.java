package com.khaliullov.love_nail.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Master {
    private int id;
    private String name;
    private List<String> services;
    private String about;

}
