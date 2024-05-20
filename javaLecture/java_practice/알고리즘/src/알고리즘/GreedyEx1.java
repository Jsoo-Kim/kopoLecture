package 알고리즘;
import java.util.Arrays;
import java.util.Scanner;

public class GreedyEx1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 동전 종류 개수
		int K = sc.nextInt(); // 만들어야 하는 값

		Integer[] moneyList = new Integer[N];
		for (int i = 0; i < N; i++) {
			moneyList[i] = sc.nextInt(); // [10, 50, 100, 500]
		}
		
		Arrays.sort(moneyList, (a, b) -> {
			return b - a;
		});

		int count = 0;
		for (int i = N - 1; i >= 0; i--) {
			if (K % moneyList[i] != 0) {
				count += K / moneyList[i];
				K = K % moneyList[i];
			} else {
				count += K / moneyList[i];
				break;
			}
		}

		System.out.println(count);
	}
}
