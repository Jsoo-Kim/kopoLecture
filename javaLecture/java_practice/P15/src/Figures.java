import java.util.Scanner;

public class Figures {
	
	public void printFigure1() {
		for (int i = 1; i < 6; i++) {
			for (int j = 1; j < 6; j++) {
				System.out.print(i);
			}
			System.out.println();
		}
	}

	public void printFigure2() {
		for (int i = 1; i < 6; i++) {
			for (int j = 1; j < i + 1; j++) {
				System.out.print(j);
			}
			System.out.println();
		}
	}

	public void printFigure3() {
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j < i; j++) {
				System.out.print(" ");
			}
			for (int k = 9; k >= i; k--) {
				System.out.print(i);
			}
			System.out.println();
		}
	}

	public void printFigure4() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < i + 1; j++) {
				System.out.print(" ");
			}
			for (int k = 2 * (5 - i) - 1; k > 0; k--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	public void printFigure5() {
		Scanner s = new Scanner(System.in);
		System.out.println("숫자를 입력해주세요.");
		int inputNum = s.nextInt();

		for (int i = 1; i < inputNum + 1; i++) {
			for (int j = 1; j < i + 1; j++) {
				System.out.print(i);
			}
			System.out.println();
		}
	}
}
