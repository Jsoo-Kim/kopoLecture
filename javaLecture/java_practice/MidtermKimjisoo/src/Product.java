import java.util.Scanner;

public class Product {
	public String name;
	public int price;
	public int stock;
	public double discountRate;
	public ProductList productList;
	
	// 여기서 상품 등록
	public Product(ProductList productList) {
		Scanner scanner = new Scanner(System.in);
	    try {
	        System.out.println("등록할 상품명을 입력하세요: ");
	        String inputName = scanner.nextLine();
	        // 상품명 중복 체크
	        for (Product product : productList.productList) {
	            if (product.name.equals(inputName)) {
	                System.out.println("이미 존재하는 상품명입니다.");
	                return;
	            }
	        }
	        System.out.println("등록할 상품 가격을 입력하세요: ");
	        int inputPrice = Integer.parseInt(scanner.nextLine());
	        System.out.println("등록할 상품 재고를 입력하세요: ");
	        int inputStock = Integer.parseInt(scanner.nextLine());
	        System.out.println("등록할 상품의 할인율을 입력하세요: ");
	        double inputDiscountRate = Double.parseDouble(scanner.nextLine());

	        this.name = inputName;
	        this.price = inputPrice;
	        this.stock = inputStock;
	        this.discountRate = inputDiscountRate;
	    } catch (NumberFormatException e) {
	        System.out.println("잘못된 입력 형식입니다. 다시 시도해주세요.");
	    }
	}
	
    @Override
    public String toString() {
        return "상품명: " + name + "\n가격: " + price + "\n재고: " + stock + "\n할인율: " + discountRate + "\n-----------------------";
    }
	
}
