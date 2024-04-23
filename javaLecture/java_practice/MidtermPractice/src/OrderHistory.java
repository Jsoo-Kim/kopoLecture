import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private List<Order> orders;

    // 생성자
    public OrderHistory() {
        this.orders = new ArrayList<>();
    }


    // 주문 내역 출력 메서드
    public void printAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("주문 내역이 없습니다.");
        } else {
            System.out.println("전체 주문 내역:");
            for (Order order : orders) {
                order.printOrderDetails();
                System.out.println();
            }
        }
    }

}