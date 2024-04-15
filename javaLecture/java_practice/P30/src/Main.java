
public class Main {
	public static void main(String[] args) {
		Base triangle = new Triangle(); // Base 타입인데 실제 들어 있는 객체는 Triangle
		triangle.doAction();
		
		Base rectangle = new Rectangle();
		rectangle.doAction();
		
		Base circle = new Circle();
		circle.doAction();
		
		Base trapezoid = new Trapezoid();
		trapezoid.doAction();
		
		Base regularPolygon = new RegularPolygon();
		regularPolygon.doAction();
	}
}
