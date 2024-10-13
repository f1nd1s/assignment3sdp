import exceptions.NoLocationException;
import exceptions.NotEnoughCashException;
import exceptions.NotEnoughProductException;

import java.util.Scanner;

class ShoppingFacade {
    private ProductCatalog productCatalog;
    private PaymentProcessor paymentProcessor;
    private InventoryManager inventoryManager;
    private ShippingService shippingService;

    public ShoppingFacade() {
        productCatalog = new ProductCatalog();
        paymentProcessor = new PaymentProcessor();
        inventoryManager = new InventoryManager();
        shippingService = new ShippingService();
    }

    public void placeOrder() {
        Scanner scanner = new Scanner(System.in);
        String productName;
        int productQuantity;
        Double cash = 5000.0;
        double price;
        double deliveryCost;
        double totalPrice;

        System.out.print("Welcome to the Shopping Facade! If you want to buy something, print 'yes':");
        String choice = scanner.nextLine();

        while (choice.equalsIgnoreCase("yes")) {
            try {
                System.out.print("Enter your location: ");
                String location = scanner.nextLine();
                deliveryCost = shippingService.calculateDeliveryCost(location);

                System.out.print("What do you want to buy?");
                productName = scanner.nextLine();
                price = productCatalog.getProductPrice(productName);

                System.out.print("How much do you want to buy?");
                productQuantity = Integer.parseInt(scanner.nextLine());

                paymentProcessor.processPayment(cash, price, productQuantity, deliveryCost);
                totalPrice = price * productQuantity * deliveryCost;
                System.out.println("It will cost: " + totalPrice);
                System.out.print("Do you want to continue?");
                choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("yes")) {
                    System.out.println("Processing...");
                } else {
                    continue;
                }

                // reduces amount of items
                inventoryManager.reduceStock(productName, productQuantity);
                cash = cash - price * productQuantity * deliveryCost;
                System.out.println("Thank you for buying " + productName + "!\nYou have left: " + cash);

                System.out.print("Do you want to continue?");
                choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("no")) {
                    break;
                }
            } catch (NullPointerException e) {
                System.out.println("No such product found.");
            } catch (NotEnoughProductException e) {
                System.out.println("There is not enough such product to buy, sorry");
            } catch (NoLocationException e) {
                System.out.println("No such location found.");
            } catch (NotEnoughCashException e) {
                System.out.println("There is not enough cash to buy, sorry");
            }
            System.out.println("\n");
        }
        System.out.println("Thank you for shopping!");
    }
}