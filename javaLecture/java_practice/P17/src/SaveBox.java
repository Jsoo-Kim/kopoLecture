
public class SaveBox {
	
	int amount = 0; // 프로퍼티 property
	
	public void deposit(long money) { // 메소드 method
		amount += money;
	}
	
	public void showAmount() {
		System.out.println("현재 잔고: " + amount);
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
