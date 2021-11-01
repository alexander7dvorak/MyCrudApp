package com.example.WebApplication.Models;

import lombok.Data;

@Data
public class CarEngine {
    private int id;
    private int manufacturerId;
    private String name;

    public CarEngine(int id, int manufacturerId, String name) {
        this.id = id;
        this.manufacturerId = manufacturerId;
        this.name = name;
    }
}
