package warehouse;

import java.time.LocalDate;

public class Stocktake {
    private String stocktakeId;
    private Product product;
    private LocalDate stocktakeDate;
    private int actualQuantity;
    private int systemQuantity;   
    private int difference;       

    public Stocktake(String stocktakeId, Product product, LocalDate stocktakeDate,
                     int actualQuantity, int systemQuantity) {
        this.stocktakeId = stocktakeId;
        this.product = product;
        this.stocktakeDate = stocktakeDate;
        this.actualQuantity = actualQuantity;
        this.systemQuantity = systemQuantity;
        this.difference = actualQuantity - systemQuantity;
    }

    public void apply(InventoryItem inventoryItem) {
        inventoryItem.setQuantity(actualQuantity);
    }

    public void printResult() {
        System.out.println("=== STOCKTAKE #" + stocktakeId + " ===");
        System.out.println("Product: " + product.getName());
        System.out.println("Date: " + stocktakeDate);
        System.out.println("System qty: " + systemQuantity);
        System.out.println("Actual qty: " + actualQuantity);
        if (difference > 0) {
            System.out.println("Surplus: +" + difference);
        } else if (difference < 0) {
            System.out.println("Deficit: " + difference);
        } else {
            System.out.println("Exact match.");
        }
    }

    public String getStocktakeId() { return stocktakeId; }
    public Product getProduct() { return product; }
    public LocalDate getStocktakeDate() { return stocktakeDate; }
    public int getActualQuantity() { return actualQuantity; }
    public int getSystemQuantity() { return systemQuantity; }
    public int getDifference() { return difference; }
}
