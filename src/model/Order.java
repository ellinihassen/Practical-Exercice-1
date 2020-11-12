package model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Order {

   public enum OrderState {
        CREATED("created"),
        VALIDATED("validated"),
        DELIVERD("delivered");

        private final String status;

        OrderState(String status) {
            this.status=status;
        }
        public boolean equalsStatus(String otherStatus) {

            return status.equals(otherStatus);
        }
        public String toString() {
            return this.status;
        }

    }

    private Long id;
    private LocalDate date;
    private Double etPrice;
    private OrderState status;
    private User user;
    private Adresse adresseLiv;
    private List<OrderItem> items;

    private static final Integer tva = 5;

    public Order() {

    }

    public Order(OrderState status, User user) {
        Date date = new Date();
        LocalDate localDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        this.status = status;
        this.user = user;
        this.date = localDate;
    }

    public double calculateMontantTotal() {

        double somme =0;
        for (OrderItem orderItem : items) {
            somme += orderItem.calaculateTotalPrice();
        }
        this.etPrice=somme;
        return somme;
    }


	    public Double calculateTaxPrice() {
		return calculateMontantTotal()/100 *tva;
	}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public void confirmDelivery() {
        this.status = OrderState.DELIVERD;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Adresse getAdresseLiv() {
        return adresseLiv;
    }

    public void setAdresseLiv(Adresse adresseLiv) {
        this.adresseLiv = adresseLiv;
    }

    public Double getEtPrice() {
        return etPrice;
    }

    public void setEtPrice(Double etPrice) {
        this.etPrice = etPrice;
    }

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(String status) {
        if(status.equals("created")){
            this.status= OrderState.CREATED;
        }else if(status.equals("validated")){
            this.status= OrderState.VALIDATED;
        }else if(status.equals("delivered")){
            this.status= OrderState.DELIVERD;
        }

    }


}
