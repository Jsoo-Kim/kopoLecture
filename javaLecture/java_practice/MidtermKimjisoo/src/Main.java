import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	ProductList productList = new ProductList();
    	OrderList orderList = new OrderList();
    	
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 999999; i++) {
            System.out.println("1. 상품 등록");
            System.out.println("2. 상품 목록 출력");
            System.out.println("3. 상품 통계");
            System.out.println("4. 정렬된 상품 목록 출력");
            System.out.println("5. 상품 판매");
            System.out.println("6. 판매 목록 출력");
            System.out.println("7. 상품 입고");
            System.out.println("8. 판매서 수정");
            System.out.println("q. 종료하기");
            System.out.print("메뉴를 선택하세요: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": // 상품 등록
                	Product product = new Product(productList);
                	if (product.name != null) {
                		productList.addProduct(product);
                	}
                    break;
                case "2": // 상품 목록 출력
                	productList.printProductList();
                    break;
                case "3": // 상품 통계
                	productList.printProductStatics();
                    break;
                case "4": // 정렬된 상품 목록 출력
                	System.out.println("1) 높은 가격 순으로 정렬");
                	System.out.println("2) 낮은 가격 순으로 정렬");
                	System.out.println("3) 할인율이 높은 순으로 정렬");
                	System.out.println("4) 할인율이 낮은 순으로 정렬");
                	System.out.print("메뉴를 선택하세요: ");
                	String sortChoice = scanner.nextLine();
                	
                    switch (sortChoice) {
                    case "1":
                        productList.printByHighestPrice();
                        break;
                    case "2":
                    	productList.printByLowestPrice();
                        break;
                    case "3":
                    	productList.printByHighestDiscountRate();
                        break;
                    case "4":
                        productList.printByLowestDiscountRate();
                        break;
                    default:
                        System.out.println("올바른 메뉴를 선택해주세요.");
                        break;
                    }
                    break;
                case "5": // 상품 판매
                	Order order = new Order(productList.productList);
                	if (order.isOrderCompleted) {
                		orderList.addOrder(order);
                	}
                	break;
                case "6": // 판매 목록 출력
                	orderList.printOrderList();
                	break;
                case "7": // 상품 입고
                	productList.addStock();
                	break;
                case "8": // 판매서 수정
                	orderList.updateContactInfo();
                	break;
                case "q":
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("올바른 메뉴를 선택해주세요.");
            }
        }
    }
}
