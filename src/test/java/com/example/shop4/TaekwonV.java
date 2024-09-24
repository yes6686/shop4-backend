package com.example.shop4;

public class TaekwonV extends Robot{
    public TaekwonV(String name){
        super(name);
    }
    @Override
    public void move(){
        System.out.println("can only walk");
    }

    @Override
    public void attack() {
        System.out.println("missile");
    }


}
