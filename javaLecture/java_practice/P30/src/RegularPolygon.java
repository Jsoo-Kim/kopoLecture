import java.util.Scanner;

public class RegularPolygon extends Base {
	int numSides; // 변의 수
	int sideLength; // 한 변의 길이
	double interiorAngle; // 내각
	double apothem; // 변과 중점을 잇는 선분의 길이

	RegularPolygon() {
		this.name = "정다각형";
	}

	Scanner scanner = new Scanner(System.in);

	@Override
	public void inputSize() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("정다각형의 변의 수: ");
		String inputText = scanner.nextLine();
		this.numSides = Integer.parseInt(inputText);
		System.out.println("정다각형의 한 변의 길이: ");
		inputText = scanner.nextLine();
		this.sideLength = Integer.parseInt(inputText);
		
		// 내각, 변과 중점을 잇는 선분의 길이 계산
        interiorAngle = (numSides - 2) * Math.PI / numSides; 
        apothem = sideLength / (2 * Math.tan(Math.PI / numSides));
	}

	@Override
	public void calcSize() {
		this.resultSize = 0.5 * this.numSides * this.sideLength * this.apothem;
	}

}