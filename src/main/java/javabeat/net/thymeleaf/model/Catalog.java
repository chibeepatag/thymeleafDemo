/**
 * 
 */
package javabeat.net.thymeleaf.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Celine Patag
 * 
 */
public class Catalog {
	private static Map<String, Item> items;

	static {
		items = new HashMap<String, Item>();
		items.put("hat001", new Item("hat001", "Hat",
				"Stylish bowler hat (SALE!)", new BigDecimal(19.35)));
		items.put("dog001", new Item("dog001", "Dog",
				"Chocolate labrador puppy", new BigDecimal(87.56)));
		items.put("sou001", new Item("sou001", "Soup",
				"Can of tasty tomato soup", new BigDecimal(95.65)));
		items.put("cha001", new Item("cha001", "Chair",
				"Swivelling office chair", new BigDecimal(45.65)));
		items.put("str001", new Item("str001", "String",
				"Metric tonne of bailing twine", new BigDecimal(65.36)));
		items.put("qua001", new Item("qua001", "Quark",
				"Everyone's favorite sub-atomic particle", new BigDecimal(23.98)));
	}

	/**
	 * 
	 * @return collection of items in the Catalog
	 */
	public Collection<Item> getAllItems() {
		return items.values();
	}

	/**
	 * Checks if the catalog map contains the item
	 * 
	 * @param itemCode
	 * @return boolean if the item is in the map
	 */
	public boolean containsItem(String itemCode) {
		return items.containsKey(itemCode);
	}

	/**
	 * Retrieves the item from the catalog map
	 * 
	 * @param itemCode
	 * @return item associated with the itemCode
	 */
	public Item getItem(String itemCode) {
		return items.get(itemCode);
	}

}
