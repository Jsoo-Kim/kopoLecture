
public class MainClass {
	public static void main(String[] args) {
//		// for문 10번 반복
//		for (int i = 0; i < 10; i++) {
//			// 10번 반복
//		}
//
//		
//		// while문 10번 반복
//		int j = 0;
//		while (j < 10) {
//			// 10번 반복
//			j++;
//		}
//		
//
//		// while문 무한루프
//		while (true) { // 조건만 있는 반복문일 경우 while 사용
//			// 무한루프
//		}
//		
//
//		// do-while 문
//		do {
//			// 반드시 1번은 실행
//		} while (false) {
//			
//		};
//		
		
		// break
		for (int i = 0; i < 10; i++) {
			// 10번 반복
			if (i == 3) {
				break; // i가 3이면 반복문 종료
			}
			System.out.println(i);
		}
		System.out.println("END");
		
		
		// continue
		for (int i = 0; i < 10; i++) {
			// 10번 반복
			if (i == 3) {
				continue; // i가 3이면 아래 코드 실행 안 되고(i 출력 안 되고) 다음 회차로 넘어감
			}
			System.out.println(i);
		}
		System.out.println("END");
		
		
		// return
		for (int i = 0; i < 10; i++) {
			// 10번 반복
			if (i == 3) {
				return; // 아예 프로그램 종료 (main에서 실행되는 것 자체가 종료)
			}
			System.out.println(i);
		}
		System.out.println("END");
	}
}
