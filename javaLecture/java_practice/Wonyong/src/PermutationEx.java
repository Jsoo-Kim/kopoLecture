class PermutationEx {

	public static void main(String args[]) {
		String str = "ABCDE";
		int n = str.length();
		char[] arr = str.toCharArray();
		boolean visited[] = new boolean[n];
		for (int i = 0; i < n; i++) {
			visited[i] = false;
		}
		char[] branch = new char[n];
		generatePermutations(arr, n, branch, -1, visited);
		System.out.println("----");
		char[] output = new char[n];
		combination(arr, 3, 0, output, 0);
		System.out.println("----");
		powerset(arr);
	}

	static void generatePermutations(char[] arr, int size, char[] branch, int level, boolean[] visited) {
		if (level >= size - 1) {
			System.out.println(branch);
			return;
		}

		for (int i = 0; i < size; i++) {
			if (!visited[i]) {
				branch[++level] = arr[i];
				visited[i] = true;
				generatePermutations(arr, size, branch, level, visited);
				visited[i] = false;
				level--;
			}
		}
	}

	static void combination(char[] arr, int pick, int startIdx, char[] output, int numElem) {
		if (numElem == pick) {
			// System.out.println(Arrays.toString(output));
			System.out.println(output);
			return;
		}

		for (int i = startIdx; i < arr.length; i++) {
			output[numElem++] = arr[i];
			combination(arr, pick, ++startIdx, output, numElem);
			--numElem;
		}
	}

	static void powerset(char[] arr) {
		for (int i = 0; i < arr.length; i++) {
			char[] branch = new char[i];
			combination(arr, i, 0, branch, 0);
		}
	}
}