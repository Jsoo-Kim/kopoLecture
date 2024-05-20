import java.util.Arrays;

public class MainClass {
	static long[] memo;
	
	public static void main(String[] args) {
		memo = new long[101];
		System.out.println(fibonacci(100));
		
		System.out.println(mySum(1));
		
		
	}

	public static long fibonacci(int n) { // 피보나치 수열: 어떤 수는 그 전값과 전전값의 합이다
		
		if (n <= 1) {
			return n;
		} else if (memo[n] != 0) {
			return memo[n];
		}
		else {
			return memo[n] = fibonacci(n-1) + fibonacci(n-2);
		}
	}
	
	public static int recursiveSum(int n) { // 1부터 n까지의 합 - 내 코드
		if (n == 0) {
			return 0;
		} else {
			return n + recursiveSum(n - 1);
		}
	}
	
	public static int mySum(int n) { // n부터 10까지의 합 - 교수님 코드
		if (n == 10) {
			return 10;
		} else {
			return n + mySum(++n);			
		}
	}

}
