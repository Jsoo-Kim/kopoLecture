import java.util.Random;
import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {		
		
		Scanner s = new Scanner(System.in);
		Random r = new Random();
		
		
//		while (true) {
//			// 사용자 주사위
//			System.out.println("주사위를 굴릴까요? YES: enter / 종료: q 입력");
//			String userInput = s.nextLine();
//			if (userInput.equals("q")) {
//				break;
//			}
//			int myDiceNum  = r.nextInt(6) + 1;
//			System.out.println("내 주사위 번호: " + myDiceNum);
//			
//			// 컴퓨터 주사위
//			System.out.println("컴퓨터가 주사위를 굴립니다. 실행할까요? YES: enter / 종료: q 입력");
//			userInput = s.nextLine();
//			if (userInput.equals("q")) {
//				break;
//			}
//			int computerDiceNum  = r.nextInt(6) + 1;
//			System.out.println("컴퓨터의 주사위 번호: " + computerDiceNum);
//			
//			// 대전 결과
//			if (myDiceNum > computerDiceNum) {
//				System.out.println("\nYou Win!");
//			} else if (myDiceNum < computerDiceNum) {
//				System.out.println("\nYou Lose!");
//			} else {
//				System.out.println("\nDraw");
//			}
//		}
//		System.out.println("대전이 종료되었습니다.");
		
		
		// 위 코드에 게임 히스토리까지 추가
		String[] gameHistory = new String[99999];
		int gameCnt = 0;
		int userWinCnt = 0;
		
		for (int i = 0; i < 99999; i++) {
			System.out.println();
			// 사용자 주사위
			System.out.println("\n주사위를 굴릴까요? YES: enter / 종료: q 입력 / 히스토리 출력: h 입력");
			String userInput = s.nextLine();
			if (userInput.equals("q")) {
				break;
			} else if (userInput.equals("h")) { // 히스토리 출력
				for (int j = 0; j < gameCnt; j++) {
					if (gameHistory[j].equals("Win")) {
						System.out.println("" + (j + 1) + "번째 게임은 사용자가 이겼습니다.");
					} else if (gameHistory[j].equals("Lose")) {
						System.out.println("" + (j + 1) + "번째 게임은 컴퓨터가 이겼습니다.");
					} else {
						System.out.println("" + (j + 1) + "번째 게임은 비겼습니다.");
					}
				}
			}
			int myDiceNum  = r.nextInt(6) + 1;
			System.out.println("내 주사위 번호: " + myDiceNum);
			
			// 컴퓨터 주사위
			System.out.println("\n컴퓨터가 주사위를 굴립니다. 실행할까요? YES: enter / 종료: q 입력");
			userInput = s.nextLine();
			if (userInput.equals("q")) {
				break;
			} else if (userInput.equals("h")) { // 히스토리 출력
				for (int j = 0; j < gameCnt; j++) {
					if (gameHistory[j].equals("Win")) {
						System.out.println("" + (j + 1) + "번째 게임은 사용자가 이겼습니다.");
					} else if (gameHistory[j].equals("Lose")) {
						System.out.println("" + (j + 1) + "번째 게임은 컴퓨터가 이겼습니다.");
					} else {
						System.out.println("" + (j + 1) + "번째 게임은 비겼습니다.");
					}
				}
			}
			int computerDiceNum  = r.nextInt(6) + 1;
			System.out.println("컴퓨터의 주사위 번호: " + computerDiceNum);
			
			// 대전 결과
			if (myDiceNum > computerDiceNum) {
				System.out.println("\nYou Win!");
				userWinCnt++;
				gameHistory[i] = "Win";
			} else if (myDiceNum < computerDiceNum) {
				System.out.println("\nYou Lose!");
				gameHistory[i] = "Lose";
			} else {
				System.out.println("\nDraw");
				gameHistory[i] = "Draw";
			}
			gameCnt++;
			// 10000번의 게임 중 1번만 이겼다면, 승률은 0.01% 이기 때문에 굳이 10000을 곱하고 다시 100으로 나눈 것!
			double winRate = Math.round(userWinCnt / (double) gameCnt * 10000) / (double) 100;
			System.out.println("사용자의 현재 승률은 " + winRate + "% 입니다.");
		}
		System.out.println("대전이 종료되었습니다.");
	}
}