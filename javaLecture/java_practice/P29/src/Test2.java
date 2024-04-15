
public class Test2 extends Test {
	public void t2() {
		System.out.println("T2");
	}
	
//	@Override // 어노테이션 사용 이유: 재정의 메소드 이름 잘못 쓰면 빨간줄로 알려줌
//	public void t12() {
//		System.out.println("T1 오버라이드");
//	}

	@Override // 어노테이션 사용 이유: 재정의 메소드 이름 잘못 쓰면 빨간줄로 알려줌
	public void t1() {
		System.out.println("T1 오버라이드");
	}
}
