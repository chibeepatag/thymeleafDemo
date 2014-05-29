package javabeat.net.thymeleaf.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Java bean of the item. A collection of these is found in the catalog and the
 * cart.
 *
 * @author Celine Patag
 */
public class Item {

    private String code;
    private String name;
    private String description;
    private BigDecimal price;

    /**
     * Creates a new Item instance.
     *
     * @param code
     * @param name
     * @param description
     * @param price
     */
    public Item(String code, String name, String description, BigDecimal price) {
        this.code = code;
        this.name = name;
        this.description = description;        
        this.price = price.setScale(2, RoundingMode.HALF_DOWN);
    }

    /**
     *
     * @return item code
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @return name of the item
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return description of the item
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return price of the item
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Evaluates if two items are equal.
     *
     * @return true of code is equal, else false
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (this == null) {
            return false;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        return ((Item) o).getCode().equals(this.code);
    }
}
