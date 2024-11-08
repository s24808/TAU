**Scenariusz do testu testAmazonLaptopSearchAndProductDetails w klasie AmazonTest:**

**Cel testu:**
Sprawdzenie, czy wyszukiwanie produktu "Laptop" na stronie Amazon działa poprawnie oraz czy elementy takie jak tytuł, przycisk dodania do koszyka, cena produktu oraz komentarze są wyświetlane poprawnie.

**Kroki:**
1. Otwórz stronę główną Amazon.
2. Wprowadź frazę „Laptop” w polu wyszukiwania.
3. Kliknij przycisk „Szukaj” lub zatwierdź wyszukiwanie.
4. Sprawdź, czy pojawiły się wyniki wyszukiwania / jeśli nie - fail.
5. Kliknij pierwszy wynik wyszukiwania. 
6. Poczekaj na załadowanie strony szczegółów produktu.
7. Sprawdź, czy tytuł produktu jest widoczny / jeśli nie - fail.
8. Sprawdź, czy na stronie produktu znajduje się przycisk „Add to Cart” / jeśli nie - fail.
9. Sprawdź, czy cena produktu jest wyświetlana / jeśli nie - fail.
10. Sprawdź, czy sekcja z recenzjami jest widoczna na stronie produktu / jeśli nie - fail.

**Oczekiwany wynik:**
Test powinien przejść, jeśli elementy takie jak tytuł, przycisk dodania do koszyka, cena produktu oraz komentarze są wyświetlane poprawnie na stronie, a wyniki wyszukiwania pojawiły się po wprowadzeniu frazy "Laptop"

**Scenariusz do testu testGitHubSearch w klasie GithubTest:**

**Cel testu:**
Sprawdzenie, czy wyszukiwanie projektu "Selenium" na stronie GitHub działa poprawnie i czy wyniki są wyświetlane.

**Kroki:**
1. Otwórz stronę główną Github.
2. Kliknij ikonę wyszukiwania na stronie głównej.
3. Poczekaj na pojawienie się pola wyszukiwania.
4. Wprowadź frazę "Selenium" w polu wyszukiwania.
5. Zatwierź wyszukiwanie. 
6. Poczekaj na załadowanie wyników.
7. Sprawdź, czy pojawiły się wyniki / jeśli nie - fail.

**Oczekiwany wynik:**
Test powinien przejść, po wprowadzeniu frazy "Selenium" wyniki wyszukiwania wyświetlają się poprawnie

**Scenariusz do testu testGoogleSearch w klasie GoogleTest:**

**Cel testu:**
Sprawdzenie, czy wyszukiwanie frazy "Selenium" na stronie Google działa poprawnie i czy wyniki są wyświetlane.

**Kroki:**
1. Otwórz stronę główną Google.
2. Jeśli pojawi się okno z plikami cookies, kliknij przycisk "Zaakceptuj wszystko".
3. Wprowadź frazę "Selenium" w polu wyszukiwania.
5. Zatwierź wyszukiwanie. 
6. Poczekaj na załadowanie wyników.
7. Sprawdź, czy pojawiły się wyniki / jeśli nie - fail.
8. Sprawdź, czy pierwszy wynik zawiera frazę "Selenium" w nazwie / jeśli nie - fail.

**Oczekiwany wynik:**
Test powinien przejść, po wprowadzeniu frazy "Selenium" wyniki wyszukiwania wyświetlają się poprawnie i pierwszy wynik zawiera frazę "Selenium" w swojej treści.

**Scenariusz do testu testWikipediaSearch w klasie WikipediaTest:**

**Cel testu:**
Sprawdzenie, czy wyszukiwanie frazy "Selenium" na stronie Wikipedia działa poprawnie i czy wyniki są wyświetlane.

**Kroki:**
1. Otwórz stronę główną Wikipedii.
2. Wprowadź frazę "Selenium" w polu wyszukiwania.
5. Zatwierź wyszukiwanie. 
6. Poczekaj na załadowanie wyników.
7. Sprawdź, czy na stronie wyników widoczny jest nagłówek pasujący do podanej wcześniej frazy / jeśli nie - fail.

**Oczekiwany wynik:**
Test powinien przejść, po wprowadzeniu frazy "Selenium" wyniki wyszukiwania wyświetlają się poprawnie i strona z wynikami wyświetli nagłówek zawierającym słowo "Selenium".
