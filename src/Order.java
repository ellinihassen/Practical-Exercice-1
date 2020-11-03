import java.util.Date;

public class Order {
	
	enum OrderState {
		  CREATED,
		  VALIDATED,
		  DELIVERD
		}
	private Date date;
	private Adresse adresseLiv;
	private double etPrice;
	private OrderState status;
	private ShoppingCart cart;
	private User user;
	private static  final double tva = 5;
	
	public Order() {
		
	}
	
	public Order( User user,Adresse adresseLiv,ShoppingCart cart) {
		this.user =user;
		this.date = new Date();
		this.adresseLiv = adresseLiv;
		this.etPrice = cart.calculateMontantTotal();
		this.status = OrderState.CREATED;
		this.cart = cart;
	}
	
	public double calculateTaxPrice() {
		return cart.calculateMontantTotal()/100 *tva;
	}
	
	public void afficheOrderDetail() {
		cart.afficheCart();
		System.out.println(" Purshased from"+ this.user +"Created at "+this.date+" "
				+ " Delivery adresse "+this.adresseLiv+" price after tax "
				+this.etPrice+calculateTaxPrice()+" \n Statut: "+this.status);
	}
	
	
	public void validateOrder() {
		this.status = OrderState.VALIDATED;
	}
	
	public void confirmDelivery() {
		this.status = OrderState.DELIVERD;
	}
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Adresse getAdresseLiv() {
		return adresseLiv;
	}
	public void setAdresseLiv(Adresse adresseLiv) {
		this.adresseLiv = adresseLiv;
	}
	public double getEtPrice() {
		return etPrice;
	}
	public void setEtPrice(double etPrice) {
		this.etPrice = etPrice;
	}
	public OrderState getStatus() {
		return status;
	}
	public void setStatus(OrderState status) {
		this.status = status;
	}
	
	
	
	
	
	

}
