package edu.pdobrosz.gamestore.sdkgame;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
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

	private String groupId;

	@Size(min = 8, max = 240)
	private String name;

	private String[] category;

	@Size(min = 8, max = 1024)
	private String description;

	@Size(min = 3, max = 240)
	private String publisher;

	@Size(min = 8, max = 240)
	private String imageUrl;

	@Size(min = 8, max = 240)
	private String url;

	@NotNull
	private BigDecimal price;

	@NotNull
	private Boolean published;

	@NotNull
	private Date lastSaved;

}
