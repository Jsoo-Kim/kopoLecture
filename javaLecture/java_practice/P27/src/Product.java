
public class Product {
	public String productName;
	public int price;
	
	Product() {
		
	}
	
	Product(String productName, int price) {
		this.productName = productName;
		this.price = price;
	}
	
	@Override
	public String toString() {
        return "Product [productName: " + productName + ", price: " + price + " 원]";
    }
}
