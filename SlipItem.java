package warehouse;

public class SlipItem {
    private Product product;
    private int quantity;

    public SlipItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
}
