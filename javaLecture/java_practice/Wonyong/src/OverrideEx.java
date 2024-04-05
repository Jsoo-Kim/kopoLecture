
public class OverrideEx {

	public static void main(String[] args) {
		Animal animal = new Animal();
		Animal dog = new Dog();
		Animal cat = new Cat();
		Animal kitty = new Kitty();
		
		animal.makeSound();
		dog.makeSound();
		cat.makeSound(); 
		kitty.makeSound();
		
	}
	
}

class Animal {
	public void makeSound() {
		System.out.println("동물이 소리를 냅니다.");
	}
}

class Cat extends Animal {
	@Override // 이 어노테이션은 생략해도 됨
	public void makeSound() {
		super.makeSound();
		System.out.println("야옹");
	}
}

class Kitty extends Cat {
	public void makeSound() {
		System.out.println("미야옹");
	}
}

class Dog extends Animal {
	@Override // 이 어노테이션은 생략해도 됨
	public void makeSound() {
		System.out.println("멍멍");
	}	
}
