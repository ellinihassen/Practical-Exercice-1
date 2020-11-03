import java.util.ArrayList;

public class ShoppingCart {
	
	ArrayList<ShoppingCartItem> cart;
	
	
	
	public ShoppingCart() {
		
		cart = new ArrayList<ShoppingCartItem>();	
		}


	public void addItem(Product p,int q) {
		ShoppingCartItem item= new ShoppingCartItem(p, q);
		cart.add(item);
		
		
	}

	public void removeItem(int index) {
		cart.remove(index);
		
	}
	
	
	
	public double calculateMontantTotal() {
		 double somme =0;
		for (ShoppingCartItem shoppingCartItem : cart) {
			somme += shoppingCartItem.calaculateTotalPrice();
		}
		return somme;
	}
	
	
	
	public ArrayList<ShoppingCartItem> getCart() {
		return cart;
	}


	public void setCart(ArrayList<ShoppingCartItem> cart) {
		this.cart = cart;
	}


	@Override
	public String toString() {
		return "the Shopping cart contain " + cart.size() + " item this is your order \n" + cart ;
	}


	public void afficheCart() {
		System.out.println(toString());
		System.out.println(" Price to pay :  "+calculateMontantTotal());
	}



}
