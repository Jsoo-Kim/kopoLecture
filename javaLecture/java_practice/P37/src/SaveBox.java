
public class SaveBox {
	String name;
	int amount;

	SaveBox(String name) {
		this.name = name;
	}

	public void deposit(int coin) {
		this.amount = this.amount + coin;
	}

	// 1. HashSet은 요소를 저장할 때 각 요소의 해시 코드를 기반으로 저장 위치를 결정
//	@Override
//	public int hashCode() {
//		return name.hashCode();
//	}
	// 1-1. 해시코드를 이렇게도 할 수 있음
	@Override
	public int hashCode() {
		return this.name.length();
	}

	// 2. HashSet에 이미 같은 hashCode가 존재하면 equals() 메서드를 호출하여 두 객체가 실제로 같은지 확인
	@Override
	public boolean equals(Object obj) { 
		return this.name.equals(((SaveBox) obj).name);
	}

	@Override
	public String toString() {
		return "" + this.name + "(" + this.amount + ")";
	}
}
