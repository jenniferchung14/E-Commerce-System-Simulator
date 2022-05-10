/* A book IS A product that has additional information - e.g. title, author

 	 A book also comes in different formats ("Paperback", "Hardcover", "EBook")
 	 
 	 The format is specified as a specific "stock type" in get/set/reduce stockCount methods.

*/
public class Book extends Product implements Comparable<Book>
{
  private String author;
  private String title;
  int year;

  // Stock related information NOTE: inherited stockCount variable is used for EBooks
  int paperbackStock;
  int hardcoverStock;
  
  /** Main constructor that creates a product with the specified name, id, category and stock count.
	* 
	* @param name - is the string name of the product.
	* @param id - is the string id of the book product.
	* @param price - is the price of book in double.
  * @param paperbackStock - is the stock number for paperback books.
	* @param hardcoverStock - is the stock number for hardcover books.
	* @param title - is the title of the book.
  * @param author - is the author of the book.
  * @param year - is the year the book was published.
	*/
  public Book(String name, String id, double price, int paperbackStock, int hardcoverStock, String title, String author, int year)
  {
  	// Make use of the constructor in the super class Product. Initialize additional Book instance variables. 
  	// Set category to BOOKS 
    super(name, id, price, 100000, Product.Category.BOOKS, 0);
    this.paperbackStock = paperbackStock;
    this.hardcoverStock = hardcoverStock;
    this.title = title;
    this.author = author;
    this.year = year;
  }
    
  // Check if a valid format  
  public boolean validOptions(String productOptions)
  {
  	// check productOptions for "Paperback" or "Hardcover" or "EBook"
  	// if it is one of these, return true, else return false
  	if (productOptions.equalsIgnoreCase("Paperback") || productOptions.equalsIgnoreCase("Hardcover") || productOptions.equalsIgnoreCase("EBook")) {
      return true;
    }
    else {
      return false;
    }
  }
  
  // Override getStockCount() in super class.
  public int getStockCount(String productOptions)
	{
  	// Use the productOptions to check for (and return) the number of stock for "Paperback" etc
  	// Use the variables paperbackStock and hardcoverStock at the top. 
  	// For "EBook", use the inherited stockCount variable.
    if (productOptions.equalsIgnoreCase("Paperback")) {
      return paperbackStock;
    }
    else if (productOptions.equalsIgnoreCase("Hardcover")) {
      return hardcoverStock;
    }
    else if (productOptions.equalsIgnoreCase("EBook")) {
      return super.getStockCount(productOptions);
    }
    return 0;
	}
  
  //Override setStockCount() in super class.
  public void setStockCount(int stockCount, String productOptions)
	{
    // Use the productOptions to check for (and set) the number of stock for "Paperback" etc
   	// Use the variables paperbackStock and hardcoverStock at the top. 
   	// For "EBook", set the inherited stockCount variable.
    if (productOptions.equalsIgnoreCase("Paperback")) {
      hardcoverStock = stockCount;
    }
    else if (productOptions.equalsIgnoreCase("Hardcover")) {
      paperbackStock = stockCount;
    }
    else if (productOptions.equalsIgnoreCase("EBook")) {
      super.setStockCount(stockCount, productOptions);
    }
	}
  
  /*
   * When a book is ordered, reduce the stock count for the specific stock type
   */
  public void reduceStockCount(String productOptions)
	{
  	// Use the productOptions to check for (and reduce) the number of stock for "Paperback" etc
   	// Use the variables paperbackStock and hardcoverStock at the top. 
   	// For "EBook", set the inherited stockCount variable.
    if (productOptions.equalsIgnoreCase("Paperback")) {
      paperbackStock --;
    }
    else if (productOptions.equalsIgnoreCase("Hardcover")) {
      hardcoverStock --;
    }
    else if (productOptions.equalsIgnoreCase("EBook")){
      super.reduceStockCount(productOptions);
    }
	}

  /** Gets the author of the book.
	* @return the author of the book.
	*/	
  public String getAuthor() 
  {
		return author;
	}

  /** Gets the year of when the book was published.
	* @return the year of when the book was published.
	*/
  public int getYear() 
  {
		return year;
	}

  //return a negative number if the original book year comes before the other when sorted
	//returns a 0 if they are the same book year
	//returns a positive number if the original book year comes after the other when sorted.
  public int compareTo(Book other) {
    return this.year - other.getYear();
  }
  
  /*
   * Print product information in super class and append Book specific information title and author
   */
  public void print()
  {
  	// Replace the line below.
  	// Make use of the super class print() method and append the title and author info. See the video
    super.print();
    System.out.printf(" Book Title: %-35s Author: %-15s Year: %-5s", title, author, year);
  }

}
