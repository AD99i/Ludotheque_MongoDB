package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Jeu;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;

public interface JeuRepository extends MongoRepository<Jeu, String> {

    List<Jeu> findByTitreContainingIgnoreCase( String titre);

    Float findTarifJourByNoJeu(String noJeu);

    Jeu findByReference(String reference);
}
