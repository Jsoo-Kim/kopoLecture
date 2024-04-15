import java.util.Scanner;

public class Rectangle extends Base {
	int width;
	int height;
	
	Rectangle() {
		this.name = "사각형";
	}
	
	@Override
	public void inputSize() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("사각형의 Width: ");
		String inputText = scanner.nextLine();
		this.width = Integer.parseInt(inputText);
		System.out.println("사각형의 Heigt: ");
		inputText = scanner.nextLine();
		this.height = Integer.parseInt(inputText);
	}
	
	@Override
	public void calcSize() {
		this.resultSize = this.width * this.height;
	}
}
