package Assignment3;


public class SalesItem {
    private String name;
    private double price;
    private int quantity;

    public SalesItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotalCost() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return name + " ($" + price + ", Qty: " + quantity + ")";
    }
}
