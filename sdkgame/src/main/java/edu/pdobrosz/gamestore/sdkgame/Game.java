package edu.pdobrosz.gamestore.sdkgame;

import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Game extends RepresentationModel<Game> {

	private String id;

	@Size(min = 8, max = 240)
	private String name;

}
