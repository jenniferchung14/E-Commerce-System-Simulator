//imported libraries
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem
{
    //private ArrayList<Product>  productsList = new ArrayList<Product>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();	

    private ArrayList<ProductOrder> orders = new ArrayList<ProductOrder>();
    private ArrayList<ProductOrder> shippedOrders = new ArrayList<ProductOrder>();

    //private Map<String, Product> products = new HashMap<String, Product>(); 
    private TreeMap<String, Product> products = new TreeMap<String, Product>();

    // These variables are used to generate order numbers, customer id's, product id's 
    private int orderNumber = 500;
    private int customerId = 900;
    private int productId = 700;
    
    // General variable used to store an error message when something is invalid (e.g. customer id does not exist)  
    String errMsg = null;
    
    // Random number generator
    Random random = new Random();
    
    public ECommerceSystem()
    {
    	// NOTE: do not modify or add to these objects!! - the TAs will use for testing
    	// If you do the class Shoes bonus, you may add shoe products
    	// Create some products. Notice how generateProductId() method is used
    	// productsList.add(new Product("Acer Laptop", generateProductId(), 989.0, 99, Product.Category.COMPUTERS));
    	// productsList.add(new Product("Apex Desk", generateProductId(), 1378.0, 12, Product.Category.FURNITURE));
    	// productsList.add(new Book("Book", generateProductId(), 45.0, 4, 2, "Ahm Gonna Make You Learn", "T. McInerney", 2000));
    	// productsList.add(new Product("DadBod Jeans", generateProductId(), 24.0, 50, Product.Category.CLOTHING));
    	// productsList.add(new Product("Polo High Socks", generateProductId(), 5.0, 199, Product.Category.CLOTHING));
    	// productsList.add(new Product("Tightie Whities", generateProductId(), 15.0, 99, Product.Category.CLOTHING));
    	// productsList.add(new Book("Book", generateProductId(), 35.0, 4, 2, "How to Fool Your Prof", "D. Umbast", 1999));
    	// productsList.add(new Book("Book", generateProductId(), 45.0, 4, 2, "How to Escape from Prison", "A. Fugitive", 1985));
    	// productsList.add(new Book("Book", generateProductId(), 44.0, 14, 12, "Ahm Gonna Make You Learn More", "T. McInerney", 2022));
    	// productsList.add(new Product("Rock Hammer", generateProductId(), 10.0, 22, Product.Category.GENERAL));
      
      // //added shoes to test
      // int[][] airforces = {{30, 10}, {150, 80}, {25, 25}, {5, 8}, {4, 2}}; 
      // int[][] crocs = {{4, 2}, {3, 5}, {1, 4}, {2, 3}, {4, 2}};
      // int[][] vans = {{7, 100}, {60, 8}, {30, 10}, {5, 65}, {45, 5}};
      // int[][] tims = {{9, 10}, {11, 12}, {5, 4,}, {3, 2}, {1, 20}};
      // productsList.add(new Shoes("Air Forces", generateProductId(), 135.0, airforces));
      // productsList.add(new Shoes("Crocs", generateProductId(), 45.0, crocs));
      // productsList.add(new Shoes("Vans", generateProductId(), 55.0, vans));
      // productsList.add(new Shoes("Timberlands", generateProductId(), 150.0, tims));

      // //added more books to test the sort book by author action
      // productsList.add(new Book("Book", generateProductId(), 35.0, 4, 2, "How to Pass CPS 209", "I. Dunno", 2005));
    	// productsList.add(new Book("Book", generateProductId(), 45.0, 4, 2, "How to Print Hello World", "D. Umbast", 2000));
    	// productsList.add(new Book("Book", generateProductId(), 44.0, 14, 12, "Screaming Shouting Crying", "D. Umbast", 1999));
      // productsList.add(new Book("Book", generateProductId(), 44.0, 14, 12, "Dating 101", "D. Umbast", 1965));
      // productsList.add(new Book("Book", generateProductId(), 44.0, 14, 12, "Am I going to Succeed", "I. Dunno", 1995));
    	
      readProds(); //reads the product file

    	// Create some customers. Notice how generateCustomerId() method is used
    	customers.add(new Customer(generateCustomerId(),"Inigo Montoya", "1 SwordMaker Lane, Florin"));
    	customers.add(new Customer(generateCustomerId(),"Prince Humperdinck", "The Castle, Florin"));
    	customers.add(new Customer(generateCustomerId(),"Andy Dufresne", "Shawshank Prison, Maine"));
    	customers.add(new Customer(generateCustomerId(),"Ferris Bueller", "4160 Country Club Drive, Long Beach"));
    }
    
    //method to read the products given in the products file and place them into a hashmap
    private void readProds() {
      try {
        Scanner prodsFile = new Scanner(new File("products.txt"));
        
        while (prodsFile.hasNextLine()) { 
          String id = generateProductId();

          String category = prodsFile.nextLine(); //gets the category of the product

          //checks if it is a valid category
          for (Product.Category c : Product.Category.values()) {
            if (c.name().equals(category)) {
              String name = prodsFile.nextLine(); //gets the name of the product
              double price = Double.valueOf(prodsFile.nextLine()); //gets the price of the product

              //gets the additional information for a book product
              if (c.equals(Product.Category.BOOKS)) {
                //gets the book stock information for paperback and hardcover
                String[] stocksStr = prodsFile.nextLine().split(" ");
                int[] bookStock = new int[2];
                bookStock[0] = Integer.valueOf(stocksStr[0]);
                bookStock[1] = Integer.valueOf(stocksStr[1]);

                prodsFile.useDelimiter(":");
                String title = prodsFile.next();
                String author = prodsFile.next();
                int year = Integer.valueOf(prodsFile.nextLine().substring(1));
                products.put(id, new Book(name, id, price, bookStock[0], bookStock[1], title, author, year));
              }
              //gets the additional information for a shoe product
              else if (c.equals(Product.Category.SHOES)) {
                String[] stocksStr = prodsFile.nextLine().split(" ");
                int[][] shoeStock = new int[5][2];
                for (int i = 0; i < 5; i++) {
                  shoeStock[i][0] = Integer.valueOf(stocksStr[i]);
                }
                for (int j = 5; j < stocksStr.length; j++) {
                  shoeStock[j-5][1] = Integer.valueOf(stocksStr[j]);
                }
                products.put(id, new Shoes(name, id, price, shoeStock));
                prodsFile.nextLine(); 
              }
              else {
                int stock = Integer.valueOf(prodsFile.nextLine());
                int orderCount = 0;
                products.put(id, new Product(name, id, price, stock, Product.Category.valueOf(category), orderCount)); 
                prodsFile.nextLine();
              }
            }
          }
        }
      } catch (IOException e) {
          System.out.println(e.getMessage());
          System.exit(1);
      }
    }
    
    //method to generate order number as a string
    private String generateOrderNumber()
    {
    	return "" + orderNumber++;
    }

    //method to generate the customer id as a string
    private String generateCustomerId()
    {
    	return "" + customerId++;
    }
    
    //method to generate product id as a string
    private String generateProductId()
    {
    	return "" + productId++;
    }
    
    //method that returns the error message for when an input is invalid
    public String getErrorMessage()
    {
    	return errMsg;
    }
    
    //method that prints all the products in the products arraylist
    public void printAllProducts()
    {
      for (Product p : products.values()) {
        p.print();
      }
    }
    
    // Print all products that are books. See getCategory() method in class Product
    public void printAllBooks()
    {
      for (Product books : products.values()) {
        if (books.getCategory() == Product.Category.BOOKS) {
          books.print();
        }
      }
    }

    // Print all products that are Shoes
    public void printAllShoes()
    {
      for (Product shoes: products.values()) {
        if (shoes.getCategory() == Product.Category.SHOES) {
          shoes.print();
        }
      }
    }

    // Print all current orders
    public void printAllOrders()
    {
    	for (ProductOrder order: orders) {
        order.print();
      }
    }

    // Print all shipped orders
    public void printAllShippedOrders()
    {
    	for (ProductOrder shipped: shippedOrders) {
        shipped.print();
      }
    }
    
    // Print all customers
    public void printCustomers()
    {
    	for (Customer cust : customers) 
        cust.print();
    }

    /*
     * Given a customer id, print all the current orders and shipped orders for them (if any)
     */
    public void printOrderHistory(String customerId)
    {
      // Make sure customer exists - check using customerId
    	// if it does not throw exception

      boolean flag = false;

      for(int i = 0; i < customers.size(); i++) {
        if (customers.get(i).getId().equals(customerId)) { 
          // Print current orders of this customer 
          System.out.println("Current Orders of Customer " + customerId);
          // enter code here
          for (ProductOrder o : orders) {
            if (o.getCustomer().getId().equals(customerId)) {
              o.print();
            }
          }
          System.out.println();
          // Print shipped orders of this customer
          System.out.println("Shipped Orders of Customer " + customerId);
          //enter code here
          for (ProductOrder shipped : shippedOrders) {
            if (shipped.getCustomer().getId().equals(customerId)) {
              shipped.print();
            }
          }
          flag = true;
        }
      }
      if (flag == false) {
        throw new CustomerNotFoundException("Customer " + customerId + " Not Found");	
      }  
    }
    
    /*
     * Given a product id, customer id and product option it places an order for that specific product
     */
    public String orderProduct(String productId, String customerId, String productOptions)
    {
      Customer cust = checkValidCustomer(customerId);
      Product prod = checkValidProduct(productId);
      int orderCount = 0;

    	// Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
    	// See class Product and class Book for the method vaidOptions()
      if (!prod.validOptions(productOptions)) {
        throw new InvalidProductOptionsException("Product " + prod.getClass().getName() + " ProductId " + productId + " Invalid Options: " + productOptions);
      }

    	// Check if the product has stock available (i.e. not 0)
    	// See class Product and class Book for the method getStockCount()
      if (prod.getStockCount(productOptions) == 0) {
        throw new OutOfStockException("ProductId " + productId + " Not in Stock");
      }

      //will increase the order count by 1 (for STATS action)
      orderCount = prod.getOrderCount(); // NEW
      prod.setOrderCount(orderCount+1);

      // Create a ProductOrder, (make use of generateOrderNumber() method above)
    	// reduce stock count of product by 1 (see class Product and class Book)
    	// Add to orders list and return order number string
      ProductOrder prodOrder = new ProductOrder(generateOrderNumber(), prod, cust, productOptions);
    	prod.reduceStockCount(productOptions);  	
      orders.add(prodOrder);
      return prodOrder.getOrderNumber();
    }
    
    /*
     * Create a new Customer object and add it to the list of customers
     */ 
    public void createCustomer(String name, String address)
    {
    	// Check name parameter to make sure it is not null or ""
    	// if it does not throw exception
    	// Repeat this check for address parameter

    	if (name.equals(null) || name.equals("")) {
        throw new InvalidCustNameException();
      }
      else if (address.equals(null) || address.equals("")) { 
        throw new InvalidCustAddressException();
      }
      else {
        // Create a Customer object and add to array list
        customers.add(new Customer(generateCustomerId(), name, address)); 
      }
    }
    
    public ProductOrder shipOrder(String orderNumber)
    {
      // Check if order number exists first
    	// Retrieve the order from the orders array list, remove it, then add it to the shippedOrders array list
    	// return a reference to the order

      //USED CODE GIVEN IN SOLUTION
      int index = orders.indexOf(new ProductOrder(orderNumber,null,null,""));
      if (index == -1)
      {
        throw new InvalidOrderNumException("Order " + orderNumber + " Not Found");
      }
      ProductOrder order = orders.get(index);
      orders.remove(index);
      shippedOrders.add(order);
      return order;
    }
    
    /*
     * Cancel a specific order based on order number
     */
    public void cancelOrder(String orderNumber)
    {
      //if customer cancels their order it decreases the stat by 1 for the product
      // Check if order number exists first
      boolean flag = false;
      Product p = null;
      int orderCount = 0;

      //for loop that goes through orders arraylist to see if the order number already exists
      for (int i = 0; i < orders.size(); i++) {
        if (orders.get(i).getOrderNumber().equals(orderNumber)) {
          p = orders.get(i).getProduct();
          orderCount = p.getOrderCount();
          p.setOrderCount(orderCount-1);
          orders.remove(i);
          flag = true;
        }
      }
      if (flag == false) {
        throw new InvalidOrderNumException("Order " + orderNumber + " Not Found"); 
      }
    }
    
    // Sort products by increasing price
    public void sortByPrice()
    {
      ArrayList<Product> tempProdList = new ArrayList<Product>();

      //adds all the products into the temporary products arraylist created
      for (Product p : products.values()) {
        tempProdList.add(p);
      }
      
      Collections.sort(tempProdList, Comparator.comparing((Product::getPrice)));
      
      //prints the sorted arraylist
      for (Product prod : tempProdList) {
        prod.print();
      }
    }
    
    // Sort products alphabetically by product name
    public void sortByName()
    {
      ArrayList<Product>  tempProdList = new ArrayList<Product>();

      // adds all the products into the temporary products arraylist created
      for (Product p : products.values()) {
        tempProdList.add(p);
      }

      Collections.sort(tempProdList, Comparator.comparing((Product::getName)));
      
      for (Product prod : tempProdList) {
        prod.print();
      }
    }

    // Sort products alphabetically by customer name
    public void sortCustomersByName()
    {
      Collections.sort(customers);
    }

    //Sort book of by year published in increasing order by the given author 
    public void sortBookByAuthor(String author)
    {
      ArrayList<Book> bookList = new ArrayList<Book>();
      Book book;

      //for loop that checks which items in the products list are books and checks if the book is by the given author name, if it is it'll get added to the book list
      for (Product p: products.values()) {
        if (p.getCategory() == Product.Category.BOOKS) {
          book = (Book)p;
          if (book.getAuthor().equalsIgnoreCase(author)) {
            bookList.add(book);
          }
        }
      }
   
      //if book list is still empty that means that there is no books by that author in the product list making it invalid 
      if (bookList.size() == 0) {
        throw new InvalidAuthorNameException();
      }

      //sorts the book array
      Collections.sort(bookList);

      //print the sorted book list
      for (Book b: bookList) {
        b.print();
      }
    }

    /*
     * Given a customer id, print all the items in the customer's cart
     */
    public void printCart(String customerId) 
    {
      Customer c = checkValidCustomer(customerId);
      System.out.println("Current Items in Cart of Customer " + customerId); // Print the number of items in the customer's cart
      c.getCustCart().print();
      System.out.println();	
    }

    /*
     * Given a customer id, find the customers cart and add the product (given by the product Id) to the customer's cart
     */
    public void addCart(String productId, String customerId, String productOptions) 
    {
      Customer cust = checkValidCustomer(customerId);
      Product prod = checkValidProduct(productId);
    	
    	// Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
    	// if it does not throw exception
      if (!prod.validOptions(productOptions)) {
        throw new InvalidProductOptionsException("Product " + prod.getClass().getName() + " ProductId " + productId + " Invalid Options: " + productOptions);
      }

    	// Check if the product has stock available (i.e. not 0)
    	// if it does not throw exception
      if (prod.getStockCount(productOptions) == 0) {
        throw new OutOfStockException("ProductId " + productId + "Not in Stock");
      }

      cust.getCustCart().addItem(new CartItem(prod, productOptions));     
    }

    /*
     * Given a customer id, find the customers cart and remove the product (given by the product Id) from the customer's cart
     */
    public void removeCartItem(String customerId, String prodId)
    {
      boolean custflag = false;
      boolean prodflag = false;
      for (int i = 0; i < customers.size(); i++) {
        if (customers.get(i).getId().equals(customerId)) {
          custflag = true;
          for (Product p : products.values()) {
            if (p.getId().equals(prodId)) {
              customers.get(i).getCustCart().removeItem(prodId);
              prodflag = true;
            }
          }
        }
      }
      if (custflag == false) {
        throw new CustomerNotFoundException("Customer " + customerId + " Not Found");
      }
      if (prodflag == false) {
        throw new ProductNotFoundException("ProductId " + productId + " Not Found");
      }
    }

    /*
     * Given a customer id, order all the products found in the customer's cart
     */
    public void orderItems(String customerId)
    {
      Customer cust = checkValidCustomer(customerId);
      Cart c = cust.getCustCart();

      for (CartItem item : c.getCart()) {
        System.out.println("Order #" + orderProduct(item.getProduct().getId(), customerId, item.getProductOptions()));
      }
    }

    /*
     * prints the statistics of how many time each product was ordered in a sorted order from most to least ordered
     */
    public void stats() {
      ArrayList<Product> tempProdList = new ArrayList<Product>();

      for (Product p : products.values()) {
        tempProdList.add(p);
      }

      Collections.sort(tempProdList, new countComparator());

      for (Product prod : tempProdList) {
        System.out.printf("\nName: %-20s Id: %-5s Number of Times Ordered: %-5d", prod.getName(), prod.getId(), prod.getOrderCount());
      }
    }

    /*
     * Comparator for STATS action to help sort from most ordered to least ordered items
     */
    private class countComparator implements Comparator<Product>
	  {
      public int compare(Product a, Product b)
      {
        return b.getOrderCount() - a.getOrderCount();
      }
	  }

    /** Gets the product given the product id.
    * @return the id of the product.
    */
    public Product getProduct(String prodId) {
      for (Product p : products.values()) {
        if (p.getId().equals(prodId)) {
          return p;
        }
      }
      return null;
    }
    
    /** Rates a product.
    * @param productId - the id of the product.
    * @param rating - the rating of the product.
    */
    public void rateProduct(String productId, String rating) {
      boolean flag = false;
      for (Product p : products.values()) {
        if(p.getId().equals(productId)) {
          p.addRatings(rating);
          System.out.println("Thank you for taking the time to rate our product! :)");
          flag = true;
        }
      }

      if (flag == false) {
        throw new ProductNotFoundException();
      }
    }
    
    /** Calculates the average rating of a specific product.
    * @param productId - the id of the product.
    */
    public void avgProdRating(String productId) {
      boolean flag = false;
      for (Product p : products.values()) {
        if(p.getId().equals(productId)) {
          System.out.println("The average rating for product " + productId + " - " + p.getName() + " is: " + String.format("%.2f",p.averageRating()));
          flag = true;
        }
      }

      if (flag == false) {
        throw new ProductNotFoundException();
      }
    }


    /** Lists out all products from specified category that have a minimum average rating of the one given in the parameter or greater.
    * @param category - the category of the products.
    * @param ratingreq - minimum rating average that the products must be.
    */
    public void sortRatings(String category, String ratingreq) {
      ArrayList<Product> goodProducts = new ArrayList<Product>();
      
      if (Integer.valueOf(ratingreq) < 0 || Integer.valueOf(ratingreq) > 5) {
        throw new InvalidRatingException();
      }
      
      for (Product good : products.values()) {
        if ((good.getCategory().name().equalsIgnoreCase(category)) && (Integer.valueOf(ratingreq) <= good.averageRating())) {   
            goodProducts.add(good);
        }
      }

      for (Product prod : goodProducts) {
        prod.print();
        System.out.printf(" Average Rating: %-4.1f", prod.averageRating());
      }
    }

    /** Checks if the customer is valid given a customer id.
    * @param customerId - the string id of the customer.
    * @return the customer object if it is a valid customer otherwise it will throw an error
    */
    public Customer checkValidCustomer(String customerId) {
      for(int i = 0; i < customers.size(); i++) {
        if (customers.get(i).getId().equals(customerId)) { 
          return customers.get(i); 
        }
      }
      throw new CustomerNotFoundException("Customer " + customerId + " Not Found");
    }

    /** Checks if the product is valid given a product id.
    * @param productId - the string id of a product.
    * @return the product object if it is a valid product otherwise it will throw an error
    */
    public Product checkValidProduct(String productId) {
      for(Product p : products.values()) {
        if (p.getId().equals(productId)) { 
          return p;
        }
      }  
      throw new ProductNotFoundException("Product " + productId + " Not Found");
    }
}

