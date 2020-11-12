import model.*;
import service.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserService();
        AuthorService authorService = new AuthorService();
        AdresseService adresseService = new AdresseService();
        CategoryService categoryService = new CategoryService();
        BookService bookService = new BookService();
        OrderItemService orderItemService = new OrderItemService();
        OrderService orderService = new OrderService();


//		User admin = new User("hassen","ellini","hassen@gmail.com","123456");
//		admin.setRole(User.Role.ADMIN);
//		userService.save(admin);



        Scanner scanner = new Scanner(System.in);

        while (true) {
            String actionMenu =
                    "Login as: \n 1- admin \n 2- user \n 3- leave app \n"
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
                User admin = userService.login(email, password);
                if (admin != null && admin.getRole().equals("admin")) {
                    System.out.println("Login Succeded");
                    while (true) {
                        actionMenu =
                                "1 -- Add User \n"
                                        + "2 -- Add Category \n"
                                        + "3 -- Add Author \n"
                                        + "4 -- Add Book \n"
                                        + "5 -- Affiche Users \n "
                                        + "6 -- Affiche Categories \n "
                                        + "7 -- Affiche Books \n "
                                        + "8 -- leave menu";

                        response = scanner.nextLine();
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
                            User user1 = new User(name, lastName, email1, pass);
                            user1.setRole("user");
                            userService.save(user1);

                        }
                        if (response.contains("2")) {

                            System.out.println("Enter category name");
                            String name = scanner.nextLine();
                            Category cat1 = new Category(name);
                            categoryService.save(cat1);

                        }
                        if (response.contains("3")) {

                            System.out.println("Enter a valid author name");
                            String name = scanner.nextLine();
                            System.out.println("Enter a valid author lastName");
                            String lastName = scanner.nextLine();
                            Author auth = new Author(name, lastName);
                            authorService.save(auth);


                        }
                        if (response.contains("4")) {

                            System.out.println("Enter book  title");
                            String title = scanner.nextLine();
                            System.out.println("Enter unitPrice");
                            double price = scanner.nextDouble();
                            System.out.println("Enter Description");
                            String description = scanner.nextLine();
                            String photoname = " ";
                            System.out.println("Enter number of product in stock");
                            int productInStock = scanner.nextInt();
                            String catName;
                            Category cat;
                            do {
                                System.out.println("Enter a valid category name");
                                catName = scanner.nextLine();
                                cat = categoryService.searchCategoreis(catName);
                            } while (cat == null);
                            String authorName;
                            String authorLastName;
                            Author author;
                            do {
                                System.out.println("Enter a valid author name");
                                authorName = scanner.nextLine();
                                System.out.println("Enter a valid author lastName");
                                authorLastName = scanner.nextLine();
                                author = authorService.searchAuthor(authorName, authorLastName);
                            } while (author == null);

                            Date date = new Date();
                            LocalDate localDate = date.toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate();
                            Book book = new Book(title, price, localDate, description, photoname, productInStock);
                            book.setCategory(cat);
                            book.setAuthor(author);
                            bookService.save(book);


                        }
                        if (response.contains("5")) {

                            userService.afficheUsers();

                        }
                        if (response.contains("6")) {

                            categoryService.afficheCategories();

                        }
                        if (response.contains("7")) {

                            bookService.afficheBooks();

                        }

                        if (response.contains("8")) {
                            break;
                        }

                    }        //end while admin menu
                } else {
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
                User user = userService.login(email, password);
                if (user != null && user.getRole().equals("user")) {
                    System.out.println("Login Succeded");

                    Order order = orderService.getOrderByStatusAndUserId(Order.OrderState.CREATED.toString(), user.getId());
                    Adresse adr = null;

                    if (order == null) {
                        order = new Order(Order.OrderState.CREATED, user);
                        order.setEtPrice(0d);
                        adr = new Adresse(" ", " ", " ", " ");
                        adresseService.save(adr);
                        order.setAdresseLiv(adr);
                        orderService.save(order);
                    } else {
                        adr = order.getAdresseLiv();
                    }


                    while (true) {
                        actionMenu =
                                "1 -- Affiche Books List And choose book to add\n "
                                        + "2 -- Affiche Shopping Cart \n "
                                        + "3 -- Remove Item from Shopping Cart \n"
                                        + "4 -- place an order \n"
                                        + "5 -- leave menu ";

                        response = scanner.nextLine();
                        do {
                            System.out.println(actionMenu);
                            response = scanner.nextLine();
                        } while (response.isEmpty());


                        if (response.contains("1")) {

                            bookService.afficheBooks();
                            System.out.println("Choose item number to add to your shopping cart");
                            Long index = scanner.nextLong();
                            Book b = bookService.getBookById(index);
                            System.out.println("please enter quantity of this item ");
                            int quantity = scanner.nextInt();
                            OrderItem orderItem = new OrderItem(b, quantity);
                            orderItem.setOrder(order);
                            orderItemService.save(orderItem);

                        }
                        if (response.contains("2")) {
                            order.setItems(orderItemService.getItemsByOrderId(order.getId()));

                            for (OrderItem orderItem : order.getItems()) {
                                System.out.println(orderItem);
                            }
                            ;
                        }
                        if (response.contains("3")) {
                            for (OrderItem orderItem : order.getItems()) {
                                System.out.println(orderItem);
                            }
                            ;
                            System.out.println("Enter the id of product to delete from order");
                            Long index = scanner.nextLong();
                            orderItemService.delete(index);
                            order.setItems(orderItemService.getItemsByOrderId(order.getId()));
                        }
                        if (response.contains("4")) {
                            System.out.println("Enter your ZibCode");
                            String zibCode = scanner.nextLine();
                            System.out.println("Enter your street");
                            String street = scanner.nextLine();
                            System.out.println("Enter your  city");
                            String city = scanner.nextLine();
                            System.out.println("Enter your country");
                            String country = scanner.nextLine();
                            Long id = adr.getId();
                            adr = new Adresse(zibCode, street, city, country);
                            adr.setId(id);
                            adresseService.update(adr);

                            for (OrderItem orderItem : order.getItems()) {
                                System.out.println(orderItem + " " + orderItem.calaculateTotalPrice());
                            }

                            System.out.println("HTT : " + order.getEtPrice());
                            System.out.println("Total to pay " + order.calculateMontantTotal());
                            System.out.println("Would u like to validate your command type 1 to validate");
                            int validate = scanner.nextInt();
                            if (validate == 1) {
                                orderService.validateOrder(order);
                            }

                        }

                        if (response.contains("5")) {
                            break;
                        }

                    } //end while user menu

                }
            } else {
                System.out.println("Permession denied for user");
            }
            if (response.contains("3")) {
                break;
            }
        }//end while main
//		User admin = new User("hassen","ellini","hassen@gmail.com","123456");
//		admin.setRole(User.Role.ADMIN);
//		userService.save(admin);
//		Author author = new Author("martin","luther king");
//		authorService.save(author);
//		Category category = new Category("Java");
//		categoryService.save(category);
//		Date date = new Date();
//		LocalDate localDate = date.toInstant()
//				.atZone(ZoneId.systemDefault())
//				.toLocalDate();
//		Book book = new Book("harry potter", 25.0, localDate, "the second novel of the serie", "../image/harry.png", 100);
//		book.setCategory(categoryService.getCategoryById(1l));
//		book.setAuthor(authorService.getAuthorById(1l));
//		bookService.save(book);

    }
}
