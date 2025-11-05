package fr.eni.ludotheque.bo;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection="JEUX")
public class Jeu {

    @Id
	private String noJeu;

	@NonNull
	private String titre;


	@NonNull private String reference;
	

	private int ageMin;
	

	private String description;


	private int duree;
	

	@NonNull
	private Float tarifJour;

	@Transient
	private int nbExemplairesDisponibles;

	private List<Genre> genres = new ArrayList<>();
	
	public void addGenre(Genre g) {
		genres.add(g);
	}
}
