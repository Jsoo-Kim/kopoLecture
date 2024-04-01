import java.util.Random;

public class Dice {
	int diceNum;
	
	Dice() {
		this.rollDice();
	}
	
	public void rollDice() {
		Random random = new Random();
		this.diceNum = random.nextInt(6) + 1;
	}
	
	public int getDiceNum() {
		return this.diceNum;
	}
	
	// 이게 없으면 출력할 때 객체 주소가 출력됨!
	public String toString() {
		return "" + this.diceNum;
	}
}
