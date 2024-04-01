import java.util.Arrays;

public class MainClass {
	static long[] memo;
	
	public static void main(String[] args) {
		memo = new long[101];
		System.out.println(fibonacci(100));
		
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

}
