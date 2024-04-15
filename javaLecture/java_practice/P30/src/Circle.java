import java.util.Scanner;

public class Circle extends Base{
	int radius;
	
	Circle() {
		this.name = "원";
	}
	
	@Override
	public void inputSize() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("원의 Radius: ");
		String inputText = scanner.nextLine();
		this.radius = Integer.parseInt(inputText);
	}
	
	@Override
	public void calcSize() {
		this.resultSize = Math.pow(radius, 2) * Math.PI;
	}
}
