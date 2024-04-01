import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

// 이건 코테 전에 꼭 외워야 함!!
public class ArraysEx {
	public static void main(String[] args) {

		// 1번 방법
//		int[] array = {10, 3, 53, 100, 0, 1};
		Integer[] array1 = {10, 3, 53, 100, 0, 1};
		
		Arrays.sort(array1, Collections.reverseOrder()); // Arrays.sort는 primitive 자료형은 다루지 않음
		System.out.println(Arrays.toString(array1));
		
		
		// 2번 방법 - 클래스 별도 선언
		Integer[] array2 = {10, 3, 53, 100, 0, 1};
		
		myCompareClass mcc = new myCompareClass();
		Arrays.sort(array2, mcc);
		System.out.println(Arrays.toString(array2));
		
		
		// 3번 방법 - 객체 선언 및 오버라이딩
		Integer[] array3 = {10, 3, 53, 100, 0, 1};
		
		Comparator<Integer> myCompare = new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return a - b;
			}
		};
		Arrays.sort(array3, myCompare);
		System.out.println(Arrays.toString(array3));
		
		
		// 4번 방법 - 익명 객체 (클래스도 싫고 변수도 싫고 그냥 바로 안에 넣어버리기)
		Integer[] array4 = {10, 3, 53, 100, 0, 1};
		
		Arrays.sort(array4, new Comparator<Integer>() { // Comparator<? super T> => ex) Comparator<Number>는 Number 타입과 그 하위 타입인 Integer, Double 등을 모두 비교할 수 있다.
			public int compare(Integer a, Integer b) {
				return b - a;
			}
		});
		System.out.println(Arrays.toString(array4));
		
		
		// 5번 방법 - 람다식
		Integer[] array5 = {10, 3, 53, 100, 0, 1};
		
		Arrays.sort(array5, (a, b) -> {
			return b - a;
		});
		System.out.println(Arrays.toString(array5));
		
		
	}
}

class myCompareClass implements Comparator<Integer> {
	// @Override 생략되어 있음
	public int compare(Integer a, Integer b) {
//		return a - b;
		return b - a;
	}
}