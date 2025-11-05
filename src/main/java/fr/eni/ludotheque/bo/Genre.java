package fr.eni.ludotheque.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Genre {

    @Id
	@NonNull
	private String noGenre;

	@NonNull private String libelle;
	
}
