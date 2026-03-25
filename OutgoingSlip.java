package warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OutgoingSlip {
    private String slipId;
    private List<SlipItem> items;
    private String recipientName;   
    private LocalDate dispatchedDate;
    private String warehouseId;

    public OutgoingSlip(String slipId, String recipientName, LocalDate dispatchedDate, String warehouseId) {
        this.slipId = slipId;
        this.recipientName = recipientName;
        this.dispatchedDate = dispatchedDate;
        this.warehouseId = warehouseId;
        this.items = new ArrayList<>();
    }

    public void addItem(SlipItem item) {
        items.add(item);
    }

    public void print() {
        System.out.println("=== OUTGOING SLIP #" + slipId + " ===");
        System.out.println("Warehouse: " + warehouseId);
        System.out.println("Recipient: " + recipientName);
        System.out.println("Date: " + dispatchedDate);
        System.out.println("Items:");
        for (SlipItem item : items) {
            System.out.println("  - " + item.getProduct().getName()
                    + ": " + item.getQuantity() + " " + item.getProduct().getUnit());
        }
    }

    public String getSlipId() { return slipId; }
    public List<SlipItem> getItems() { return items; }
    public String getRecipientName() { return recipientName; }
    public LocalDate getDispatchedDate() { return dispatchedDate; }
    public String getWarehouseId() { return warehouseId; }
}
