import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RunClass {
	public static void main(String[] args) { // 일단 틀만 잡아놓자~! 불명확한 기준들 교수님게 여쭤보고 다시 구현!
		// 학생들 생성
		Student[] students = makeStudents();

		// 2. 시험 별 총점 / 평균 출력
		printClassMidtermTotalAvg(students);
		printClassFinaltermTotalAvg(students);

		// 3-1. 중간고사 석차 출력
		printClassMidtermRank(students);

		// 3-2. 기말고사 석차 출력
		printClassFinaltermRank(students);

		// 4. 성적 향상이 이루어진 사람의 목록 출력
		List<Student> gradeImprovedStudents = printAndGetGradeImprovedList(students);

		// 5. 성적 향상률 석차 출력 (정렬해서 출력)
		printAndGetGradImprovementRateRank(gradeImprovedStudents);

		// 총 10개 반 (각 반별로 50명씩) 으로 확장. 반 별 순위까지 추가 출력
		Classroom[] classrooms = makeClassrooms();

		// 6-1. 반 별 중간고사 석차
		printTotalClassMidtermRank(classrooms);

		// 6-2. 반 별 기말고사 석차
		printTotalClassFinaltermRank(classrooms);

	}

	static Student[] makeStudents() { // Classroom 클래스가 생기면 이건 필요 없어짐!
		Student[] students = new Student[50];
		for (int i = 0; i < students.length; i++) {
			students[i] = new Student();
		}
		return students;
	}

	static void printClassMidtermTotalAvg(Student[] students) {
		int totalMidGrade = 0;
		for (int i = 0; i < students.length; i++) {
			totalMidGrade += students[i].midTermGrade;
		}
		System.out.println("중간고사 총점: " + totalMidGrade + " / " + "평균: " + (totalMidGrade / students.length));
	}

	static void printClassFinaltermTotalAvg(Student[] students) {
		int totalFinalGrade = 0;
		for (int i = 0; i < students.length; i++) {
			totalFinalGrade += students[i].finalTermGrade;
		}
		System.out.println("기말고사 총점: " + totalFinalGrade + " / " + "평균: " + (totalFinalGrade / students.length));
	}

	static void printClassMidtermRank(Student[] students) {
		IntRankComparator rankComparator = new IntRankComparator();

		int[][] midtermGradeArr = new int[students.length][2]; // [시험성적, 학생번호]

		for (int i = 0; i < students.length; i++) {
			midtermGradeArr[i][0] = (int) students[i].midTermGrade;
			midtermGradeArr[i][1] = students[i].studentId;
		}
		Arrays.sort(midtermGradeArr, rankComparator);

//        // 성적 내림차순으로 잘 정렬됐는지 확인 출력
//        for (int i = 0; i < midtermGradeArr.length; i++) {
//            System.out.println("중간고사 성적: " + midtermGradeArr[i][0] + ", 학생 번호: " + midtermGradeArr[i][1]);
//        }

		System.out.println("\n[중간고사 석차]");
		for (int i = 0; i < midtermGradeArr.length; i++) {
			System.out.println("" + (i + 1) + "등" + " / 학생 번호: " + midtermGradeArr[i][1]);
		}
	}

	static void printClassFinaltermRank(Student[] students) {
		IntRankComparator rankComparator = new IntRankComparator();

		int[][] finaltermGradeArr = new int[students.length][2]; // [시험성적, 학생번호]

		for (int i = 0; i < students.length; i++) {
			finaltermGradeArr[i][0] = (int) students[i].finalTermGrade;
			finaltermGradeArr[i][1] = students[i].studentId;
		}
		Arrays.sort(finaltermGradeArr, rankComparator);

//         //성적 내림차순으로 잘 정렬됐는지 확인 출력
//        for (int i = 0; i < midtermGradeArr.length; i++) {
//            System.out.println("중간고사 성적: " + midtermGradeArr[i][0] + ", 학생 번호: " + midtermGradeArr[i][1]);
//        }

		System.out.println("\n[기말고사 석차]");
		for (int i = 0; i < finaltermGradeArr.length; i++) {
			System.out.println("" + (i + 1) + "등" + " / 학생 번호: " + finaltermGradeArr[i][1]);
		}
	}

	static List<Student> printAndGetGradeImprovedList(Student[] students) {
		List<Student> gradeImprovedStudents = new ArrayList<>();

		for (int i = 0; i < students.length; i++) {
			if (students[i].gradeImproved) {
				gradeImprovedStudents.add(students[i]);
			}
		}
		System.out.println("성적 향상된 학생 번호 명단: " + gradeImprovedStudents.toString());

		return gradeImprovedStudents;
	}

	static double[][] printAndGetGradImprovementRateRank(List<Student> gradeImprovedStudents) {
		DoubleRankComparator rankComparator = new DoubleRankComparator();

		double[][] gradeImprovementRateArr = new double[gradeImprovedStudents.size()][2];

		for (int i = 0; i < gradeImprovementRateArr.length; i++) {
			gradeImprovementRateArr[i][0] = gradeImprovedStudents.get(i).gradeImprovementRate;
			gradeImprovementRateArr[i][1] = (double) gradeImprovedStudents.get(i).studentId;
		}
		Arrays.sort(gradeImprovementRateArr, rankComparator);

		System.out.println("\n[성적 향상 석차]");
		for (int i = 0; i < gradeImprovementRateArr.length; i++) {
			System.out.println("" + (i + 1) + "등" + " / 학생 번호: " + (int)gradeImprovementRateArr[i][1]);
		}
		return gradeImprovementRateArr;
	}

	static Classroom[] makeClassrooms() {
		Classroom[] classrooms = new Classroom[10];
		for (int i = 0; i < classrooms.length; i++) {
			classrooms[i] = new Classroom();
		}
		return classrooms;
	}

	static void printTotalClassMidtermRank(Classroom[] classrooms) {
		IntRankComparator rankComparator = new IntRankComparator();

		int[][] totalClassMidtermGradeArr = new int[classrooms.length][2]; // [시험성적, 반 번호]

		for (int i = 0; i < classrooms.length; i++) {
			totalClassMidtermGradeArr[i][0] = (int) classrooms[i].totalClassMidtermScore;
			totalClassMidtermGradeArr[i][1] = classrooms[i].classId;
		}
		Arrays.sort(totalClassMidtermGradeArr, rankComparator);

//        // 성적 내림차순으로 잘 정렬됐는지 확인 출력
//        for (int i = 0; i < totalClassMidtermGradeArr.length; i++) {
//            System.out.println("중간고사 성적: " + totalClassMidtermGradeArr[i][0] + ", 반 번호: " + totalClassMidtermGradeArr[i][1]);
//        }

		System.out.println("\n[전체 반 중간고사 석차]");
		for (int i = 0; i < totalClassMidtermGradeArr.length; i++) {
			System.out.println("" + (i + 1) + "등" + " / 반 번호: " + totalClassMidtermGradeArr[i][1]);
		}
	}

	static void printTotalClassFinaltermRank(Classroom[] classrooms) {
		IntRankComparator rankComparator = new IntRankComparator();

		int[][] totalClassFinaltermGradeArr = new int[classrooms.length][2]; // [시험성적, 반 번호]

		for (int i = 0; i < classrooms.length; i++) {
			totalClassFinaltermGradeArr[i][0] = (int) classrooms[i].totalClassFinaltermScore;
			totalClassFinaltermGradeArr[i][1] = classrooms[i].classId;
		}
		Arrays.sort(totalClassFinaltermGradeArr, rankComparator);

//        // 성적 내림차순으로 잘 정렬됐는지 확인 출력
//        for (int i = 0; i < totalClassFinaltermGradeArr.length; i++) {
//            System.out.println("기말고사 성적: " + totalClassFinaltermGradeArr[i][0] + ", 반 번호: " + totalClassFinaltermGradeArr[i][1]);
//        }

		System.out.println("\n[전체 반 기말고사 석차]");
		for (int i = 0; i < totalClassFinaltermGradeArr.length; i++) {
			System.out.println("" + (i + 1) + "등" + " / 반 번호: " + totalClassFinaltermGradeArr[i][1]);
		}
	}

}