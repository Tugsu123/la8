package warehouse;

import java.time.LocalDate;
import java.util.List;

public class Storekeeper {
    private String storekeeperID;
    private String name;
    private Warehouse warehouse;

    public Storekeeper(String storekeeperID, String name, Warehouse warehouse) {
        this.storekeeperID = storekeeperID;
        this.name = name;
        this.warehouse = warehouse;
    }

    
    public IncomingSlip receiveGoods(String slipId, List<SlipItem> items,
                                     String supplierName, LocalDate receivedDate) {
        IncomingSlip slip = new IncomingSlip(slipId, supplierName, receivedDate, warehouse.getWarehouseId());
        for (SlipItem item : items) {
            slip.addItem(item);
        }
        warehouse.receiveGoods(slip);
        slip.print();
        return slip;
    }

    
    public OutgoingSlip dispatchGoods(String slipId, List<SlipItem> items,
                                      String recipientName, LocalDate dispatchedDate) throws Exception {
        OutgoingSlip slip = new OutgoingSlip(slipId, recipientName, dispatchedDate, warehouse.getWarehouseId());
        for (SlipItem item : items) {
            slip.addItem(item);
        }
        warehouse.dispatchGoods(slip);
        slip.print();
        return slip;
    }

    
    public InventoryReport viewInventoryReport(LocalDate fromDate, LocalDate toDate,
                                               List<String> productIdFilter) {
        InventoryReport report = warehouse.generateReport(fromDate, toDate, productIdFilter);
        report.print();
        return report;
    }

    
    public Stocktake performStocktake(String stocktakeId, Product product,
                                      LocalDate date, int actualQuantity) {
        Stocktake st = warehouse.performStocktake(stocktakeId, product, date, actualQuantity);
        st.printResult();
        return st;
    }

    public String getStorekeeperID() { return storekeeperID; }
    public String getName() { return name; }
    public Warehouse getWarehouse() { return warehouse; }
}
