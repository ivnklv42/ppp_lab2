public class Product {
    private String title;
    private double price;
    private ProductType productType;

    public Product(String title, double price, ProductType productType) {
        this.title = title;
        this.price = price;
        this.productType = productType;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductType getProductType() {
        return this.productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
