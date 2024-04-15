import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		ClassRoom a = new ClassRoom("A반");
		ClassRoom b = new ClassRoom("B반");
		ClassRoom c = new ClassRoom("C반");
		
		a.setStudents(50);
		b.setStudents(49);
		c.setStudents(40);

//		System.out.println(Arrays.toString(b.students));
//		
//		Student s = new Student();
//		System.out.println(s); // null (중간: 0, 기말: 0, 총합: 0) toString 메서드를 만들어놔서 바로 찍힘
//		System.out.println(c.toString()); //ClassRoom@1175e2db
//		System.out.println(c); //ClassRoom@1175e2db
//		
//		System.out.println(s.getName()); // 접근제어자 private 으로 지정해서 직접 접근 안 됨
//		System.out.println(s.middleScore);
//		
//		Test test = new Test();
//		test.t1();
		
		Test.t1(); // T1
		Test.t1("sdfsdsfsf"); // sdfsdsfsf
		Test.t1(10); // 10 (int로 갈지 double로 갈지 miss 날 때 있음)
		Test.t1(10.0); // 10.0
		Test.t1(20, 30); // 50.0
		
	}
}
