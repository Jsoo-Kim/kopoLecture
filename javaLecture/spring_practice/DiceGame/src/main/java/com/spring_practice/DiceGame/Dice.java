package com.spring_practice.DiceGame;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Dice {
	int idx;
	int user;
	int computer;
	String result;
	String created;
	
	public Dice() {
		this.roll();
	}
	
	public void roll() {
		Random random = new Random();
		this.user = random.nextInt(6) + 1;
		this.computer = random.nextInt(6) + 1;
		if (this.user > this.computer) {
			this.result = "WIN";
		} else if (this.user < this.computer) {
			this.result = "LOSE";
		} else {
			this.result = "DRAW";
		}
		this.created = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
	}

}
