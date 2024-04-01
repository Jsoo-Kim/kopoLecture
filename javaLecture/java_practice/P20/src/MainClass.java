import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {	
		
		Scanner scanner = new Scanner(System.in);
		
		
		while (true) {
			
			// 입력받기
			System.out.println("------------------------------------");
			System.out.println("\n실행 시작: enter / 실행 종료: q");
			String startOrNot = scanner.nextLine();
			if (startOrNot.equals("q")) {
				break;
			}
			
			System.out.println("계산할 행, 열의 개수를 입력해주세요. ex) 3 ");
			int inputNum = Integer.parseInt(scanner.nextLine());
			int[][] arr = new int[inputNum + 1][inputNum + 1];
			
			// 입력 받은 대로 이중배열 생성
			for (int i = 0; i < inputNum; i++) {
				for (int j = 0; j < inputNum; j++) {				
					System.out.println("임의의 숫자를 입력해주세요.");
					arr[i][j] = scanner.nextInt();
				}
			}
			scanner.nextLine(); // nextInt()로 입력받은 엔터 뱉어주기
			
			
			// 각 행과 열의 합 넣어주기
			int totalSum = 0;
			int rowSum = 0;
			int colSum = 0;

			for (int i = 0; i < inputNum; i++) {
				rowSum = 0;
				colSum = 0;

				for (int j = 0; j < inputNum; j++) {
					rowSum += arr[i][j];
					colSum += arr[j][i];
				}
				arr[i][inputNum] = rowSum;
				arr[inputNum][i] = colSum;
				totalSum += (rowSum + colSum);
			}

			arr[inputNum][inputNum] = totalSum;

			
			
			// 출력하기 -> 총합 숫자의 자릿수에 맞춰서 정렬!
			int digit = String.valueOf(totalSum).length();
			String format = "%" + (digit + 2) + "d";
			
			for (int[] a : arr) {			
				for (int i : a) {
					System.out.printf(format, i);
				}
				System.out.println();
			}
		}
		System.out.println("실행이 종료되었습니다.");
		
	}			
}
