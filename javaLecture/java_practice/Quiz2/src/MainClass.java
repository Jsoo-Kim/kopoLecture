import java.io.IOException;
import java.util.Arrays;

public class MainClass {
	public static void main(String[] args) throws NumberFormatException, IOException {

		int arr[][] = { { 10, 33, 44 }, { 27, 5, 98 } };
		int arr2[][] = new int[3][4];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr2[i][j] = arr[i][j];
			}
		}

		for (int j = 0; j < arr[0].length; j++) {
			arr2[0][3] += arr2[0][j];
			arr2[1][3] += arr2[1][j];
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr2[2][j] += arr2[i][j];
			}
		}
		

		double avg1 = arr2[0][3] / 3.0;
		double avg2 = (arr2[1][3] / 3.0);

		// 여기부터 출력
		System.out.println("Name     Quiz1  Quiz2  Quiz3  Sum  Avg");
		System.out.println("------------------------------------------------");
		System.out.print("Student1");
		for (int i = 0; i < arr2[0].length; i++) {
			System.out.printf("%6d", arr2[0][i]);
		}
		System.out.print("   " + avg1);
		System.out.println();
		System.out.print("Student2");
		for (int i = 0; i < arr2[0].length; i++) {
			System.out.printf("%6d", arr2[1][i]);
		}
		System.out.print("   " + avg2);
		System.out.println();
		System.out.println("------------------------------------------------");
		System.out.print("tot     ");
		for (int i = 0; i < arr2.length; i++) {
			System.out.printf("%6d", arr2[2][i]);
		}

	}

}
