package com.kopo.t11;

public class Student {
	int id;
	String name;
	int middleScore;
	int finalScore;
	String created;
	
	Student() {
		
	}
	
	Student(String name, int middleScore, int finalScore, String created) {
		this.name = name;
		this.middleScore = middleScore;
		this.finalScore = finalScore;
		this.created = created;
	}
}
