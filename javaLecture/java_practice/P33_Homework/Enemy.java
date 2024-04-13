package javaLecture.java_practice.P33_Homework;

import javaLecture.java_practice.P33_Homework.Base;

public class Enemy extends Base {
    private Floor floor;

    public Enemy(Floor floor, int i) {
        this.floor = floor;
        this.name = "monster" + Integer.toString(i+1);
    }

    public int attack() {
        return 28;
    }


    @Override
    public void underAttack(int damage) {
        this.hp -= damage;
        if (!this.isLive()) {
            this.floor.removeEnemy(this);
            System.out.println(this.name + "이 사망했습니다.");
        }
    }
}