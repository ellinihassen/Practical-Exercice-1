
public class Product {
	
		
	    private String name;
	    private String description;
	   
	    private String photoName;
	    private double unitPrice;
	    private int productInStock;
	    
	    private Category category;

		public Product() {
			
			// TODO Auto-generated constructor stub
		}

		public Product(String name, String description, String photoName, double unitPrice,
				int productInStock, Category category) {
	
			this.name = name;
			this.description = description;
			this.photoName = photoName;
			this.unitPrice = unitPrice;
			this.productInStock = productInStock;
			this.category = category;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		

		public String getPhotoName() {
			return photoName;
		}

		public void setPhotoName(String photoName) {
			this.photoName = photoName;
		}

		public double getUnitPrice() {
			return unitPrice;
		}

		public void setUnitPrice(double unitPrice) {
			this.unitPrice = unitPrice;
		}

		public int getProductInStock() {
			return productInStock;
		}

		public void setProductInStock(int productInStock) {
			this.productInStock = productInStock;
		}

		public Category getCategory() {
			return category;
		}

		public void setCategory(Category category) {
			this.category = category;
		}

		@Override
		public String toString() {
			return " the Product:" + name + ", description:" + description+ " unitPrice:" + unitPrice + ", productInStock:" + productInStock + ", category:" + category
					+ "";
		}
	    
		
	    
	    

		
		
		
		
	    
	    

}
