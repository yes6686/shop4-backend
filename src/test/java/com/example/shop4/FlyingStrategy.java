package com.example.shop4;

public class FlyingStrategy implements MovingStrategy{
    @Override
    public void move() {
        System.out.println("can fly");
    }
}
