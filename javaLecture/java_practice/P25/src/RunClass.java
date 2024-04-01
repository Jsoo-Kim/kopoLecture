
public class RunClass {
	public static void main(String[] args) {
		DiceGame[] diceGames = new DiceGame[10];
		int[] counts = new int[3]; // 인덱스 0, 1, 2 순으로 Win, Draw, Lose 
		
		for (int i = 0; i < diceGames.length; i++) {
			diceGames[i] = new DiceGame();
			String result =  diceGames[i].getResult();
			System.out.println("" + (i+1) + "번째 게임: " + result);
			System.out.println("---------------------");
			if (result.equals("Win")) {
				counts[0]++;
			}
		}
		System.out.println("승률: " + Math.round((counts[0] / (double) diceGames.length) * 100) + "%");
	}
}
