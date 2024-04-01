
public class Classroom {
	static int nextClassId = 1;
	int classId;
	Student[] students;
	double totalClassMidtermScore;
	double totalClassFinaltermScore;
	double classMidtermAvg;
	double classFinaltermAvg;

	Classroom() {
		if (classId > 10) {
			nextClassId = 1;
		}
		classId = nextClassId;
		nextClassId++;
		setClassStudents();
		setTotalClassMidtermScore();
		setTotalClassFinaltermScore();
		setClassMidtermAvg();
		setClassFinaltermAvg();
	}

	void setClassStudents() {
		students = new Student[50];
		for (int i = 0; i < students.length; i++) {
			students[i] = new Student();
		}
	}

	void setTotalClassMidtermScore() {
		for (int i = 0; i < students.length; i++) {
			totalClassMidtermScore += students[i].midTermGrade;
		}
	}

	void setTotalClassFinaltermScore() {
		for (int i = 0; i < students.length; i++) {
			totalClassFinaltermScore += students[i].finalTermGrade;
		}
	}

	void setClassMidtermAvg() {
		classMidtermAvg = totalClassMidtermScore / students.length;
	}

	void setClassFinaltermAvg() {
		classFinaltermAvg = totalClassFinaltermScore / students.length;
	}

}
