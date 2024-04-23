import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ProductList {
	 public List<Product> productList;
	 
	 ProductList() {
		 this.productList = new ArrayList<>();
	 }
	 
	// 상품 리스트에 추가 
    public void addProduct(Product product) {
        productList.add(product);
        System.out.println("상품이 추가되었습니다.\n");
    }
    
    // 상품 목록 출력
    public void printProductList() {
        System.out.println("\n[상품 목록]");
        for (Product product : productList) {
            System.out.println("상품명: " + product.name);
            System.out.println("가격: " + product.price);
            System.out.println("재고: " + product.stock);
            System.out.println("할인율: " + product.discountRate);
            System.out.println("-----------------------");
        }
    }
    
    // 상품 통계 출력
    public void printProductStatics() {
    	int priceSum = 0;
    	double discountedPriceSum = 0;
    	System.out.println("\n[상품 통계]");
    	for (Product product : productList) {
    		priceSum += product.price;
    		discountedPriceSum += product.price * (1 - product.discountRate);
    	}
    	System.out.println("총 상품 수: " + productList.size());
    	System.out.println("평균 가격: " + (priceSum / productList.size()));
    	System.out.println("할인 적용 평균 가격: " + (discountedPriceSum / productList.size()));
    	System.out.println("-----------------------");
    }
    
    // 높은 가격 순 출력
    public void printByHighestPrice() {
        // 상품 목록을 가격이 높은 순으로 정렬
        Collections.sort(productList, (p1, p2) -> p2.price - p1.price);

        System.out.println("\n[높은 가격 순 상품 목록]");
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    // 낮은 가격 순 출력
    public void printByLowestPrice() {
        // 상품 목록을 가격이 낮은 순으로 정렬
        Collections.sort(productList, (p1, p2) -> p1.price - p2.price);

        System.out.println("\n[낮은 가격 순 상품 목록]");
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    // 할인율 높은 순 출력
    public void printByHighestDiscountRate() {
        // 상품 목록을 할인율이 높은 순으로 정렬
        Collections.sort(productList, (p1, p2) -> Double.compare(p2.discountRate, p1.discountRate));

        System.out.println("\n[할인율 높은 순 상품 목록]");
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    // 할인율 낮은 순 출력
    public void printByLowestDiscountRate() {
        // 상품 목록을 할인율이 낮은 순으로 정렬
        Collections.sort(productList, (p1, p2) -> Double.compare(p1.discountRate, p2.discountRate));

        System.out.println("\n[할인율 낮은 순 상품 목록]");
        for (Product product : productList) {
            System.out.println(product);
        }
    }
    
    // 상품 입고
    public void addStock() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("입고할 상품명을 입력하세요: ");
    	String inputProductName = scanner.nextLine();
    	System.out.println("입고할 수량을 입력하세요: ");
    	int inputAddStock = Integer.parseInt(scanner.nextLine());
    	
    	for (Product product : productList) {
    		if (product.name.equals(inputProductName)) {
    			System.out.println("기존 재고: " + product.stock);
    			product.stock += inputAddStock;
    			System.out.println("입고 후 재고: " + product.stock);
    			System.out.println("-----------------------");
    			return;
    		}
    	}
    	System.out.println("해당하는 상품명이 존재하지 않습니다.");
    }
    
}
