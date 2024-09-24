package com.example.shop4;

public class Main {
    public static void main(String[] args) {
        Robot r1 = new TaekwonV("insang1");
        Robot r2 = new Atom("insang2");

        System.out.println(r1.getName());
        r1.setAttackStrategy(new MissileStrategy());
        r1.setMovingStrategy(new FlyingStrategy());
        r1.move();
        r1.attack();

        System.out.println(r2.getName());
        r2.setAttackStrategy(new PunchStrategy());
        r2.setMovingStrategy(new WalkingStrategy());
        r2.move();
        r2.attack();
    }
}
