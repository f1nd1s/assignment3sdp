import exceptions.NoLocationException;

import java.util.HashMap;
import java.util.Map;

class ShippingService {

    private Map<String, Double> locations;

    public ShippingService() {
        locations = new HashMap<>();
        locations.put("Astana", 1.05);
        locations.put("Almaty", 1.1);
        locations.put("Atyrau", 1.15);
        locations.put("Pavlodar", 1.2);
        locations.put("Turkestan", 1.25);
    }

    private boolean findLocation(String location) {
        return locations.containsKey(location);
    }

    public double calculateDeliveryCost(String location) throws NoLocationException {
        if (!findLocation(location)) {
            throw new NoLocationException("No such location");
        }
        return locations.get(location);
    }
}
