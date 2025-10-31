package fr.raphaelmakaryan.cours_springboot.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    public List<Client> arrayClients = new ArrayList<>();

    public void addClient(int id, String firstName, String lastName, String numberDrive, LocalDate birthDate) {
        Client newClient = new Client(id, firstName, lastName, numberDrive, birthDate);
        arrayClients.add(newClient);
    }

    public List<Client> displayClients() {
        return arrayClients;
    }

    public Client getClient(int id) {
        return arrayClients.get(id);
    }

    public int getLength() {
        return arrayClients.size();
    }
}
