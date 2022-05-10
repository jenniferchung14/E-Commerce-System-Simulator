/*
 *  class Customer defines a registered customer. It keeps track of the customer's name and address. 
 *  A unique id is generated when when a new customer is created. 
 *  
 *  Implement the Comparable interface and compare two customers based on name
 */
public class Customer implements Comparable<Customer> 
{
	private String id;  
	private String name;
	private String shippingAddress;
	Cart cart;
	
	/** Main constructor that creates a customer with their specified id, name and address.
	* 
	* @param id - is the string id of the customer.
	* @param name - is the string name of the customer.
	* @param address - is the address of the customer in a string.
	*/
	public Customer(String id, String name, String address) 
	{
		this.id = id;
		this.name = name;
		this.shippingAddress = address;
		cart = new Cart(id);
	}

	/** Constructor for customer.
	* @param id - is the assigned id to a customer.
	*/
	public Customer(String id)
	{
		this.id = id;
		this.name = "";
		this.shippingAddress = "";
	}

	/** Constructor that creates a customer with their specified id, name, address and cart.
	* 
	* @param id - is the string id of the customer.
	* @param name - is the string name of the customer.
	* @param address - is the address of the customer in a string.
	* @param cart - is the cart assigned to the customer
	*/
	public Customer(String id, String name, String address, Cart cart) 
	{
		this.id = id;
		this.name = name;
		this.shippingAddress = address;
		this.cart = cart;
	}
	
	/** Gets the id of the customer.
	* @return the id of the customer.
	*/
	public String getId()
	{
		return id;
	}

	/*
	 * Set the id for the customer.
	 */	
	public void setId(String id)
	{
		this.id = id;
	}

	/** Gets the name of the customer.
	* @return the name of the customer.
	*/	
	public String getName()
	{
		return name;
	}
	
	/*
	 * Set the name of the customer.
	 */	
	public void setName(String name)
	{
		this.name = name;
	}

	/** Gets the shipping address of the customer.
	* @return the shipping address of the customer.
	*/	
	public String getShippingAddress()
	{
		return shippingAddress;
	}
	
	/*
	 * Set the shipping address of the customer.
	 */	
	public void setShippingAddress(String shippingAddress)
	{
		this.shippingAddress = shippingAddress;
	}
	
	/** Gets the cart that belongs to the customer.
	* @return the customer's cart.
	*/
	public Cart getCustCart() 
	{
		return cart;
	}

	/*
	 * Gives a string representation of the customer object.
	 * Prints the name, id and shipping address of the customer as a formatted string.
	 */
	public void print()
	{
		System.out.printf("\nName: %-20s ID: %3s Address: %-35s", name, id, shippingAddress);
	}
	
	/*
	 * Two customers are equal if they have the same product Id.
	 * This method is inherited from superclass Object and overridden here
	 */
	public boolean equals(Object other)
	{
		Customer otherC = (Customer) other;
		return this.id.equals(otherC.id);
	}

	//compareTo method (comparable interface) for SORTCUSTS action
	public int compareTo(Customer otherCust) {
		return this.getName().compareTo(otherCust.getName());
	}

}
