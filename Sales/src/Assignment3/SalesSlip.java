package Assignment3;



import java.util.ArrayList;
import java.util.List;

public class SalesSlip {
    private List<SalesItem> items;

    public SalesSlip() {
        items = new ArrayList<>();
    }

    public void addItem(SalesItem item) {
        items.add(item);
    }

    public double getTotalSales() {
        double total = 0;
        for (SalesItem item : items) {
            total += item.getTotalCost();
        }
        return total;
    }

    public List<SalesItem> getItems() {
        return items;
    }
}