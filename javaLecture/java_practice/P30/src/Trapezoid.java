import java.util.Scanner;

public class Trapezoid extends Rectangle{
	int upperLength;
	int lowerLength;
	int height;
	
	Trapezoid() {
		this.name = "사다리꼴";
	}
	
	@Override
	public void inputSize() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("사다리꼴의 upperLength: ");
		String inputText = scanner.nextLine();
		this.upperLength = Integer.parseInt(inputText);
		System.out.println("사다리꼴의 lowerLength: ");
		inputText = scanner.nextLine();
		this.lowerLength = Integer.parseInt(inputText);
		System.out.println("사다리꼴의 Heigt: ");
		inputText = scanner.nextLine();
		this.height = Integer.parseInt(inputText);
	}
	
	@Override
	public void calcSize() {
		this.resultSize = this.width * this.height;
	}
}
