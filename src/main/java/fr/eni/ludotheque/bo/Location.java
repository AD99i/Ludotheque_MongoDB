package fr.eni.ludotheque.bo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Document(collection="LOCATIONS")
public class Location {

    @Id
	private String noLocation;

	@EqualsAndHashCode.Include
    @Field
	@NonNull private LocalDateTime dateDebut;

    @Field
	private LocalDateTime dateRetour;

    @Field
	private float tarifJour;


	@NonNull private Client client;

	@EqualsAndHashCode.Include
	@NonNull private Exemplaire exemplaire;

    private Facture facture;



}
