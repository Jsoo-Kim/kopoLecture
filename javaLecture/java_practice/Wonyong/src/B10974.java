import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

class B10974 {

	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer str = new StringBuffer();
//		Scanner sc = new Scanner(System.in);
//		int n = sc.nextInt();
		int n = Integer.parseInt(br.readLine());
		boolean visited[] = new boolean[n];
		for (int i = 0; i < n; i++) { // 초기화
			str.append(i + 1);
			visited[i] = false;
		}
		char[] arr = str.toString().toCharArray();
		char[] branch = new char[n];
		generatePermutations(arr, n, branch, -1, visited);

	}

	static void generatePermutations(char[] arr, int size, char[] branch, int level, boolean[] visited) {

		if (level >= size - 1) {
			for (int i = 0; i < branch.length; i++) {
				System.out.print(branch[i] + " ");
			}
			System.out.println();
			return;
		}

		for (int i = 0; i < size; i++) { // size = 5
			if (!visited[i]) { // visited[0] = false -> visited[1] = false
				branch[++level] = arr[i]; // level =0 branch[0] = arr[0]
				visited[i] = true; // branch[0] = A , visited[0] = true;
				generatePermutations(arr, size, branch, level, visited);
				visited[i] = false;
				level--;
			}
		}
	}

}