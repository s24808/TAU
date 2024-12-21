import org.example.InventoryService;
import org.example.NotificationService;
import org.example.OrderService;
import org.example.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {
    @Mock
    private PaymentService paymentService;
    @Mock
    private InventoryService inventoryService;
    @Mock
    private NotificationService notificationService;
    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testOrderSuccessfullyPlaced() {
        //Konfiguracja
        when(inventoryService.isProductAvailable("product1")).thenReturn(true);
        when(paymentService.processPayment("user1", "product1")).thenReturn(true);

        //Wywołanie
        boolean result = orderService.placeOrder("product1", "user1");

        //Assercje
        assertTrue(result, "Zamówienie złożone pomyślnie");
        verify(notificationService).sendOrderConfirmation("user1", "product1");
    }

    @Test
    void testProductNotAvailable() {
        //Konfiguracja
        when(inventoryService.isProductAvailable("product1")).thenReturn(false);

        //Wywołanie
        boolean result = orderService.placeOrder("product1", "user1");

        //Assercje
        assertFalse(result, "Produkt jest niedostępny, zamówienie nie zostało złożone");
        verify(notificationService).sendOrderFailure("user1", "product1");
        verifyNoInteractions(paymentService);
    }

    @Test
    void testPaymentFailed() {
        //Konfiguracja
        when(inventoryService.isProductAvailable("product1")).thenReturn(true);
        when(paymentService.processPayment("user1", "product1")).thenReturn(false);

        //Wywołanie
        boolean result = orderService.placeOrder("product1", "user1");

        //Assercje
        assertFalse(result, "Płatność się nie powiodła");
        verify(notificationService).sendOrderFailure("user1", "product1");
        verify(notificationService, never()).sendOrderConfirmation(anyString(), anyString());
    }

    @Test
    void testPaymentServiceThrowsException() {
        //Konfiguracja
        when(inventoryService.isProductAvailable("product1")).thenReturn(true);
        when(paymentService.processPayment("user1", "product1")).thenThrow(new RuntimeException("Błąd płatności"));

        //Wywołanie z obsługą wyjątku
        assertThrows(RuntimeException.class, () -> orderService.placeOrder("product1", "user1"), "Error");
        verify(notificationService, never()).sendOrderConfirmation(anyString(), anyString());
    }
}