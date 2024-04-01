import java.util.Scanner;

public class Student {
	String name;
	int korScore;
	int engScore;
	int mathScore;
	int totalScore = korScore + engScore + mathScore;
	double avgScore = totalScore / (double) 3;
	
	Student() {
		
	}
	
	Student(String name) {
		this.name = name;
	}
	
	public void printScore() {
		System.out.println(this.name);
		System.out.println("국어: " + this.korScore);
		System.out.println("영어: " + this.engScore);
		System.out.println("수학: " + this.mathScore);
	}
	
	public void inputData() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("학생의 이름을 입력하세요.");
		this.name = scanner.nextLine();
		
		System.out.println("국어 점수를 입력하세요.");
		this.korScore = Integer.parseInt(scanner.nextLine());
		
		System.out.println("영어 점수를 입력하세요.");
		this.engScore = Integer.parseInt(scanner.nextLine());
		
		System.out.println("수학 점수를 입력하세요.");
		this.mathScore = Integer.parseInt(scanner.nextLine());
	}
	
	// 여기서부터는 따로 만들어 본 것
    public Student(String name, int korScore, int engScore, int mathScore) {
        this.name = name;
        this.korScore = korScore;
        this.engScore = engScore;
        this.mathScore = mathScore;
    }

    public int getTotalScore() {
        return korScore + engScore + mathScore;	
    }
	
	public double getAvgScore() {
		return this.avgScore;	
	}
}
