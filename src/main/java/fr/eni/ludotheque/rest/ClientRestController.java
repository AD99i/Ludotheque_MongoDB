package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dto.AdresseDTO;
import fr.eni.ludotheque.dto.ClientDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
//@Controller + @ResponseBody
public class ClientRestController {

    static Logger log = LoggerFactory.getLogger(ClientRestController.class.getName());


    @NonNull
    private ClientService clientService;

    @PostMapping("/clients")
    public ResponseEntity<?> ajouterClient(@RequestBody ClientDTO clientDTO) {

        log.trace("niveau trace");
        log.debug("niveau debug");
        log.info("ajouterClient");
        log.warn("niveau warn");
        log.error("niveau error");


         Client client = clientService.ajouterClient(clientDTO);


         return  ResponseEntity.status(HttpStatus.CREATED).body(client);

    }


    @DeleteMapping("clients/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable String id) {
        clientService.supprimerClient(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("clients/{id}")
    public ResponseEntity<?> updateClient(@PathVariable String id, @RequestBody ClientDTO clientDTO) {
        clientService.modifierClient(id, clientDTO);
        return ResponseEntity.status(HttpStatus.OK).body(clientDTO);
    }

    @PatchMapping("clients/{id}")
    public ResponseEntity<?> updateClient(@RequestBody AdresseDTO adresseDTO, @PathVariable String id) {
        clientService.modifierAdresse(id,adresseDTO);
        return ResponseEntity.status(HttpStatus.OK).body(adresseDTO);
    }

    @GetMapping("clients")
    public ResponseEntity<List<Client>> findClientByNom(@RequestParam String nom) {

        List<Client> listeClients = clientService.trouverClientsParNom(nom);
        return ResponseEntity.status(HttpStatus.OK).body(listeClients);
    }

    @GetMapping("clients/{id}")
    public ResponseEntity<Client> findClientById(@PathVariable String id) {
        Client client = clientService.trouverClientParId(id);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

}
