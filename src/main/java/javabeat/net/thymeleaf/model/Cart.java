/**
 * 
 */
package javabeat.net.thymeleaf.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Celine Patag
 * 
 */
public class Cart {
	private HashMap<Item, Integer> contents;

	/**
	 * Creates a new Cart instance
	 */
	public Cart() {
		contents = new HashMap<Item, Integer>();
	}

	/**
	 * Adds a named item to the cart
	 * 
	 * @param itemCode
	 */
	public void addItem(String itemCode) {

		Catalog catalog = new Catalog();

		if (catalog.containsItem(itemCode)) {
			Item item = catalog.getItem(itemCode);

			int newQuantity = 1;
			if (contents.containsKey(item)) {
				Integer currentQuantity = contents.get(item);
				newQuantity += currentQuantity.intValue();
			}

			contents.put(item, new Integer(newQuantity));
		}
	}

	/**
	 * Removes the named item from the cart
	 * 
	 * @param itemCode
	 */
	public void removeItem(String itemCode) {
		Item item = new Catalog().getItem(itemCode);
		Integer quantity = contents.get(item);
		if (quantity > 1) {
			Integer newQuantity = --quantity;
			contents.put(item, newQuantity);
			System.out.println("new quantity: " + newQuantity);
		} else {
			contents.remove(item);
		}
	}

	/**
	 * Clears the content of the cart.
	 */
	public void clearCart() {
		contents = new HashMap<Item, Integer>();
	}

	/**
	 * Calculates the total of the items in the shopping cart.
	 * 
	 * @return the String representation of the total.
	 */
	private String getCartTotal() {
		int total = 0;

		for (Iterator<Item> I = contents.keySet().iterator(); I.hasNext();) {
			Item item = I.next();
			int itemQuantity = contents.get(item).intValue();

			// total += item.getPrice().multiply(new BigDecimal(itemQuantity));
		}

		return "$" + new BigDecimal(total).movePointLeft(2);
	}

	public HashMap<Item, Integer> getContents() {
		return contents;
	}

	public String toJSon() {
		StringBuffer jsonSb = new StringBuffer("{\"cart\": {");
		addJsonAttribute(jsonSb, "generated",
				Long.toString(System.currentTimeMillis()), false);
		addJsonAttribute(jsonSb, "total", this.getCartTotal(), false);
		jsonSb.append("\"item\" : [");

		for (Iterator<Item> I = contents.keySet().iterator(); I.hasNext();) {
			jsonSb.append("{");
			Item item = I.next();
			addJsonAttribute(jsonSb, "code", item.getCode(), false);
			addJsonAttribute(jsonSb, "name", item.getName(), false);
			addJsonAttribute(jsonSb, "quantity", contents.get(item).toString(),
					false);
			int itemQuantity = contents.get(item).intValue();
			
			BigDecimal priceQty = item.getPrice().multiply(new BigDecimal(itemQuantity).setScale(2, RoundingMode.HALF_DOWN));
			addJsonAttribute(jsonSb, "price", priceQty.toString(), true);
			jsonSb.append("}");
			if (I.hasNext()) {
				jsonSb.append(",");
			}
		}
		jsonSb.append("]}}");
		return jsonSb.toString();
	}
	
	  private void addJsonAttribute(StringBuffer sb, String attribute, String value, boolean isLast){
	      sb.append("\"");
	      sb.append(attribute);
	      sb.append("\" : \"");
	      sb.append(value);
	      sb.append("\"");
	      if(!isLast){
	          sb.append(",");
	      }
	      
	  }
}
