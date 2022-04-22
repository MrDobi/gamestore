package edu.pdobrosz.gamestore.game.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {

	@Id
	private String id;

	private String groupId;

	@TextIndexed
	@Size(min = 8, max = 240)
	private String name;

	@JsonIgnore
	private String secret;

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
