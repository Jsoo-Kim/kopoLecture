
public class PigBox {
	String name;
	int amount;
	
	PigBox() { // new를 통해서 생성될 때 실행
		this.name = "돼지저금통";
	}

	PigBox(String name) { 
		this.name = name; // this는 이 객체(PigBox 객체)를 뜻한다
	}
	
	public void deposit(int money) {
		this.amount += money; //
		System.out.println(this.name + "에 " + money + " 입금");
	}
	
	public void withdraw(int money) {
		System.out.println(this.name + "에 " + "출금 금액: " + money);
		this.amount -= money;
	}
	
	public void getAmount() {
		System.out.println(this.amount);
	}

}
