import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		System.out.println(Math.random()); // 0부터 1 미만(0.9999...)까지만 표시
		
		// 0 ~ 99
		double randNum = Math.floor(Math.random() * 100); // floor는 내림 
		
		// 0 ~ 4
		int randNum2 = (int) Math.random() * 5; // 이렇게도 가능
		
		
		// Wrapper Class
//		ArrayList<int> a1 = new ArrayList<>(); // 에러! 제네릭은 객체 자료형만 받음
		
		// 오토박싱
		Integer num = 10; // int 타입의 10이 Integer 객체로 자동 박싱되어 할당됨
		
		// 오토언박싱
		int num2 = num; // Integer 객체가 int로 자동 언박싱되어 할당됨
		
		
		// Formatter
		System.out.printf("%10d", num);
		String t1 = String.format("%10d", num); // 이렇게 문자열로도 만들 수 있음
		System.out.println(t1);
		
		// %o : 8진수 / %h : 16진수 / %3.2f : 실수 소수점 앞은 3자리, 소수점 부분은 2자리
		
	}
}
