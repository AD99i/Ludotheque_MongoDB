package fr.eni.ludotheque.dal;


import org.springframework.data.mongodb.repository.MongoRepository;


import fr.eni.ludotheque.bo.Exemplaire;

import java.util.List;

public interface ExemplaireRepository extends MongoRepository<Exemplaire, String> {


    Exemplaire findByCodebarre(String codebarre);


    List<Exemplaire> findByJeuNoJeuAndLouableTrue(String noJeu);

}
