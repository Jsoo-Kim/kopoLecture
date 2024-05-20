package 알고리즘;

import java.util.Arrays;
import java.util.Scanner;

public class GreedyEx2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int input = sc.nextInt();
		int numberOfCoin = 0;
		
		
		for (int i = 0; i < 2; i++) {
			if ((input < 5) && (input % 2 != 0)) {
				System.out.println(-1);
				break;
			}
			if (input / 5 != 0) {
				numberOfCoin += input / 5;
				input = input % 5;
			} else if (input / 2 != 0) {
				numberOfCoin += input / 2;
				System.out.println(numberOfCoin);
			}
		}
		
	}
}
