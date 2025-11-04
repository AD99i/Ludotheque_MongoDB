package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bll.JeuService;
import fr.eni.ludotheque.bo.Jeu;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JeuRestController {

    @NonNull
    private JeuService jeuService;

    @GetMapping("/jeux")
    public ResponseEntity<List<Jeu>> listeJeu() {
        List<Jeu> listejeu = jeuService.listeJeuxCatalogue("");
        return ResponseEntity.ok().body(listejeu);
    }

    @GetMapping("/jeux/{codeBarre}")
    public ResponseEntity<?> getJeu(@PathVariable("codeBarre") String codeBarre) {
        Jeu jeu = jeuService.findJeuByCodeBarre(codeBarre);
        return ResponseEntity.ok().body(jeu);

    }


}

