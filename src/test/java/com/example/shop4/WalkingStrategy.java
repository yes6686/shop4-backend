package com.example.shop4;

public class WalkingStrategy implements MovingStrategy{
    @Override
    public void move() {
        System.out.println("can only walk");
    }
}
