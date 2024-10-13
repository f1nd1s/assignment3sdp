import java.util.HashMap;
import java.util.Map;

class ProductCatalog {
    private Map<String, Double> products;

    public ProductCatalog() {
        products = new HashMap<>();
        products.put("Laptop", 1000.00);
        products.put("Phone", 700.00);
        products.put("Headphones", 200.00);
    }

    public Double getProductPrice(String productName) throws NullPointerException {
        return products.get(productName);
    }
}