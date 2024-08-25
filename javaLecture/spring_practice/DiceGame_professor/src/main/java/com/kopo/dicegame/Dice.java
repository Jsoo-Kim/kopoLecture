package com.kopo.dicegame;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Dice {
	int idx;
	int user;
	int com;
	String result;
	String created;
	
	Dice() {
	}
	
	public void roll() {
		Random random = new Random();
		this.user = random.nextInt(6) + 1;
		this.com = random.nextInt(6) + 1;
		if (this.user > this.com) {
			this.result = "WIN";
		} else if (this.user < this.com) {
			this.result = "LOSE";
		} else {
			this.result = "DRAW";
		}
		this.created = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
	}
	
	public int getIdx() {
		return this.idx;
	}
	
	public int getUser() {
		return this.user;
	}
	
	public int getCom() {
		return this.com;
	}
	
	public String getResult() {
		return this.result;
	}
	
	public String getCreated() {
		return this.created;
	}
}
