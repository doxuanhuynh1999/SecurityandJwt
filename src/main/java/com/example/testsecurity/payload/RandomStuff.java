package com.example.testsecurity.payload;

import lombok.Data;

@Data
public class RandomStuff {
    private String message;

    public RandomStuff() {
    }

    public RandomStuff(String message) {
        this.message = message;
    }
}
