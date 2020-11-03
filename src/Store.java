import java.util.ArrayList;
import java.util.List;

public class Store {
	
	private String name;
	private Admin admin;
	private List<Category> categories;
	private List<Product> products;
	private List<User> users;
	private List<Order> orders;
	
	
	
	public Store() {
		categories = new ArrayList<>();
		products = new ArrayList<>();
		users = new ArrayList<>();
		orders = new ArrayList<>();
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void addCategories(Category category) {
		this.categories.add(category);
	}
	public List<Product> getProducts() {
		return products;
	}
	public void addProducts(Product product) {
		this.products.add(product);
	}
	public List<User> getUsers() {
		return users;
	}
	public void addUsers(User user) {
		this.users.add(user);
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public Category searchCategoreis(String name) {
		
		for (Category category : categories) {
			if (category.getName().equals(name)) {
				return category;
			}
		}
		return null;
	}
	
	public User searchUsers(String email) {
		for (User user : users) {
			if(user.getEmail().equals(email)) {
				return user;
				}
			
			}
			return null;
		}
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public void afficheProducts() {
		for (int i=0;i< products.size();i++) {
			System.out.println(i +"- "+products.get(i));
		}
	}
	
	public void afficheCategories() {
		for (Category category : categories) {
			System.out.println(category);
		}
	}
	
	public void afficheUsers() {
		for(User user : users) {
			System.out.println(user);
		}
	}
	
	
}
