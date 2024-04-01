import java.util.Random;

public class DiceGame {
	public void play() {
		Random random = new Random();
		int diceNumber = random.nextInt(6) + 1;
		System.out.println("주사위 숫자: " + diceNumber);
	}
	
	public void play2() {
		System.out.println("hello world");
	}
	
	public void p3() {
		Random random = new Random();
		System.out.println(random.nextInt());
	}
}