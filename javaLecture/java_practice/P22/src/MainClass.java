
public class MainClass {
	public static void main(String[] args) {
		Animal cat = new Animal("고양이");
		Animal dog = new Animal("강아지");
		Animal rabbit = new Animal("토끼");
		Animal horse = new Animal("말");
		
		cat.cuteAction();
		dog.cuteAction();
		rabbit.cuteAction();
		
		cat.stroke();
		dog.stroke();
		rabbit.stroke();
		
		horse.cuteAction();
		horse.stroke();
	}
}
