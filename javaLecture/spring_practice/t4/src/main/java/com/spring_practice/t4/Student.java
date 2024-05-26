package com.spring_practice.t4;

public class Student {

	int id;
	String name;
	int middleScore;
	int finalScore;
	int totalScore;
	double avgScore;
	String created;
	
	Student() {
		
	}
	
	public Student (String name, int middleScore, int finalScore, String created) {
		this.name = name;
		this.middleScore = middleScore;
		this.finalScore = finalScore;
		this.totalScore = middleScore + finalScore;
		this.avgScore = (middleScore + finalScore) / 2.0;
		this.created = created;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMiddleScore() {
		return middleScore;
	}

	public void setMiddleScore(int middleScore) {
		this.middleScore = middleScore;
	}

	public int getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(int finalScore) {
		this.finalScore = finalScore;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	
	
}
