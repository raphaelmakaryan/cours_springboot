package fr.raphaelmakaryan.cours_springboot.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ClientService {
    public List<Client> arrayClients = new ArrayList<>();

    public void save(int id, String firstName, String lastName, String numberDrive, LocalDate birthDate) {
        Client newClient = new Client(id, firstName, lastName, numberDrive, birthDate);
        arrayClients.add(newClient);
    }

    public List<Client> findAll() {
        return arrayClients;
    }

    public Client findById(int id) {
        return arrayClients.get(id);
    }

    public int getLength() {
        return arrayClients.size();
    }

    public void editTestClient(int id) {
        for (int i = 0; i < arrayClients.size(); i++) {
            if (arrayClients.get(i).firstName.equals("Sarah") && arrayClients.get(i).id == id) {
                arrayClients.get(i).firstName = "Raphael";
            }
        }
    }

    public void delete(int id) {
        for (int i = 0; i < arrayClients.size(); i++) {
            if (arrayClients.get(i).id == id) {
                arrayClients.remove(i);
            }
        }
    }

    public String createCodeAlphanumeric() {
        Random rand = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz123567810";
        String result = "";
        int longueur = alphabet.length();
        for (int i = 0; i < 9; i++) {
            int k = rand.nextInt(longueur);
            result += alphabet.charAt(k);
        }
        return result;
    }
}