//an exception class that is used when a customer is not found
class CustomerNotFoundException extends RuntimeException {

  public CustomerNotFoundException() {
      super("Customer Not Found");
  }

  public CustomerNotFoundException(String message) {
      super(message);
  }
}

//an exception class that is used when the user doesn't give a valid author name/the author name given isn't found
class InvalidAuthorNameException extends RuntimeException {

  public InvalidAuthorNameException() {
      super("Invalid Author Name");
  }

  public InvalidAuthorNameException(String message) {
      super(message);
  }
}

//an exception class that is used when the user gives an invalid customer address (doesn't enter anything)
class InvalidCustAddressException extends RuntimeException {

  public InvalidCustAddressException() {
      super("Invalid Customer Address");
  }

  public InvalidCustAddressException(String message) {
      super(message);
  }
}

//an exception class that is used when the user gives an invalid customer address (doesn't enter anything)
class InvalidCustNameException extends RuntimeException {

  public InvalidCustNameException() {
      super("Invalid Customer Name");
  }

  public InvalidCustNameException(String message) {
      super(message);
  }
}

//an exception class that is used when an invalid order number is given
class InvalidOrderNumException extends RuntimeException {

  public InvalidOrderNumException() {
      super("Invalid Order Number");
  }

  public InvalidOrderNumException(String message) {
      super(message);
  }
}

//an exception class that is used when the user inputs an invalid product option
class InvalidProductOptionsException extends RuntimeException {

  public InvalidProductOptionsException() {
      super("Invalid Product Options");
  }

  public InvalidProductOptionsException(String message) {
      super(message);
  }
}

//an exception class that is used when the product that is trying to be ordered is out of stock
class OutOfStockException extends RuntimeException {

  public OutOfStockException() {
      super("Product Out of Stock");
  }

  public OutOfStockException(String message) {
      super(message);
  }
}

//an exception class that is used when the product trying to be called doesn't exist 
class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException() {
      super("Product Not Found");
  }

  public ProductNotFoundException(String message) {
      super(message);
  }
}

//an exception class that is used when the user tries to rate a product greater than 5 or less than 1
class InvalidRatingException extends RuntimeException {

  public InvalidRatingException() {
      super("Invalid rating");
  }

  public InvalidRatingException(String message) {
      super(message);
  }
}

class NoItemsInCartException extends RuntimeException {
  public NoItemsInCartException() {
    super("There are no items currently in the cart. You need to place an item in the cart in order to remove it.");
  }

  public NoItemsInCartException(String message) {
      super(message);
  }
}