package fr.eni.ludotheque.bo;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Document(collection="EXEMPLAIRES")
public class Exemplaire {
	@Id
	private String noExemplaire;
	

	@NonNull private String codebarre;

    @NonNull
	private boolean louable=true;

	@NonNull
	private Jeu jeu;
	
}
