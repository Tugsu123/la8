package warehouse;

import java.time.LocalDate;
import java.util.*;

public class Warehouse {
    private String warehouseId;
    private String location;
    private Map<String, InventoryItem> inventory;        
    private List<IncomingSlip> incomingSlips;
    private List<OutgoingSlip> outgoingSlips;
    private List<Stocktake> stocktakes;

    public Warehouse(String warehouseId, String location) {
        this.warehouseId = warehouseId;
        this.location = location;
        this.inventory = new HashMap<>();
        this.incomingSlips = new ArrayList<>();
        this.outgoingSlips = new ArrayList<>();
        this.stocktakes = new ArrayList<>();
    }

    
    public void receiveGoods(IncomingSlip slip) {
        for (SlipItem item : slip.getItems()) {
            String pid = item.getProduct().getProductId();
            inventory.computeIfAbsent(pid, k -> new InventoryItem(item.getProduct(), 0))
                     .addQuantity(item.getQuantity());
        }
        incomingSlips.add(slip);
    }

    
    public void dispatchGoods(OutgoingSlip slip) throws Exception {
        for (SlipItem item : slip.getItems()) {
            String pid = item.getProduct().getProductId();
            InventoryItem invItem = inventory.get(pid);
            if (invItem == null) throw new Exception("Product not found: " + item.getProduct().getName());
            invItem.removeQuantity(item.getQuantity());
        }
        outgoingSlips.add(slip);
    }

    
    public Stocktake performStocktake(String stocktakeId, Product product,
                                      LocalDate date, int actualQuantity) {
        String pid = product.getProductId();
        InventoryItem invItem = inventory.computeIfAbsent(pid, k -> new InventoryItem(product, 0));
        int systemQty = invItem.getQuantity();
        Stocktake stocktake = new Stocktake(stocktakeId, product, date, actualQuantity, systemQty);
        stocktake.apply(invItem);
        stocktakes.add(stocktake);
        return stocktake;
    }

    
    public InventoryReport generateReport(LocalDate fromDate, LocalDate toDate,
                                          List<String> productIdFilter) {
        Map<String, InventoryReport.ProductSummary> summaries = new LinkedHashMap<>();

        for (InventoryItem item : inventory.values()) {
            String pid = item.getProduct().getProductId();
            if (productIdFilter != null && !productIdFilter.isEmpty() && !productIdFilter.contains(pid)) {
                continue;
            }

            int totalIn = 0;
            int totalOut = 0;

            for (IncomingSlip slip : incomingSlips) {
                if (!slip.getReceivedDate().isBefore(fromDate) && !slip.getReceivedDate().isAfter(toDate)) {
                    for (SlipItem si : slip.getItems()) {
                        if (si.getProduct().getProductId().equals(pid)) totalIn += si.getQuantity();
                    }
                }
            }
            for (OutgoingSlip slip : outgoingSlips) {
                if (!slip.getDispatchedDate().isBefore(fromDate) && !slip.getDispatchedDate().isAfter(toDate)) {
                    for (SlipItem si : slip.getItems()) {
                        if (si.getProduct().getProductId().equals(pid)) totalOut += si.getQuantity();
                    }
                }
            }

            
            int openingBalance = item.getQuantity() - totalIn + totalOut;
            summaries.put(pid, new InventoryReport.ProductSummary(
                    item.getProduct().getName(), openingBalance, totalIn, totalOut));
        }

        return new InventoryReport(warehouseId, fromDate, toDate, summaries);
    }

    public String getWarehouseId() { return warehouseId; }
    public String getLocation() { return location; }
    public Map<String, InventoryItem> getInventory() { return inventory; }
    public List<IncomingSlip> getIncomingSlips() { return incomingSlips; }
    public List<OutgoingSlip> getOutgoingSlips() { return outgoingSlips; }
}
