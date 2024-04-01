import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 배열에 담기
		int[][] arr = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		// 정렬
		MyCompare mc = new MyCompare();
		Arrays.sort(arr, mc);
		
		// 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		
	}
}

class MyCompare implements Comparator<int[]> {
	@Override
	public int compare(int[] a, int[] b) {
		int result = Integer.compare(a[0], b[0]); // a[0] - b[0] 로 해도 됨
		if (result == 0) {
			return Integer.compare(a[1], b[1]);
		}
		return result;
	}
}