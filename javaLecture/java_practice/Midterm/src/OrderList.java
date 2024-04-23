import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderList {
	public List<Order> orderList;
	
	public OrderList() {
		this.orderList = new ArrayList<>();
	}
	
	// 판매 내역에 추가 
    public void addOrder(Order order) {
        orderList.add(order);
    }
	
    // 판매 내역서 목록 출력
    public void printOrderList() {
        System.out.println("\n[판매 내역]");
        for (Order order : orderList) {
            System.out.println("상품명: " + order.productName);
            System.out.println("구매자 이름: " + order.buyerName);
            System.out.println("구매자 연락처: " + order.phoneNumber);
            System.out.println("배송지 주소: " + order.shippingAddress);
            System.out.println("-----------------------");
        }
    }
    
    // 판매서 수정
    public void updateContactInfo() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("수정하실 판매 내역서의 상품명을 입력하세요.");
    	String inputProductName = scanner.nextLine();
    	System.out.println("수정하실 판매 내역서의 구매자명을 입력하세요.");
    	String inputBuyerName = scanner.nextLine();
    	System.out.println("변경된 연락처를 입력하세요.");
    	String inputPhoneNumber = scanner.nextLine();
    	System.out.println("변경된 주소를 입력하세요.");
    	String inputShippingAddress = scanner.nextLine();
    	
    	for (Order order : orderList) {
    		if (inputProductName.equals(order.productName) && inputBuyerName.equals(order.buyerName)) {
    			order.phoneNumber = inputPhoneNumber;
    			order.shippingAddress = inputShippingAddress;
    		}
    	}
    	
    }
}
