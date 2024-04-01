
public class Animal {
	String name;
	
	Animal() {	
	}
	
	Animal(String name) {
		this.name = name;
	}
	
	public void cuteAction() {
		if (this.name.equals("고양이")) {
			System.out.println(this.name + ": 냥냥펀치!");
		} else if (this.name.equals("강아지")) {
			System.out.println(this.name + ": 꼬리를 흔든다!");
		} else if (this.name.equals("토끼")) {
			System.out.println(this.name + ": 귀 쫑긋!");
		} else {
			System.out.println("고양이/강아지/토끼 중 하나를 입력해주세요.");
		}
	}

	public void stroke() {
		System.out.println(this.name + "를/을 쓰다듬었다.");
		
		if (this.name.equals("고양이")) {
			System.out.println(this.name + ": 야옹");
		} else if (this.name.equals("강아지")) {
			System.out.println(this.name + ": 멍멍");
		} else if (this.name.equals("토끼")) {
			System.out.println(this.name + ": 무시한다.");
		} else {
			System.out.println("고양이/강아지/토끼 중 하나를 입력해주세요.");
		}
	}
}
