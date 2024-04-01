import java.util.Random;
import java.util.Scanner;

public class DiceGame {
	int userDice;
	int computerDice;
	String result;
	
	DiceGame() {
		this.rollComputerDice();
		this.rollUserDice();
	}
	
	public void rollComputerDice() {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		
		System.out.println("컴퓨터의 주사위를 굴립니다. 진행하시려면 엔터를 눌러주세요");
		scanner.nextLine();
		this.computerDice = random.nextInt(6) + 1;
		System.out.println("컴퓨터 주사위 숫자: " + computerDice);
	}

	public void rollUserDice() {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		
		System.out.println("사용자의 주사위를 굴립니다. 진행하시려면 엔터를 눌러주세요");
		scanner.nextLine();
		this.userDice = random.nextInt(6) + 1;
		System.out.println("사용자 주사위 숫자: " + userDice);
	}
	
	public String getResult() {
		if (userDice > computerDice) {
			this.result = "Win";
		} else if (userDice == computerDice) {
			this.result = "Draw";
		} else {
			this.result = "Lose";
		}
		return this.result;
	}
	
}
