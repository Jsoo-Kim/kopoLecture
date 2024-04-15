import java.util.ArrayList;
import java.util.List;

public class Purchase {
	public List<Product> products;
	public String shippingAddress;

	Purchase() {
		this.products = new ArrayList<>();
	}
	
	public void addProduct(Product product) {
		products.add(product);
	}
	

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public int getTotalPrice() { // 총 가격은 상품을 추가할 때마다 바뀌니까 그냥 메소드로 넣자!
        int totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.price;
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Purchase [" + products + ", shippingAddress: " + shippingAddress + "]";
    }
}
