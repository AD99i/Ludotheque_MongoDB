package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dto.AdresseDTO;
import fr.eni.ludotheque.dto.ClientDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@Controller + @ResponseBody
public class ClientRestController {

    @NonNull
    private ClientService clientService;

    @PostMapping("/clients")
    public ResponseEntity<?> ajouterClient(@RequestBody ClientDTO clientDTO) {


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

    @GetMapping("clients/{nom}")
    public ResponseEntity<List<Client>> findClientByNom(@PathVariable String nom) {

        List<Client> listeClients = clientService.trouverClientsParNom(nom);
        return ResponseEntity.status(HttpStatus.OK).body(listeClients);
    }

    @GetMapping("clients/{id}")
    public ResponseEntity<Client> findClientById(@PathVariable String id) {
        Client client = clientService.trouverClientParId(id);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

}
