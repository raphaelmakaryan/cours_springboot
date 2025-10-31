package fr.raphaelmakaryan.cours_springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ClientService {

    public List<Client> arrayClients = new ArrayList<>();

    public void delete(int id) {
        for (int i = 0; i < arrayClients.size(); i++) {
            if (arrayClients.get(i).getId() == id) {
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

    public Client createClient(String codeAlpha, ClientDao clientDao) {
        Client newClient = new Client();
        newClient.setfirst_name("Sarah");
        newClient.setlastName("rrere");
        newClient.setlicense(codeAlpha);
        newClient.setbirthday(LocalDate.of(2025, 12, 2));
        return clientDao.save(newClient);
    }
}
