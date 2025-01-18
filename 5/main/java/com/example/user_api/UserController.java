package com.example.user_api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users") //Prefixy dla endpointów
public class UserController {

    //Mapa z danymi użytkowników
    private final Map<Integer, User> users = new HashMap<>();

    //Zwracanie listy wszystkich użytkowników - GET
    @GetMapping
    public List<User> getUsers() {
        //Wyświetlenie jako lista
        return new ArrayList<>(users.values());
    }

    //Zwracanie użytkownika o konkretnym ID - GET
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        //Pobieranie użtkownika na podstawie ID
        User user = users.get(id);
        //Jeśli znajdzie, wyświetla 200, czyli OK, jak nie to 404, czyli NOTFOUND
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    //Dodanie nowego użytkownika - POST
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        //Ustawienie id na podstawie rozmiaru mapy
        user.setId(users.size() + 1);
        //Dodanie użytkownika do mapy
        users.put(user.getId(), user);
        //Zwraca 201, czyli Created z danymi nowego użytkownika
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    //Aktualizacja danych istniejącego użytkownika - PUT
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        //Pobieranie użytkownika z mapy
        User existingUser = users.get(id);
        if (existingUser != null) {
            //Ustawienie Id dla użykownika i zapisanie w mapie
            updatedUser.setId(id);
            users.put(id, updatedUser);
            //Zwraca 200 z danymi zaktualizowanego użytkownika
            return ResponseEntity.ok(updatedUser);
        }
        //Jeśli nie istnieje, zwraca 404, czyli NOTFOUND
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        //Sprwadzenie czy użytkownik istnieje
        if (users.containsKey(id)) {
            //Usuwanie z mapy
            users.remove(id);
            //Zwraca 204, czyli No content, jesli usuwanie się powiedzie
            return ResponseEntity.noContent().build();
        }
        //Jeśli nie istnieje zwraca 404
        return ResponseEntity.notFound().build();
    }

    //Usuwanie mapy (Reset między testami)
    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearUsers() {
        //Czysczenie mapy
        users.clear();
        //Zwraca 204
        return ResponseEntity.noContent().build();
    }

}
