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
	
	public Dice(int idx) {
		this.idx = idx;
	}
	
	public Dice(int idx, int user, int computer, String result, String created) {
		this.idx = idx;
		this.user = user;
		this.computer = computer;
		this.result = result;
		this.created = created;
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
	

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getComputer() {
		return computer;
	}

	public void setComputer(int computer) {
		this.computer = computer;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Dice{" +
				"idx=" + idx +
				", user=" + user +
				", computer=" + computer +
				", result='" + result + '\'' +
				", created='" + created + '\'' +
				'}';
	}

}
