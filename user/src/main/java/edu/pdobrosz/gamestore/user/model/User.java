package edu.pdobrosz.gamestore.user.model;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	private String id;

	@Size(min = 8, max = 240)
	private String name;

	@JsonIgnore
	private String password;

	private String[] games;

}
