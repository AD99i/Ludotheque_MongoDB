package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.bo.Location;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface LocationRepository extends MongoRepository<Location, String> {
    Location findLocationByExemplaireCodebarre(String codebarre);

    List<Location> findByFactureNoFacture(String noFacture);

    boolean existsByExemplaireAndDateRetourIsNull(Exemplaire exemplaire);

}
