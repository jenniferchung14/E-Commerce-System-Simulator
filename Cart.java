//imported libraries
import java.util.ArrayList;

/* class Cart defines a registered cart for a customer. It keeps track of the customer Id for each cart along with the items in the cart. 
 	 
*/
public class Cart 
{
    String custId;
    ArrayList<CartItem> cart;

    /** Main constructor that creates a cart with the specified customer id.
    * 
    * @param custId - is a customer id of the cart belonging to the customer as a String.
    */
    public Cart(String custId)
    {
        this.custId = custId;
        this.cart= new ArrayList<CartItem>();
    }
        
    /** Gets the cart.
	* @return the cart arraylist that contains the items in the cart.
	*/
    public ArrayList<CartItem> getCart() {
        return cart;
    }

    /** Add an item to the cart.
    * @param item - the item to be added to the cart.
    */
    public void addItem(CartItem item) {
        cart.add(item);
    }
    
    /** Removes an item from the cart.
	* @return the item removed from the cart.
	*/
    public CartItem removeItem(String prodId) {
        if (cart.size() == 0) {
            throw new NoItemsInCartException();
        }
        for (CartItem item : cart) {
            if (item.getProduct().getId().equals(prodId)) {
                cart.remove(item);
                return item;
            }
        }
        return null;
    }

    /*
    * Print cart information including the number of items in the current cart
    */
    public void print()
    {
        System.out.println("Number of items in cart: " + cart.size());
        for (int i = 0; i < cart.size(); i++) {
            cart.get(i).print();
        }
    }

}
