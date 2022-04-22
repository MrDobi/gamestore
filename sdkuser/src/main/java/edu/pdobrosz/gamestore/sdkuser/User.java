package edu.pdobrosz.gamestore.sdkuser;

import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class User extends RepresentationModel<User> {

	private String id;

	@Size(min = 8, max = 240)
	private String name;

	private String[] games;

}
