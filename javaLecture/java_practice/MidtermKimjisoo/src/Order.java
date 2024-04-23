import java.util.List;
import java.util.Scanner;

public class Order {
	public String productName;
	public String buyerName;
	public String phoneNumber;
	public String shippingAddress;
	public OrderList orderList;
	public boolean isOrderCompleted = false;
	
	// 상품 판매 (품절 처리도 여기에서)
	public Order(List<Product> productList) {
	    Scanner scanner = new Scanner(System.in);
	    try {
	        System.out.println("판매할 상품명을 입력하세요: ");
	        String inputProductName = scanner.nextLine();
	        for (Product product : productList) {
	            if (product.name.equals(inputProductName) && product.stock >= 1) {
	                product.stock -= 1;

	                System.out.println("구매자 이름을 입력하세요: ");
	                String inputBuyerName = scanner.nextLine();
	                System.out.println("구매자 연락처를 입력하세요: ");
	                String inputPhoneNumber = scanner.nextLine();
	                System.out.println("배송지 주소를 입력하세요: ");
	                String inputShippingAddress = scanner.nextLine();

	                this.productName = inputProductName;
	                this.buyerName = inputBuyerName;
	                this.phoneNumber = inputPhoneNumber;
	                this.shippingAddress = inputShippingAddress;
	                this.isOrderCompleted = true;

	                return;
	            } else if (product.name.equals(inputProductName) && product.stock <= 0) {
	                System.out.println("품절된 상품입니다.");
	                return;
	            }
	        }
	        System.out.println("해당하는 상품명이 존재하지 않습니다.");
	    } catch (NumberFormatException e) {
	        System.out.println("잘못된 입력 형식입니다. 다시 시도해주세요.");
	    }
	}

  }
