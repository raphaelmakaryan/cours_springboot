package fr.raphaelmakaryan.cours_springboot.controller;

import fr.raphaelmakaryan.cours_springboot.service.Client;
import fr.raphaelmakaryan.cours_springboot.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WebAppController {
    private final ClientService clientService;

    public WebAppController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping("/")
    public String index() {
        return "Hello World";
    }

    @RequestMapping(path = "/clients", method = RequestMethod.GET)
    public List<Client> clients() {
        return clientService.displayClients();
    }

    @RequestMapping(path = "/clients/{id}", method = RequestMethod.GET)
    public Client getClients(@PathVariable(value = "id") int id) {
        return clientService.getClient(id);
    }

    @RequestMapping(value = "/addClient", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> addClient() {
        try {
            int id = clientService.getLength();
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Votre client a été ajoutée !");
            response.put("id", id);
            clientService.addClient(id, "Sarah", "Popelier", "105A5S2A47D", LocalDate.of(2025, 12, 2));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Votre client n'a pas été ajoutée !");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
