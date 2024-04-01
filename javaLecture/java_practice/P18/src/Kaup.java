import java.util.Scanner;

public class Kaup {
	double weight;
	double height;

	public void receiveInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("체중(kg)을 입력하세요");
		weight = Double.parseDouble(scanner.nextLine());
		System.out.println("신장(m)을 입력하세요");
		height = Double.parseDouble(scanner.nextLine());
	}
	
	public void getKaupIndex() {
		System.out.println("카우프 지수: " + (weight / Math.pow(height, 2)));
	}
}
