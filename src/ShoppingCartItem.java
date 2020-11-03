
public class ShoppingCartItem {

	private Product product;
	private int quantity;

	
	
	public ShoppingCartItem() {
		
		// TODO Auto-generated constructor stub
	}

	public ShoppingCartItem(Product product, int quantity) {

		this.product = product;
		this.quantity = quantity;

	}

	public double calaculateTotalPrice() {
		return product.getUnitPrice() * quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Item :" + product + ", quantity=" + quantity + " \n";
	}

	public void afficheItem() {
		System.out.println(toString());
	}

}
