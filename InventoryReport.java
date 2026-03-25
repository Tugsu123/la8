package warehouse;

import java.time.LocalDate;
import java.util.Map;

public class InventoryReport {
    private String warehouseId;
    private LocalDate fromDate;
    private LocalDate toDate;

    
    public static class ProductSummary {
        public String productName;
        public int openingBalance;
        public int totalIncoming;
        public int totalOutgoing;
        public int closingBalance;

        public ProductSummary(String productName, int openingBalance, int totalIncoming, int totalOutgoing) {
            this.productName = productName;
            this.openingBalance = openingBalance;
            this.totalIncoming = totalIncoming;
            this.totalOutgoing = totalOutgoing;
            this.closingBalance = openingBalance + totalIncoming - totalOutgoing;
        }
    }

    private Map<String, ProductSummary> summaries; 

    public InventoryReport(String warehouseId, LocalDate fromDate, LocalDate toDate,
                           Map<String, ProductSummary> summaries) {
        this.warehouseId = warehouseId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.summaries = summaries;
    }

    public void print() {
        System.out.println("=== INVENTORY REPORT ===");
        System.out.println("Warehouse: " + warehouseId);
        System.out.println("Period: " + fromDate + " to " + toDate);
        System.out.printf("%-20s %10s %10s %10s %10s%n",
                "Product", "Opening", "Incoming", "Outgoing", "Closing");
        System.out.println("-".repeat(62));
        for (ProductSummary s : summaries.values()) {
            System.out.printf("%-20s %10d %10d %10d %10d%n",
                    s.productName, s.openingBalance, s.totalIncoming, s.totalOutgoing, s.closingBalance);
        }
    }

    public Map<String, ProductSummary> getSummaries() { return summaries; }
    public LocalDate getFromDate() { return fromDate; }
    public LocalDate getToDate() { return toDate; }
}
