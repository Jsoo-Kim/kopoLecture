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
	
	public Student (int id, String name, int middleScore, int finalScore, String created) {
		this.id = id;
		this.name = name;
		this.middleScore = middleScore;
		this.finalScore = finalScore;
		this.totalScore = middleScore + finalScore;
		this.avgScore = (middleScore + finalScore) / 2.0;
		this.created = created;
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
	
	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) { // 있으면 안 좋을 것 같지만 임시로...
		this.totalScore = totalScore;
	}

	public double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(double avgScore) { // 있으면 안 좋을 것 같지만 임시로...
		this.avgScore = avgScore;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}
	
    @Override
    public String toString() {
        return "Student{" +
                "id: " + id +
                ", 이름: '" + name + '\'' +
                ", 중간점수: " + middleScore +
                ", 기말점수: " + finalScore +
                ", 총점: " + totalScore +
                ", 평균: " + avgScore +
                ", 생성일: '" + created + '\'' +
                '}';
    }	
	
}
