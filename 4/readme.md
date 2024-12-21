W folderze main znajdują się 4 klasy: InventoryService, NotificationService, OrderSerivce i PaymentService.

1.**OrderService**
Główna klasa odpowiedzialna za obsługę zamówień. Jej obowiązki obejmują:
- Sprawdzanie dostępności produktu za pomocą `InventoryService`.
- Przetwarzanie płatności za pomocą `PaymentService`.
- Wysyłanie powiadomień za pomocą `NotificationService`.

2.**PaymentService**
Obsługuje przetwarzanie płatności. Symuluje powodzenie lub niepowodzenie płatności.

3.**InventoryService**
Zarządza zapasami, sprawdzając dostępność produktów.

4.**NotificationService**
Wysyła powiadomienia do użytkowników dotyczące statusu ich zamówień.

**Testy jednostkowe z użyciem Mockito**

Testy jednostkowe dla klasy `OrderService` są zaimplementowane z wykorzystaniem Mockito i JUnit. 
Testy obejmują następujące scenariusze:

1. **Zamówienie złożone pomyślnie**
   - Produkt jest dostępny.
   - Płatność została przetworzona pomyślnie.
   - Powiadomienie o sukcesie zostało wysłane.

2. **Produkt niedostępny**
   - Produkt jest niedostępny w magazynie.
   - Przetwarzanie płatności nie jest wykonywane.
   - Powiadomienie o niepowodzeniu zostało wysłane.

3. **Płatność nieudana**
   - Produkt jest dostępny.
   - Płatność nie powiodła się.
   - Powiadomienie o niepowodzeniu zostało wysłane.

4. **Usługa płatności rzuca wyjątek**
   - Produkt jest dostępny.
   - Usługa płatności rzuca wyjątek.
   - Powiadomienie o niepowodzeniu zostało wysłane.

**Dependencje:**

   <dependencies>
       <dependency>
           <groupId>org.junit.jupiter</groupId>
           <artifactId>junit-jupiter</artifactId>
           <version>5.10.0</version>
           <scope>test</scope>
       </dependency>
       <dependency>
           <groupId>org.mockito</groupId>
           <artifactId>mockito-core</artifactId>
           <version>5.6.0</version>
           <scope>test</scope>
       </dependency>
       <dependency>
           <groupId>org.mockito</groupId>
           <artifactId>mockito-junit-jupiter</artifactId>
           <version>5.6.0</version>
           <scope>test</scope>
       </dependency>
   </dependencies>
