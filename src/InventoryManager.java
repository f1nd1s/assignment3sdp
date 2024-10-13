import exceptions.NotEnoughProductException;

import java.util.HashMap;
import java.util.Map;

class InventoryManager {
    private Map<String, Integer> stock;

    public InventoryManager() {
        stock = new HashMap<>();
        stock.put("Laptop", 10);
        stock.put("Phone", 20);
        stock.put("Headphones", 30);
    }

    public void reduceStock(String productName, int productQuantity) throws NotEnoughProductException {
        int stockQuantity = stock.get(productName);

        if (stockQuantity < productQuantity ) {
            throw new NotEnoughProductException("<0");
        }
        stock.put(productName, stockQuantity - productQuantity);
    }
}