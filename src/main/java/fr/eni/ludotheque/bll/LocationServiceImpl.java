package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.bo.Facture;
import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.dal.ExemplaireRepository;
import fr.eni.ludotheque.dal.JeuRepository;
import fr.eni.ludotheque.dal.LocationRepository;
import fr.eni.ludotheque.dto.LocationDTO;
import fr.eni.ludotheque.exceptions.DataNotFound;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService{
	@NonNull
	final private LocationRepository locationRepository;

	@NonNull
	final private JeuRepository jeuRepository;

	@NonNull
	final private ExemplaireRepository exemplaireRepository;



	@Override
	public Location ajouterLocation(LocationDTO locationDto  ) {
		//Exemplaire exemplaire = exemplaireRepository.findByCodebarreWithJeu(locationDto.getCodebarre());
		Exemplaire exemplaire = exemplaireRepository.findByCodebarre(locationDto.getCodebarre());
		Client client = new Client();
		client.setNoClient(locationDto.getNoClient());
					
		Location location = new Location(LocalDateTime.now(),client, exemplaire );
		Float tarifJour = jeuRepository.findTarifJourByNoJeu(exemplaire.getJeu().getNoJeu());
		location.setTarifJour(tarifJour);
		Location newLoc = locationRepository.save(location);
		
		return newLoc;
	}

	@Override
    @Transactional
    public Facture retourExemplaires(List<String> codebarres) {
        Facture facture = new Facture();
        facture.setDatePaiement(LocalDateTime.now());
        float prix = 0;

        for (String codebarre : codebarres) {
            Location location = locationRepository.findLocationByExemplaireCodebarre(codebarre);
            location.setDateRetour(LocalDateTime.now());

            long nbJours = ChronoUnit.DAYS.between(location.getDateDebut(), location.getDateRetour()) + 1;
            prix += nbJours * location.getTarifJour();

            locationRepository.save(location); // mise à jour de la dateRetour

            facture.addLocation(location);
        }

        facture.setPrix(prix);

        // Injecter la facture dans chaque location si besoin
        for (Location loc : facture.getLocations()) {
            loc.setFacture(facture); // facultatif si tu veux garder la trace dans l’objet
        }

        return facture;
    }


    public Facture payerFacture(String noFacture) {

        List<Location> locations = locationRepository.findByFactureNoFacture(noFacture);

        if (locations.isEmpty()) {
            throw new DataNotFound("Facture", noFacture);
        }

        Facture facture = new Facture();
        facture.setNoFacture(noFacture);
        facture.setDatePaiement(LocalDateTime.now());

        float prix = 0;
        for (Location loc : locations) {
            long nbJours = ChronoUnit.DAYS.between(loc.getDateDebut(), loc.getDateRetour()) + 1;
            prix += nbJours * loc.getTarifJour();


            locationRepository.save(loc);
            facture.addLocation(loc);
        }

        facture.setPrix(prix);
        return facture; // objet métier, non persisté
    }


	@Override
	public void trouverLocationParExemplaireCodebarre(String codebarre) {
		// TODO Auto-generated method stub
		
	}

    public int nbExemplairesDisponibleByNoJeu(String noJeu) {
        List<Exemplaire> exemplairesLouables = exemplaireRepository.findByJeuNoJeuAndLouableTrue(noJeu);
        int disponibles = 0;

        for (Exemplaire ex : exemplairesLouables) {
            boolean estLoué = locationRepository.existsByExemplaireAndDateRetourIsNull(ex);
            if (!estLoué) {
                disponibles++;
            }
        }

        return disponibles;
    }


}
