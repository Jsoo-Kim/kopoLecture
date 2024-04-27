
public class SavingBox {
	public String name;
	public int balance;

	public SavingBox(String name, int balance) {
		this.name = name;
		this.balance = balance;
	}

	// 1. HashSet은 요소를 저장할 때 각 요소의 해시 코드를 기반으로 저장 위치를 결정
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	// 2. HashSet에 이미 같은 hashCode가 존재하면 equals() 메서드를 호출하여 두 객체가 실제로 같은지 확인
	@Override
	public boolean equals(Object obj) { // 기존에 존재하는 객체를 파라미터로 받은 듯?
		SavingBox savingBox = (SavingBox) obj; // 전달 받은 객체가 SavingBox 타입인지 확인 후 형 변환
		return (name.equals(savingBox.name));
	}

	@Override
	public String toString() {
		return "" + this.name + "(" + this.balance + ")";
	}

}
