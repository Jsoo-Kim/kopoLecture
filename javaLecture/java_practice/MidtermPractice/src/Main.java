import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderHistory orderhistory = new OrderHistory();
        Order order = new Order();

        while (true) {
            System.out.println("1. 주문하기");
            System.out.println("2. 주문 내역 확인하기");
            System.out.println("3. 주문 취소하기");
            System.out.println("q. 종료하기");
            System.out.print("메뉴를 선택하세요: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                   
                    break;
                case "2":
                	
                    break;
                case "3":
                	
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