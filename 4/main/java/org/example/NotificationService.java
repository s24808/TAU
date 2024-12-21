package org.example;

public class NotificationService {
    public void sendOrderConfirmation(String userId, String productId) {
        //Powiadomienie o pomyślnym zamówieniu
        System.out.println("Powiadomienie: Zamówienie z produktem: " + productId + " zostało pomyślnie złożone przez użytkownika: " + userId);
    }

    public void sendOrderFailure(String userId, String productId) {
        //Powiadomienie o nieudanym zamówieniu
        System.out.println("Powiadomienie: Nie udało się złożyć zamówienia z produktem: " + productId + " dla użytkownika: " + userId);
    }
}
