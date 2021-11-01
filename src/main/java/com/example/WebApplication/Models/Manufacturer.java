package com.example.WebApplication.Models;

import lombok.Data;

@Data
public class Manufacturer {
    private int id;
    private String name;

    public Manufacturer(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
