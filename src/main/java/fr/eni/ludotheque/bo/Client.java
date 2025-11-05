package fr.eni.ludotheque.bo;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Document(collection = "CLIENTS")

public class Client {
	@Id
	private String noClient;
	

	@NonNull
    private String nom;
	

	@NonNull
    private String prenom;
	

	@NonNull
    private String email;
	


	private String noTelephone;

	@NonNull
	private Adresse adresse;
}
