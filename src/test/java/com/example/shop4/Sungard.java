package com.example.shop4;

public class Sungard extends Robot{
    public Sungard(String name){
        super(name);
    }

    @Override
    public void move() {
        System.out.println("can fly");
    }

    @Override
    public void attack() {
        System.out.println("missile");
    }
}
