
public class RunClass {
	public static void main(String[] args) {
		
		// 주사위 100개 생성하고 주사위 값의 평균과 각 주사위 값들의 개수 (전체 개수와 대비) 구하기!
		Dice[] dices = new Dice[100];
		int[] counts = new int[6]; 
		int totalDiceNum = 0;
		
		for (int i = 0; i < dices.length; i++) {
		    dices[i] = new Dice();
		    int diceNum = dices[i].getDiceNum();
		    totalDiceNum += diceNum;
		    counts[diceNum - 1]++; // 주사위 값에 해당하는 인덱스의 카운트 증가
		}
		
		double avgDiceNum = totalDiceNum / (double) dices.length;

		System.out.println("주사위 값의 평균: " + avgDiceNum);
		for (int i = 0; i < counts.length; i++) {
		    System.out.println((i + 1) + "의 값을 가진 주사위: " + counts[i] + 
		    		" / 전체 대비 비율: " +  Math.round(( counts[i] / (double) dices.length * 100 )) + "%");
		}
		
				
//		// toString: Dice 클래스에 toString 메소드가 없으면 그냥 객체 주소가 출력됨!!
//		for (Dice dice : dices) {
//			System.out.print(dice);
//		}
	}
}
