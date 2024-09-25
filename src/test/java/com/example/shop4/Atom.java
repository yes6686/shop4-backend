package com.example.shop4;

public class Atom extends Robot{
    public Atom(String name){
        super(name);
    }
    @Override
    public void move() {
        System.out.println("can fly");
    }

    @Override
    public void attack() {
        System.out.println("can punch");
    }
}
