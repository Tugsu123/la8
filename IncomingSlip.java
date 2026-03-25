package warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IncomingSlip {
    private String slipId;
    private List<SlipItem> items;
    private String supplierName;   
    private LocalDate receivedDate;
    private String warehouseId;

    public IncomingSlip(String slipId, String supplierName, LocalDate receivedDate, String warehouseId) {
        this.slipId = slipId;
        this.supplierName = supplierName;
        this.receivedDate = receivedDate;
        this.warehouseId = warehouseId;
        this.items = new ArrayList<>();
    }

    public void addItem(SlipItem item) {
        items.add(item);
    }

    public void print() {
        System.out.println("=== INCOMING SLIP #" + slipId + " ===");
        System.out.println("Warehouse: " + warehouseId);
        System.out.println("Supplier: " + supplierName);
        System.out.println("Date: " + receivedDate);
        System.out.println("Items:");
        for (SlipItem item : items) {
            System.out.println("  - " + item.getProduct().getName()
                    + ": " + item.getQuantity() + " " + item.getProduct().getUnit());
        }
    }

    public String getSlipId() { return slipId; }
    public List<SlipItem> getItems() { return items; }
    public String getSupplierName() { return supplierName; }
    public LocalDate getReceivedDate() { return receivedDate; }
    public String getWarehouseId() { return warehouseId; }
}
