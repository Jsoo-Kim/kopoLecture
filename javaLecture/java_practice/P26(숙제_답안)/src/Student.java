
public class Student {
	private String name; // 같은 클래스 내에서만 직접 접근 가능
	public int middleScore;
	int finalScore;
	int totalScore;
	
	
	Student() {
		
	}
	
	Student(String name) {
		this.name = name;
	}
	
	Student(String name, int middleScore, int finalScore) {
		this.name = name;
		this.middleScore = middleScore;
		this.finalScore = finalScore;
		this.totalScore = middleScore + finalScore;
	}
	
	@Override // 어노테이션 생략 가능
	public String toString() {
		return this.name + " (중간: " 
				+ this.middleScore + ", 기말: " 
				+ this.finalScore + ", 총합: "
				+ this.totalScore + ")";
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setMiddleScore(int middleScore) {
		this.middleScore = middleScore;
	}
	
	public int getMiddleScore() {
		return this.middleScore;
	}
	
	public void setFinalScore() {
		this.finalScore = finalScore;
	}
	
	public int getFinalScore() {
		return this.finalScore;
	}

	public void setTotalScore() {
		this.totalScore = totalScore;
	}
	
	public int getTotalScore() {
		return this.totalScore;
	}
}
