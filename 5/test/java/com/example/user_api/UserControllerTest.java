package com.example.user_api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc; //Mock

    //Tworzenie nowy kontroler dla każdego testu osobno
    @BeforeEach
    public void resetController() {
        UserController userController = new UserController();
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    //Test sprawdzający, czy lista użytkowników jest pusta przy pierwszym wywołaniu
    @Test
    public void shouldReturnEmptyListOfUsers() throws Exception {
        mockMvc.perform(get("/users")) //GET na endpoint /users
                .andExpect(status().isOk()) //Sprwadzenie statusu, czy jest OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) //JSON
                .andExpect(content().json("[]")); //Sprawdzenie czy lista jest pusta
    }

    //Test sprawdzający dodawanie nowego użytkownika
    @Test
    public void shouldAddNewUser() throws Exception {
        mockMvc.perform(post("/users") //POST na endpoint /users
                        .contentType(MediaType.APPLICATION_JSON) //JSON
                        .content("{\"name\":\"Jan Kowalski\", \"email\":\"jan@kowalski.pl\"}")) //Dane użytkownika w JSON
                .andExpect(status().isCreated()) //Sprwadzenie statusu, czy jest CREATED
                .andExpect(jsonPath("$.name").value("Jan Kowalski")) //Sprawdzenie imienia
                .andExpect(jsonPath("$.email").value("jan@kowalski.pl")); //Sprawdzenie maila
    }

    // Test sprawdzający pobieranie użytkownika po ID
    @Test
    public void shouldReturnUserById() throws Exception {
        // Dodanie nowego użytkownika
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Anna Nowak\", \"email\":\"anna@nowak.pl\"}"))
                .andExpect(status().isCreated());

        //GET na endpoint /users/1 i sprawdzenie danych użytkownika
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Anna Nowak")) //Sprawdzenie imienia
                .andExpect(jsonPath("$.email").value("anna@nowak.pl")); //Sprawdzenie maila
    }

    //Test sprawdzający aktualizację danych użytkownika
    @Test
    public void shouldUpdateUser() throws Exception {
        //Dodanie nowego użytkownika
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Jan Kowalski\", \"email\":\"jan@kowalski.pl\"}"))
                .andExpect(status().isCreated());

        //Aktualizowanie danych użytkownika o ID 1
        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Jan Nowak\", \"email\":\"jan@nowak.pl\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jan Nowak")) //Sprawdzenie imienia po aktualizacji
                .andExpect(jsonPath("$.email").value("jan@nowak.pl")); //Sprawdzenie maila po aktualizacji
    }

    //Test sprawdzający usunięcie użytkownika
    @Test
    public void shouldDeleteUser() throws Exception {
        //Dodanie nowego użytkownika
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Jan Kowalski\", \"email\":\"jan@kowalski.pl\"}"))
                .andExpect(status().isCreated());

        //Usuwanie użytkownika o ID 1
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isNoContent()); //Sprwadzenie statusu, czy jest NO CONTENT

        //Sprawdzenie, czy użytkownik został usunięty
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isNotFound()); //Sprwadzenie statusu, czy jest NOT FOUND
    }

    //Test sprawdzający pobranie nieistniejącego użytkownika
    @Test
    public void shouldReturnNotFoundForNonExistingUser() throws Exception {
        //GET na endpoint /users/999
        mockMvc.perform(get("/users/999"))
                .andExpect(status().isNotFound()); //Sprwadzenie statusu, czy jest NOT FOUND
    }
}
