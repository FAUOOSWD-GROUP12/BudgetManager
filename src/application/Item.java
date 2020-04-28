package application;

/**
 * Item Object will store an items name and price value.
 * To be used when a new purchase is made.
 */
public class Item {
    /**
     * Constructor will assign the Item a name and price Value.
     *
     * @param aName     - name of the item purchased.
     * @param aPrice    - price of the item purchased.
     * @param aCategory - category the item belongs to.
     */
    public Item(String aCategory, String aName, double aPrice) {
        this.name = aName;
        this.price = aPrice;
        this.category = aCategory;
    }

    /**
     * getPrice gets the items price.
     *
     * @return the price of the item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * getName() returns the name of the item.
     *
     * @return String name of the item purchased.
     */
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name + " $" + price;
    }

    private final String name;
    private final double price;
    private final String category;
}