package org.example;

public class OrderService {
    private final PaymentService paymentService;
    private final InventoryService inventoryService;
    private final NotificationService notificationService;

    public OrderService(PaymentService paymentService, InventoryService inventoryService, NotificationService notificationService) {
        this.paymentService = paymentService;
        this.inventoryService = inventoryService;
        this.notificationService = notificationService;
    }

    public boolean placeOrder(String productId, String userId) {
        //Sprawdzenie dostepności produktu
        if (!inventoryService.isProductAvailable(productId)) {
            notificationService.sendOrderFailure(userId, productId);
            return false; //Produkt niedostępny
        }

        //Przetworzenie płatności
        boolean paymentSuccessful = paymentService.processPayment(userId, productId);
        if (!paymentSuccessful) {
            notificationService.sendOrderFailure(userId, productId);
            return false; //Płatność się nie udała
        }

        //Wysłanie powiadomienia do użytkownika
        notificationService.sendOrderConfirmation(userId, productId);

        return true;
    }
}
