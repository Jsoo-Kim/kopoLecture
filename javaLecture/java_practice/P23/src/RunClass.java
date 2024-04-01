import java.util.Scanner;

public class RunClass {
	public static void main(String[] args) {
		
		// NullPointerExcepntion 뜰 때 가장 먼저 의심할 것: 객체 생성 안 된 것!!
		// Student s1; // 이렇게 하면 객체 생성안 됨!
		
//		Student s1 = new Student("홍길동");
//		Student s2 = new Student("강감찬");
//		Student s3 = new Student("이성계");

//		Student[] student = new Student[3]; // 객체 배열 변수 생성
//		student[0] = new Student("홍길동"); // 객체 생성
//		student[1] = new Student("강감찬");
//		student[2] = new Student("이성계");
//		
//		student[0].korScore = 100; // 이런 식의 직접적인 프로퍼티 접근은 지양하려는 게 캡슐화!
//		student[0].engScore = 50;
//		student[0].mathScore = 30;
//
//		student[1].korScore = 90;
//		student[1].engScore = 80;
//		student[1].mathScore = 60;
//		
//		student[2].korScore = 50;
//		student[2].engScore = 100;
//		student[2].mathScore = 10;
//		
//		for (int i = 0; i < student.length; i++) {
//			student[i].printScore();
//		}
		
		
		Student[] student = new Student[3];
		
		int totalScore = 0;
		for (int i = 0; i < student.length; i++) {
			student[i] = new Student();
			System.out.println("국어 점수를 입력하세요.");
			student[i].inputData();
			totalScore += (student[i].korScore + student[i].engScore + student[i].mathScore);
		}
		double avgScore = totalScore / (double) student.length;
		System.out.println("총점: " + totalScore + " / 평균: " + avgScore);

		
		// 따로 만들어 본 것
//		int allTotalScore = 0;
//		for (int i = 0; i < student.length; i++) {
//            System.out.println("학생의 이름을 입력하세요.");
//            String name = scanner.nextLine();
//            System.out.println("국어 점수를 입력하세요.");
//            int korScore = Integer.parseInt(scanner.nextLine());
//            System.out.println("영어 점수를 입력하세요.");
//            int engScore = Integer.parseInt(scanner.nextLine());
//            System.out.println("수학 점수를 입력하세요.");
//            int mathScore = Integer.parseInt(scanner.nextLine());
//
//            student[i] = new Student(name, korScore, engScore, mathScore); 
//            allTotalScore += student[i].getTotalScore(); 
//        }
//        double allAvgScore = (double) allTotalScore / student.length; 
//        System.out.println("총점: " + allTotalScore + " / 평균: " + allAvgScore);
		
	}
}
