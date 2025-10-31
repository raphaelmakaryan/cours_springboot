package fr.raphaelmakaryan.cours_springboot.service;

import fr.raphaelmakaryan.cours_springboot.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDao extends JpaRepository<Client, Integer> {
    Client findById(int id);
    List<Client> findAll();
}
