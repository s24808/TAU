**Krótki opis projektu:**
Projekt polega na stworzeniu prostej gry, w której użytkownik porusza się po planszy z punktu A (start) do punktu B (stop), omijając przeszkody.
Projekt zawiera logikę gry, testy jednostkowe oraz integrację z GitHub Actions.

**Instrukcja uruchomienia:**
1. Rzeczy potrzebne:
   -Java 17
   -Maven
2. Klonowanie repozytorium:
   -git clone https://github.com/s24808/TAU.git
3. Przejście do katalogu projektu:
   -cd 3/Zadanie3-GithubActions
4. Uruchamianie projektu:
   -mvn clean install
   -java -cp target/classes game.Main
5. Uruchamianie testów:
   -mvn test

**GitHub Actions**
Projekt został zintegrowany z GitHub Actions.
Workflow znajduję się w pliku maven.yml w tym miejscu: .github/workflows/maven.yml

**Zrzut ekranu potwierdzający wdrożenie GitHub Actions:**

![image](https://github.com/user-attachments/assets/69720ab2-d2df-4deb-9443-e51800f989ac)


