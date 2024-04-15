import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// 상품 목록 생성
		List<Product> productList = new ArrayList<>();
		productList.add(new Product("양말", 3000));
		productList.add(new Product("수건", 5000));
		

		// 구매 내역 생성
		List<Purchase> purchaseList = new ArrayList<>();

		while (true) {
			System.out.println("원하시는 메뉴의 번호 혹은 알파벳을 입력하세요: 1. 구매 / 2. 구매내역 확인 / q. 종료");
			String choice = scanner.nextLine();

			if (choice.equals("1")) {

				System.out.println("[상품 목록]");
				for (int i = 0; i < productList.size(); i++) {
					System.out.println(
							(i + 1) + ". " + productList.get(i).productName + " - " + productList.get(i).price + "원");
				}

				System.out.println("구매하실 상품의 번호를 입력하세요: ");
				int productNum = Integer.parseInt(scanner.nextLine());

				System.out.print("배송지 주소를 입력하세요: ");
				String address = scanner.nextLine();

				Purchase purchase = new Purchase();
				purchase.addProduct(productList.get(productNum - 1));
				purchase.setShippingAddress(address);

				purchaseList.add(purchase);
				System.out.println("상품을 구매하였습니다.");

			} else if (choice.equals("2")) {

				System.out.println("[구매 내역]");
				for (int i = 0; i < purchaseList.size(); i++) {
					System.out.println((i + 1) + ". " + purchaseList.get(i));
				}

			} else if (choice.equals("q")) {

				System.out.println("프로그램을 종료합니다.");
				break;

			} else {

				System.out.println("잘못된 입력입니다. 다시 선택하세요.");

			}
		}

		scanner.close();

	}

}
