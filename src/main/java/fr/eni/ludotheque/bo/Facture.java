package fr.eni.ludotheque.bo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor


public class Facture {


	private String noFacture;
	

	private LocalDateTime datePaiement;
	

	private List<Location> locations=new ArrayList<Location>();
	
	
	//@Transient // attribut calculable  
	private float prix;
	
	public void addLocation(Location location) {
		this.locations.add(location);
	}
}
