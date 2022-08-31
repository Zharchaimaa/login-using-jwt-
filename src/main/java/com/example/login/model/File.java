package com.example.login.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="RAY_REPORTING_FILES")
@Data
public class File {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//private static String date_controle = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	@Column(name="ENTITE",length=100)
	private String entite;
	@Column(name="NOM_FICHIER",length=100)
	private String filename;
	//NOUS MANQUE LA COLONNE QUI CONCERNE DATE DE TRAITEMENT ET ON SAIT JAMAIS SI FILEDATE S'AGIT DE DATE_ARRETE OU DATE_TRAITEMENT
	@Column(name="DATE_ARRETE",length=100)
	private  String filedate;
	@Column(name="DATE_TRAITEMENT",length=100)
	private  String dateTraitement;
	@Column(name="VALID_NAME",length=100)
	private  String validName;
	@Column(name="VALID_DATE",length=2)
	private  String validDate;
	@Column(name="VALID_COL",length=2)
	private  String validColumn;
	@Column(name="VALID_MONTANT",length=2)
	private  String validMontant;
}
