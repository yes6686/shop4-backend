package com.example.shop4;

public class PunchStrategy implements AttackStrategy{
    @Override
    public void attack() {
        System.out.println("punch");
    }
}
