package com.example.login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(	name = "todolist")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Todolist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="LABEL",length=100)
	private String label;
	/*@NotBlank
	@Size(max = 100)
	private String description;*/
	@Column(name="COMPLETED")
	private boolean completed;

}
