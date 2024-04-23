public class Product {
    int productId;
    String name;
    int price;
    int stock;

    // 생성자
    public Product(int productId, String name, int price, int stock) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

//    // 상품 정보 출력 메서드
//    public void printInfo() {
//        System.out.println("상품명: " + name);
//        System.out.println("가격: " + price + "원");
//        System.out.println("재고: " + stock);
//    }

    // 상품 재고 감소
    public void decreaseStock(int quantity) {
        this.stock -= quantity;
    }

    // 상품 재고 증가
    public void increaseStock(int quantity) {
        this.stock += quantity;
    }

}
