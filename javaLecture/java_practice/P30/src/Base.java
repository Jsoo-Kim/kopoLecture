
public abstract class Base {
	protected String name; // protected : 상속 받은 곳에서만 사용 가능
	protected double resultSize;
	
	Base() {
		this.name = "기본 도형";
	}
	public abstract void inputSize();
	
	public abstract void calcSize();
	
	public void printSize() { // 계산 결과 출력
		System.out.println(this.name + "의 영역 크기: " + this.resultSize);
	}
	
	public void doAction() {
		this.inputSize();
		this.calcSize();
		this.printSize();
	}
}
