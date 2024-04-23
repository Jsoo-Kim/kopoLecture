import java.util.HashMap;
import java.util.Map;

public class Order {
    static int orderIdCounter = 1;

    int orderId;
    Map<Product, Integer> products; // Map<Integer, Integer>로 바꿔야 할 수도? 상품아이디, 주문수량
    String shippingAddress;

    // 생성자
    public Order() {
        this.orderId = orderIdCounter++;
        this.products = new HashMap<>();
    }


    // 상품 구매 메서드
    public void addOrder(Product product, int quantity) {
    	if (quantity > 0 && quantity <= product.stock) {
    		 product.stock -= quantity;
             System.out.println(product.name + " 상품 " + quantity + "개를 구매하였습니다.");
             products.put(product, quantity);
    	} else {
    		 System.out.println("재고가 부족합니다.");
    	}
    }
    
    // 배송지 입력 메서드 추가!

    
    // 상품 구매 취소 메서드
    public void cancelOrder(Product product, int cancelQuantity) {
        if (products.containsKey(product) && cancelQuantity <= products.get(product)) {
        	int remainingQuantity = products.get(product) - cancelQuantity;
        	
        	if (remainingQuantity <= 0) {
        		products.remove(product);
        	} else {
        		products.put(product, remainingQuantity);
        	}
            product.stock += cancelQuantity;
            
            System.out.println("주문이 취소되어 " + product.name + " 상품 " + cancelQuantity + "개가 재고에 추가되었습니다.");
        } else if (products.containsKey(product) && cancelQuantity > products.get(product)) {
        	System.out.println("취소 수량이 기존 주문 수량을 초과합니다.");
        } else if (!products.containsKey(product)){
        	System.out.println("해당 상품이 주문 목록에 없습니다.");
        }
    }
    

    // 주문 정보 출력 메서드
    public void printOrderDetails() {
    	System.out.println("주문 번호: " + orderId);
    	System.out.println("배송지: " + shippingAddress);
    	System.out.println("주문 상품 정보:");
    	for (Map.Entry<Product, Integer> entry : products.entrySet()) {
    		Product product = entry.getKey();
    		int quantity = entry.getValue();
    		System.out.println("상품명: " + product.name + ", 주문 수량: " + quantity);
    	}
    }
    
}

