import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {		
		Scanner s = new Scanner(System.in);
		Figures fg = new Figures();

		for (int i = 0; i < 99999; i++) {
			System.out.println("----------------------------------------------");
			System.out.println("1부터 5까지의 번호 중 원하는 도형의 번호를 입력하세요. ex) 1번(X) 1(O) / 종료: q 입력");
			String userInput = s.nextLine();

			if (userInput.equals("1")) {
				fg.printFigure1();
			} else if (userInput.equals("2")) {
				fg.printFigure2();
			} else if (userInput.equals("3")) {
				fg.printFigure3();
			} else if (userInput .equals("4")) {
				fg.printFigure4();
			} else if (userInput.equals("5")) {
				fg.printFigure5();
			} else if (userInput.equals("q")) {
				System.out.println("종료되었습니다.");
				break;
			} else {
				System.out.println("잘못된 번호를 입력하였습니다.");
			}
		}
	}	
}