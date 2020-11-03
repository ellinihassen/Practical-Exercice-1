import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Store store = new Store();
		Admin admin = new Admin("hassen","ellini","hassen@gmail.com","123456");
		store.setAdmin(admin);
		
		ShoppingCart cart = new ShoppingCart();
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
		String actionMenu =
				"Login as: \n 1- admin \n 2- user \n"
				+ "Enter first as admin and create at least one user "
				+ "one category and one product";
		String response;
		do {
			System.out.println(actionMenu);
			response = scanner.nextLine();
		} while (response.isEmpty());
		
		
		if (response.contains("1")) {
			String msg = "Enter your email: \n";
			System.out.println(msg);
			String email = scanner.next();
			// password
			msg = "Enter your password: \n";
			System.out.println(msg);
			String password = scanner.next();
			
				if(store.getAdmin().getEmail().equals(email) && store.getAdmin().getPassword().equals(password)) {
						System.out.println("Login Succeded");
						while(true) {
						actionMenu =
								  "1 -- Add User \n"
								+ "2 -- Add Category \n"
								+ "3 -- Add Product \n"
								+ "4 -- Affiche Users \n "
								+ "5 -- Affiche Categories \n "
								+ "6 -- Affiche Products \n "
								+ "7 -- leave menu";
						
						response=scanner.nextLine();
						do {
							System.out.println(actionMenu);
							response = scanner.nextLine();
						} while (response.isEmpty());
						
						if (response.contains("1")) {
							
								System.out.println("Enter user name");
								String name = scanner.nextLine();
								System.out.println("Enter user lastName");
								String lastName = scanner.nextLine();
								System.out.println("Enter user email");
								String email1 = scanner.nextLine();
								System.out.println("Enter user password");
								String pass = scanner.nextLine();
								User user1 = new User(name,lastName,email1,pass);
								store.addUsers(user1);
								
						}
						if (response.contains("2")) {
							
							System.out.println("Enter category name");
							String name = scanner.nextLine();
							Category cat1 = new Category(name);
							store.addCategories(cat1);
							
					}if (response.contains("3")) {
						
						System.out.println("Enter Product  name");
						String productName = scanner.nextLine();
						System.out.println("Enter Description");
						String description = scanner.nextLine();
						System.out.println("Enter unitPrice");
						double unitPrice = scanner.nextDouble();
						System.out.println("Enter number of product in stock");
						int productInStock = scanner.nextInt();
						String catName;
						Category cat;
						do {
							System.out.println("Enter a valid category name");
							catName = scanner.nextLine();
							cat =store.searchCategoreis(catName);
						}while(cat==null);
						String photo=" ";
						Product p1 = new Product(productName,description,photo,unitPrice,productInStock,cat);
						store.addProducts(p1);
						
					}
					 if(response.contains("4")) {
							
						 store.afficheUsers();
							
					 	}
					 if(response.contains("5")) {
							
						 store.afficheCategories();
							
					 	}
					 if(response.contains("6")) {
							
					 store.afficheProducts();
							
					 	}
					 
					if (response.contains("7")) {
						break;
						}
						
					}		//end while admin menu
				}else {
					System.out.println("Permession denied for admin");
				}
		}
		if (response.contains("2")) {
			String msg = "Enter your email: \n";
			System.out.println(msg);
			String email = scanner.next();
			// password
			msg = "Enter your password: \n";
			System.out.println(msg);
			String password = scanner.next();
			User user = store.searchUsers(email);
				if(user != null && user.getPassword().equals(password)) {
					System.out.println("Login Succeded");
					
					while(true) {
					actionMenu =
							  "1 -- Affiche Product List \n "
							+ "2 -- Affiche Shopping Cart \n "
							+ "3 -- Remove Item from Shopping Cart \n"
							+ "4 -- place an order \n"
							+ "5 -- leave menu ";
					
					response=scanner.nextLine();
					do {
						System.out.println(actionMenu);
						response = scanner.nextLine();
					} while (response.isEmpty());
				
				
				 
				Order order;
				if (response.contains("1")) {
					
					store.afficheProducts();
					System.out.println("Choose item number to add to your shopping cart");
					int index = scanner.nextInt();
					Product p=store.getProducts().get(index);
					System.out.println("please enter quantity of this item ");
					int quantity = scanner.nextInt();
					cart.addItem(p, quantity);
					//cart.afficheCart();
					
					}
					if(response.contains("2")) {
						cart.afficheCart();
					}	
						if(response.contains("3")) {
						cart.afficheCart();
						System.out.println("Choose item number to remove from shopping cart");
						int index = scanner.nextInt();
						cart.getCart().remove(index);
					}	
						if(response.contains("4")) {
							System.out.println("Enter your ZibCode");
							String zibCode = scanner.nextLine();
							System.out.println("Enter your street");
							String street = scanner.nextLine();
							System.out.println("Enter your  city");
							String city = scanner.nextLine();
							System.out.println("Enter your country");
							String country = scanner.nextLine();
							Adresse adr = new Adresse(zibCode,street,city,country);
							order = new Order(user,adr,cart);
							order.afficheOrderDetail();
							System.out.println("Would u like to validate your command type 1 to validate");
							int validate = scanner.nextInt();
								if(validate==1) {
									order.validateOrder();
									order.afficheOrderDetail();
								}
			
						}
				
						if(response.contains("5")) {
						break;
					}
		
				} //end while user menu
		
				}
			}else {
				System.out.println("Permession denied for user");
			}
		}//end while main 
	}
}
