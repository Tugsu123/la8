package warehouse;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        
        Product apple  = new Product("P001", "Apple",  "kg");
        Product orange = new Product("P002", "Orange", "kg");
        Product milk   = new Product("P003", "Milk",   "L");

        Warehouse wh = new Warehouse("WH01", "Main Street");
        Storekeeper keeper = new Storekeeper("SK01", "Bat", wh);

        
        keeper.receiveGoods("IN001",
                Arrays.asList(new SlipItem(apple, 100), new SlipItem(orange, 50)),
                "Supplier A", LocalDate.of(2025, 1, 5));

        keeper.receiveGoods("IN002",
                Arrays.asList(new SlipItem(milk, 200)),
                "Supplier B", LocalDate.of(2025, 1, 8));

        
        keeper.dispatchGoods("OUT001",
                Arrays.asList(new SlipItem(apple, 30), new SlipItem(milk, 40)),
                "Client X", LocalDate.of(2025, 1, 10));

        
        System.out.println();
        keeper.viewInventoryReport(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 31), null);

        
        System.out.println();
        keeper.viewInventoryReport(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 31),
                List.of("P001", "P003"));

        
        System.out.println();
        keeper.performStocktake("ST001", apple, LocalDate.of(2025, 1, 15), 65);
    }
}
