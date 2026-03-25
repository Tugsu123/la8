package warehouse;

public class Product {
    private String productId;
    private String name;
    private String unit; 

    public Product(String productId, String name, String unit) {
        this.productId = productId;
        this.name = name;
        this.unit = unit;
    }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getUnit() { return unit; }

    @Override
    public String toString() {
        return name + " (" + unit + ")";
    }
}
