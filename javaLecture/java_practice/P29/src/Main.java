
public class Main {
	public static void main(String[] args) {
		Test test = new Test();
		test.t1();
//		test.t2(); // 부모 클래스에 없는 메소드이므로 사용 불가

		Test2 test2 = new Test2();
		test2.t1(); // 자식 클래스에 없지만 부모 클래스에 있는 메소드이므로 사용 가능
		test2.t2();
		
	}
}
