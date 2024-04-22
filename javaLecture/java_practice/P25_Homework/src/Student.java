import java.util.Random;

public class Student {
	static int nextStudentId = 1;
	int studentId;
	double midTermGrade;
	double finalTermGrade;
	double totalScore;
	boolean gradeImproved;
	double gradeImprovementRate;

	Student() {
		if (nextStudentId > 50) {
			nextStudentId = 1;
		}
		this.studentId = nextStudentId;
		nextStudentId++;
		this.setMidTermGrade();
		this.setFinalTermGrade();
		this.totalScore = midTermGrade + finalTermGrade;
		this.setGradeImproved();
		this.setGradeImprovementRate();
	}

	// void로만 선언하면 해당 메서드는 기본적으로 '같은 패키지' 내의 클래스에서만 접근 가능

	void setMidTermGrade() {
		Random random = new Random();
		this.midTermGrade = random.nextInt(101);
	}

	void setFinalTermGrade() {
		Random random = new Random();
		this.finalTermGrade = random.nextInt(101);
	}

	void setGradeImproved() {
		if (this.midTermGrade < this.finalTermGrade) {
			this.gradeImproved = true;
		} else {
			this.gradeImproved = false;
		}
	}

	void setGradeImprovementRate() { // 최대 점수 대비 오른 점수로 성적향상률 계산
		this.gradeImprovementRate = ((this.finalTermGrade - this.midTermGrade) / 100) * 100;
	}
	
	@Override
	public String toString() {
		return "번호: " + studentId;
	}

}
