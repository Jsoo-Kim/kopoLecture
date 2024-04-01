
public class RunClass {
	public static void main(String[] args) {
		PigBox p1 = new PigBox();
		PigBox p2 = new PigBox("토끼저금통");
		
		p1.deposit(400);
		p2.deposit(200);
		
		p1.withdraw(100);
		p2.withdraw(50);
		
		p1.getAmount();
		p2.getAmount();
		
		PigBox p3 = new PigBox("강아지");
		p3.withdraw(0);
	}
}
