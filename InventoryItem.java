package warehouse;

import java.time.LocalDate;

public class InventoryItem {
    private Product product;
    private int quantity;
    private LocalDate lastUpdated;

    public InventoryItem(Product product, int initialQuantity) {
        this.product = product;
        this.quantity = initialQuantity;
        this.lastUpdated = LocalDate.now();
    }

    public void addQuantity(int amount) {
        this.quantity += amount;
        this.lastUpdated = LocalDate.now();
    }

    public void removeQuantity(int amount) throws Exception {
        if (amount > this.quantity) {
            throw new Exception("Insufficient stock for product: " + product.getName());
        }
        this.quantity -= amount;
        this.lastUpdated = LocalDate.now();
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.lastUpdated = LocalDate.now();
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public LocalDate getLastUpdated() { return lastUpdated; }
}
