/**
 * Programmer: Jennifer Chung
 * Student ID: 501096156
 * Course Code: CPS 209-061
 * Due Date: 14/04/22
 */

/*
 * class Product defines a product for sale by the system. 
 * 
 * A product belongs to one of the 5 categories below. 
 * 
 * Some products also have various options (e.g. size, color, format, style, ...). The options can affect
 * the stock count(s). In this generic class Product, product options are not used in get/set/reduce stockCount() methods  
 * 
 * Some products
 */
public class Product
{
	public static enum Category {GENERAL, CLOTHING, BOOKS, FURNITURE, COMPUTERS, SHOES}; //NOTE: added a shoe category for the shoes class
	
	private String name;
	private String id;
	private Category category;
	private double price;
	private int stockCount;
	private int orderCount; //NEW
	int[] ratings;
	int numOfRatings;
	
	/** Default constructor that creates a product, assigning it a default name, id, category and stock count.
	*
	*/
	public Product()
	{
		this.name = "Product";
		this.id = "001";
		this.category = Category.GENERAL;
		this.stockCount = 0;
		this.orderCount = 0;
		this.orderCount = 0;
		ratings = new int[5];
		numOfRatings = 0;
	}
	
	/** Constructor for product.
	* 
	* @param id - is the assigned id to a product.
	*/
	public Product(String id)
	{
		this("Product", id, 0, 0, Category.GENERAL, 0);
		ratings = new int[5];
		numOfRatings = 0;
	}

	/** Main constructor that creates a product with the specified name, id, price, stockCount and category.
	* 
	* @param name - is the string name of the product.
	* @param id - is the string id of the product.
	* @param price - is the price of product in double.
	* @param stock - is the stock number of the product.
	* @param category - is the category of the product.
	* @param orderCount - is the integer to keep tracket of number of times a product has been ordered
	*/
	public Product(String name, String id, double price, int stock, Category category, int orderCount)
	{
		this.name = name;
		this.id = id;
		this.price = price;
		this.stockCount = stock;
		this.category = category;
		this.orderCount = orderCount;
		ratings = new int[5];
		numOfRatings = 0;
	}
	
	/*
	 * This method returns true if the productOptions string is null or an empty string in class Product. In subclasses, this method will be overridden
	 * and will check to see if the options specified are valid for this product.
	 */
	public boolean validOptions(String productOptions)
	{
		if (productOptions == null || productOptions.equals("")) {
			return true;
		}
		return false;

	}

	/** Gets the category of the product.
	* @return the category of the product.
	*/
	public Category getCategory()
	{
		return category;
	}

	/** Gets the name of the product.
	* @return the name of the product as a string.
	*/
	public String getName()
	{
		return name;
	}

	/*
	 * Set the name for this product.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/** Gets the id of the product.
	* @return the id of the product as a string.
	*/
	public String getId()
	{
		return id;
	}

	/*
	 * Set the id for this product.
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/** Gets the price of the product.
	* @return the price of the product as a double.
	*/
	public double getPrice()
	{
		return price;
	}

	/*
	 * Set the price for this product.
	 */
	public void setPrice(double price)
	{
		this.price = price;
	}

	/*
	 * Return the number of items currently in stock for this product
	 * Note: in this general class, the productOptions parameter is not used. It may be used
	 * in subclasses.
	 */
	public int getStockCount(String productOptions)
	{
		return stockCount;
	}

	/*
	 * Set (or replenish) the number of items currently in stock for this product
	 * Note: in this general class, the productOptions parameter is not used. It may be used
	 * in subclasses.
	 */
	public void setStockCount(int stockCount, String productOptions)
	{
		this.stockCount = stockCount;
	}

	/*
	 * Reduce the number of items currently in stock for this product by 1 (called when a product has
	 * been ordered by a customer)
	 * Note: in this general class, the productOptions parameter is not used. It may be used
	 * in subclasses.
	 */
	public void reduceStockCount(String productOIptions)
	{
		stockCount--;
	}

	/** Gets the number of time a product has been order.
	* @return the count for the number of times a product was ordered.
	*/
	public int getOrderCount() //NEW
	{
		return orderCount;
	}

	/*
	 * Set the counter that keeps track of number of time a product was ordered.
	 */	
	public void setOrderCount(int orderCount) //NEw
	{
		this.orderCount = orderCount;
	}

	/*
	 * Gives a string representation of the product object.
	 * Prints the id, category, name and price of the product as a formatted string.
	 */
	public void print()
	{
		System.out.printf("\nId: %-5s Category: %-9s Name: %-20s Price: %7.1f", id, category, name, price);
	}
	
	/*
	 * Two products are equal if they have the same product Id.
	 * This method is inherited from superclass Object and overridden here
	 */
	public boolean equals(Object other)
	{
		Product otherP = (Product) other;
		return this.id.equals(otherP.id);
	}

	public int[] getRatings() { //NEW
		return ratings;
	}

	public double averageRating() { //NEW
		double totalRatings = 0;
		for (int i = 0; i < ratings.length; i++) {
			totalRatings += ratings[i]*(i+1);
		}

		if (numOfRatings != 0) {
			return totalRatings/numOfRatings;
		}
		return 0;
	}

	public void addRatings(String rating) { //NEW
		numOfRatings++;
		if (rating.equals("1")) {
			ratings[0]++;
		}
		else if (rating.equals("2")) {
			ratings[1]++;
		}
		else if (rating.equals("3")) {
			ratings[2]++;
		}
		else if (rating.equals("4")) {
			ratings[3]++;
		}
		else if (rating.equals("5")) {
			ratings[4]++;
		}
		else {
			throw new InvalidRatingException();
		}
	}
}
