package fr.raphaelmakaryan.cours_springboot.controller;

import fr.raphaelmakaryan.cours_springboot.entity.Client;
import fr.raphaelmakaryan.cours_springboot.exception.BadRequestException;
import fr.raphaelmakaryan.cours_springboot.exception.ClientNotFindException;
import fr.raphaelmakaryan.cours_springboot.service.ClientDao;
import fr.raphaelmakaryan.cours_springboot.service.ClientService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @Operation(summary = "Voir tout les clients de la base de données H2", description = "Requête pour la récupération de tout les clients de la base de données H2")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))}), @ApiResponse(responseCode = "405", description = "Invalid input")})
    @RequestMapping(path = "/clients", method = RequestMethod.GET)
    public MappingJacksonValue clientsJPA() {
        List<Client> clients = clientDao.findAll();
        MappingJacksonValue allClients = new MappingJacksonValue(clients);
        return allClients;
    }

    @Operation(summary = "Voir un client spécifique de la base de données H2", description = "Requête pour la récupération d'un client de la base de données H2")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))}), @ApiResponse(responseCode = "405", description = "Invalid input")})
    @RequestMapping(path = "/clients/{id}", method = RequestMethod.GET)
    public Client getClients(@Parameter(description = "Identifiant du compte du client", required = true) @PathVariable(value = "id") int id) {
        try {
            return clientDao.findById(id);
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Operation(summary = "Crée un nouveau client dans la base de données H2", description = "Requête pour crée/ajouter client dans la base de données H2")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation"), @ApiResponse(responseCode = "405", description = "Invalid input")})
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

    @Operation(summary = "Mettre à jour un client dans la base de données H2", description = "Requête pour mettre a jour un client dans la base de données H2")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation"), @ApiResponse(responseCode = "405", description = "Invalid input")})
    @PutMapping("/clients/{id}")
    public ResponseEntity<Map<String, Object>> editClient(@Parameter(description = "Identifiant du compte du client", required = true) @PathVariable(value = "id") int idUSer) {
        try {
            Client client = clientDao.findById(idUSer);
            client.setfirst_name("Raphael");
            clientDao.save(client);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Votre client a été modifié !");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Operation(summary = "Supprimer un client de la base de données H2", description = "Requête pour supprimer un client de la base de données H2")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful operation"), @ApiResponse(responseCode = "405", description = "Invalid input")})
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Map<String, Object>> deleteClient(@Parameter(description = "Identifiant du compte du client", required = true) @PathVariable(value = "id") int idUSer) {
        Client client = clientDao.findById(idUSer);
        if (client.getId() != idUSer) {
            throw new ClientNotFindException(idUSer);
        }
        clientDao.delete(client);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Votre client a été supprimé !");
        return ResponseEntity.ok(response);
    }
}
