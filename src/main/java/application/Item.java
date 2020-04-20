package main.java.application;

public class Item{

    public Item(String aName, float aPrice){
        this.name = aName;
        this.price = aPrice;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    private String name;
	private float price;
}