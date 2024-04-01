
public class RunClass {
	public static void main(String[] args) {
		SaveBox s1 = new SaveBox();
		SaveBox s2 = new SaveBox();
		// s1과 s2은 서로 다른 객체! 안에 들어 있는 돈(내부값 = 프로퍼티)도 다름!
		
		s1.deposit(100);
		s1.deposit(1000);
		s1.deposit(20);

		s2.deposit(500);
		s2.deposit(50);
		
		s1.showAmount();
		s2.showAmount();
		
	}
}
