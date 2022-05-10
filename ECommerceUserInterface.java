//imported libraries
import java.util.Scanner;

// Simulation of a Simple E-Commerce System (like Amazon)
public class ECommerceUserInterface
{
	public static void main(String[] args)
	{
		// Create the system
		ECommerceSystem amazon = new ECommerceSystem();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");
		
		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			try {
				String action = scanner.nextLine();
			
				if (action == null || action.equals("")) 
				{
					System.out.print("\n>");
					continue;
				}
				else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT")) // Stops the program
					return;

				else if (action.equalsIgnoreCase("PRODS"))	// List all products for sale
				{
					amazon.printAllProducts(); 
				}
				else if (action.equalsIgnoreCase("BOOKS"))	// List all books for sale
				{
					amazon.printAllBooks(); 
				}
				else if (action.equalsIgnoreCase("SHOES"))	// List all the shoes for sale
				{
					amazon.printAllShoes(); 
				}
				else if (action.equalsIgnoreCase("CUSTS")) 	// List all registered customers
				{
					amazon.printCustomers();	
				}
				else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
				{
					amazon.printAllOrders();	
				}
				else if (action.equalsIgnoreCase("SHIPPED")) // List all orders that have been shipped
				{
					amazon.printAllShippedOrders();	
				}
				else if (action.equalsIgnoreCase("NEWCUST")) // Create a new registered customer
				{
					String name = "";
					String address = "";
					
					System.out.print("Name: ");
					//get the customer's name
					if (scanner.hasNextLine())
						name = scanner.nextLine();
					
					System.out.print("\nAddress: ");
					//get the customer's address
					if (scanner.hasNextLine())
						address = scanner.nextLine();

					amazon.createCustomer(name, address);
				}
				else if (action.equalsIgnoreCase("SHIP"))	// ship an order to a customer
				{
						String orderNumber = "";
			
						System.out.print("Order Number: ");
						// get order number from scanner
						if (scanner.hasNextLine())
							orderNumber = scanner.nextLine();

						ProductOrder ship = amazon.shipOrder(orderNumber);
						ship.print();
				}
				else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this customer id
				{
					String customerId = "";

					System.out.print("Customer Id: ");
					// get customer Id from scanner
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();

					amazon.printOrderHistory(customerId);
				}
				else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
				{
					String productId = "";
					String customerId = "";

					System.out.print("Product Id: ");
					// get product Id from scanner
					if (scanner.hasNextLine())
					productId = scanner.nextLine();

					System.out.print("\nCustomer Id: ");
					// get customer Id from scanner
					if (scanner.hasNextLine())
					customerId = scanner.nextLine();

					String order = amazon.orderProduct(productId, customerId, "");
					System.out.print("Order #" + order);
				}
				else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
				{
					String productId = "";
					String customerId = "";
					String options = "";

					System.out.print("Product Id: ");
					// get product Id
					if (scanner.hasNextLine())
						productId = scanner.nextLine();

					System.out.print("\nCustomer Id: ");
					// get customer Id
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();

					System.out.print("\nFormat [Paperback Hardcover EBook]: ");
					// get book format and store in options string
					if (scanner.hasNextLine())
						options = scanner.nextLine();
					
					String orderBook = amazon.orderProduct(productId, customerId, options);
					System.out.print("Order #" + orderBook);
				}
				else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and color 
				{
					String productId = "";
					String customerId = "";
					String options = "";
					
					System.out.print("Product Id: ");
					// get product id
					if (scanner.hasNextLine())
						productId = scanner.nextLine();

					System.out.print("\nCustomer Id: ");
					// get customer id
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();

					System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
					// get shoe size and store in options	
					if (scanner.hasNextLine())
						options = scanner.nextLine();

					System.out.print("\nColor: \"Black\" \"Brown\": ");
					// get shoe color and append to options
					if (scanner.hasNextLine())
						options += " " + scanner.nextLine();

					String orderShoes = amazon.orderProduct(productId, customerId, options);
					System.out.print("Order #" + orderShoes);
				}	
				else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
				{
					String orderNumber = "";

					System.out.print("Order Number: ");
					// get order number from scanner
					if (scanner.hasNextLine())
						orderNumber = scanner.nextLine();

					amazon.cancelOrder(orderNumber);
				}
				else if (action.equalsIgnoreCase("PRINTBYPRICE")) // print the list of products sorted by price - old name = SORTBYPRICE
				{
					amazon.sortByPrice();
				}
				else if (action.equalsIgnoreCase("PRINTBYNAME")) // print the list of products sorted by name (alphabetic) - old name = SORTBYNAME
				{
					amazon.sortByName();
				}
				else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
				{
					amazon.sortCustomersByName();
				}
				else if (action.equalsIgnoreCase("BOOKSBYAUTHOR")) // sort books by author name (alphabetic)
				{
					String authorName = "";

					System.out.print("Name of Author: ");
					// get author name
					if (scanner.hasNextLine())
						authorName = scanner.nextLine();

					amazon.sortBookByAuthor(authorName);
				}
				else if (action.equalsIgnoreCase("ADDTOCART")) // NEW -> adds an item to the cutomer's cart
				{
					String customerId = "";
					String productId = "";
					String productOptions = "";

					System.out.print("\nProduct Id: ");
					// get product Id from scanner
					if (scanner.hasNextLine())
						productId = scanner.nextLine();

					System.out.print("Customer Id: ");
					// get customer Id from scanner
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();

					if (amazon.getProduct(productId) != null) {
						//gets the product option from scanner only if it's a book or shoe
						if (amazon.getProduct(productId).getCategory() == Product.Category.BOOKS) {
							System.out.print("\nFormat [Paperback Hardcover EBook]: ");
							if (scanner.hasNextLine())
								productOptions = scanner.nextLine();
						}
						if (amazon.getProduct(productId).getCategory() == Product.Category.SHOES) {
							System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
							// get shoe size and store in options	
							if (scanner.hasNextLine())
								productOptions = scanner.nextLine();

							System.out.print("\nColor: \"Black\" \"Brown\": ");
							// get shoe color and append to options
							if (scanner.hasNextLine())
								productOptions += " " + scanner.nextLine();
						}
						amazon.addCart(productId, customerId, productOptions);
					}
					else {
						throw new ProductNotFoundException("Product " + productId + " Not Found");
					}
			
				}
				else if (action.equalsIgnoreCase("REMCARTITEM")) // NEW -> removes an item to the cutomer's cart
				{
					String customerId = "";
					String productId = "";

					System.out.print("Customer Id: ");
					// get customer Id from scanner
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();

					System.out.print("\nProduct Id: ");
					// get product Id from scanner
					if (scanner.hasNextLine())
						productId = scanner.nextLine();

					amazon.removeCartItem(customerId, productId);
				}
				else if (action.equalsIgnoreCase("PRINTCART")) // NEW -> prints all the items in the customer's cart
				{
					String customerId = "";

					System.out.print("Customer Id: ");
					// get product Id from scanner
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();

					amazon.printCart(customerId);
				}
				else if (action.equalsIgnoreCase("ORDERITEMS")) // NEW -> orders all the items in the cutomer's cart
				{
					String customerId = "";

					System.out.print("Customer Id: ");
					// get customer Id from scanner
					if (scanner.hasNextLine())
						customerId = scanner.nextLine();

					amazon.orderItems(customerId);
				}
				else if (action.equalsIgnoreCase("STATS")) // NEW -> lists the statistics of the number of times each product was ordered
				{
					amazon.stats();
				}
				else if (action.equalsIgnoreCase("RATEPRODUCT")) // NEW -> allow customer to rate a product from 1-5 stars
				{
					String productId = "";
					String rating = "";
					System.out.print("Product Id: ");
					// get product Id from scanner
					if (scanner.hasNextLine())
						productId = scanner.nextLine();

					System.out.print("\nRating [1-5 Stars]: ");
					// get rating from scanner
					if (scanner.hasNextLine())
						rating = scanner.nextLine();

					amazon.rateProduct(productId, rating);
				}
				else if (action.equalsIgnoreCase("AVGRATING")) // NEW -> lists out the average rating for a specific product
				{
					String productId = "";
					System.out.print("Product Id: ");
					// get product Id from scanner
					if (scanner.hasNextLine())
						productId = scanner.nextLine();

					amazon.avgProdRating(productId);
				}
				else if (action.equalsIgnoreCase("SORTBYRATINGS")) // NEW -> lists all the products belonging to a specified category that are above rating given by customer 
				{
					String category = "";
					String rating = "";

					System.out.print("Category [GENERAL, CLOTHING, BOOKS, FURNITURE, COMPUTERS, SHOES]: ");
					// get category of product from scanner
					if (scanner.hasNextLine())
						category = scanner.nextLine();

					System.out.print("Minimum Rating of: ");
					// get minimum rating requirement from scanner
					if (scanner.hasNextLine())
						rating = scanner.nextLine();

					amazon.sortRatings(category, rating);
				}
			}
			catch(CustomerNotFoundException e) {
				System.out.println(e.getMessage());
			}
			catch(InvalidAuthorNameException e) {
				System.out.println(e.getMessage());
			}
			catch(InvalidCustAddressException e) {
				System.out.println(e.getMessage());
			}
			catch(InvalidCustNameException e) {
				System.out.println(e.getMessage());
			}
			catch(InvalidOrderNumException e) {
				System.out.println(e.getMessage());
			}
			catch(InvalidProductOptionsException e) {
				System.out.println(e.getMessage());
			}
			catch(OutOfStockException e) {
				System.out.println(e.getMessage());
			}
			catch(ProductNotFoundException e) {
				System.out.println(e.getMessage());
			}
			catch(InvalidRatingException e) {
				System.out.println(e.getMessage());
			}
			catch(NoItemsInCartException e) {
				System.out.println(e.getMessage());
			}
			System.out.print("\n>");
		}
	}
}
