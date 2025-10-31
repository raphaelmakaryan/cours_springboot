package fr.raphaelmakaryan.cours_springboot.controller;

import fr.raphaelmakaryan.cours_springboot.service.Client;
import fr.raphaelmakaryan.cours_springboot.service.ClientDao;
import fr.raphaelmakaryan.cours_springboot.service.ClientService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WebAppController {
    private final ClientService clientService;
    private final ClientDao clientDao;

    public WebAppController(ClientService clientService, ClientDao clientDao) {
        this.clientService = clientService;
        this.clientDao = clientDao;
    }

    @RequestMapping("/")
    public String index() {
        return "Hello World";
    }

    @RequestMapping(path = "/clients", method = RequestMethod.GET)
    public MappingJacksonValue clientsJPA() {
        List<Client> clients = clientDao.findAll();
        MappingJacksonValue allClients = new MappingJacksonValue(clients);
        return allClients;
    }

    @RequestMapping(path = "/clients/{id}", method = RequestMethod.GET)
    public Client getClients(@PathVariable(value = "id") int id) {
        return clientDao.findById(id);
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addClientPost() {
        try {
            Map<String, Object> response = new HashMap<>();
            String codeAlpha = clientService.createCodeAlphanumeric();
            String verifyLicense = "http://localhost:8081/licenses/" + codeAlpha;
            RestTemplate restTemplate = new RestTemplate();
            boolean result = restTemplate.getForObject(verifyLicense, Boolean.class);
            if (result) {
                clientService.createClient(codeAlpha, clientDao);
                response.put("success", true);
                response.put("message", "Votre client a été ajoutée !");
            } else {
                response.put("success", false);
                response.put("message", "Cet licenses existe deja !");
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Votre client n'a pas été ajoutée !");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Map<String, Object>> editClient(@PathVariable(value = "id") int idUSer) {
        try {
            Client client = clientDao.findById(idUSer);
            client.setfirst_name("Raphael");
            clientDao.save(client);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Votre client a été modifié !");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Votre client n'a pas été modifié !");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Map<String, Object>> deleteClient(@PathVariable(value = "id") int idUSer) {
        try {
            clientDao.delete(clientDao.findById(idUSer));
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Votre client a été supprimé !");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Votre client n'a pas été supprimé !");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
