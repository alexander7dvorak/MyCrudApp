package com.example.WebApplication.Models;

import lombok.Data;

@Data
public class Car {
    private int id;
    private int manufacturerId;
    private int engineId;
    private String name;

    public Car(int id, int manufacturerId, int engineId, String name) {
        this.id = id;
        this.manufacturerId = manufacturerId;
        this.engineId = engineId;
        this.name = name;
    }

}
