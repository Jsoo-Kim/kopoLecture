import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainClass {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int arrRowCnt = Integer.parseInt(st.nextToken());
        int arrColCnt = Integer.parseInt(st.nextToken());

        int[][] arr = new int[arrRowCnt][arrColCnt];
        inputArray(arr, arrRowCnt, arrColCnt, br);

        int inputCnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < inputCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            int sum = calculateSum(arr, x1, y1, x2, y2);
            System.out.println(sum);
        }
    }

    private static void inputArray(int[][] arr, int rows, int cols, BufferedReader br) throws IOException {
        StringTokenizer st;
        for (int i = 0; i < rows; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < cols; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static int calculateSum(int[][] arr, int x1, int y1, int x2, int y2) {
        int sum = 0;
        if (y1 == y2) {
            for (int x = x1; x <= x2; x++) {
                sum += arr[x][y1];
            }
        } else {
            for (int x = x1; x <= x2; x++) {
                if (x == x1) {
                    for (int y = y1; y < arr[0].length; y++) {
                        sum += arr[x][y];
                    }
                } else {
                    for (int y = 0; y < arr[0].length; y++) {
                        if (x == x2 && y > y2) {
                            break;
                        } else {
                            sum += arr[x][y];
                        }
                    }
                }
            }
        }
        return sum;
    }
}