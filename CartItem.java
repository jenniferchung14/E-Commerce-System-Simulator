/* class CartItem defines a item found within a cart. It keeps track of the product and product options. 
 	 
*/
public class CartItem {
    
    Product product;
    String productOptions;

    /** Main constructor that creates a cart item with the specified product and product options.
    * 
    * @param product - is a product.
    * @param productOptions - is options of the product (specifically for shoes and book) as a String.
    */
    public CartItem(Product product, String productOptions)
    {
        this.product = product;
        this.productOptions = productOptions;
    }

    /** Gets the product.
	* @return the product.
	*/    
    public Product getProduct() {
        return product;
    }
	
    /** Gets the product options.
	* @return the options of the product.
	*/
    public String getProductOptions() 
    {
		return productOptions;
	}
    
    /*
    * Print the cart item information (same format as when you print a product)
    */
    public void print()
    {
        product.print();
    }

}
