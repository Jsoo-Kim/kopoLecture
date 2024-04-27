public class Main {
    public static void main(String[] args) {
    	int n = 5;
    	int[][] arr = new int[n][n];
    	int startX = 3; // 시작 x 좌표
    	int startY = 2; // 시작 y 좌표
    	
    	for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                arr[y][x] = y * n + x + 1;
            }
        }

    	for (int dy = -1; dy <= 1; dy++) {
    	    for (int dx = -1; dx <= 1; dx++) {
    	        if (dy == 0 && dx == 0) continue; // 시작 위치는 제외
    	        int x = startX + dx;
    	        int y = startY + dy;
    	        while (x >= 0 && x < n && y >= 0 && y < n) {
    	            System.out.printf("%3d", arr[y][x]);
    	            x += dx;
    	            y += dy;
    	        }
    	    }
    	}
    }
}
