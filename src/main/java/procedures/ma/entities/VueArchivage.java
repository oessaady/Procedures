package procedures.ma.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
public class VueArchivage implements Serializable{
	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Long codeVueArchiv ;
	  @NotEmpty
      private String document;
	  @NotEmpty
      private String emeteur;
	  @NotEmpty
      private String nbrExp;
	  @NotEmpty
      private String destinataire;
	  @NotEmpty
      private String mode;
	  @NotEmpty
      private String frequence;
	  @NotEmpty
      private String lieu;
      
      @ManyToOne
      private Procedures procedure;

	public VueArchivage() {
		super();
	}

	public VueArchivage(String document, String emeteur, String nbrExp, String destinataire) {
		super();
		this.document = document;
		this.emeteur = emeteur;
		this.nbrExp = nbrExp;
		this.destinataire = destinataire;
	}

	

	public Long getCodeVueArchiv() {
		return codeVueArchiv;
	}

	public void setCodeVueArchiv(Long codeVueArchiv) {
		this.codeVueArchiv = codeVueArchiv;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getEmeteur() {
		return emeteur;
	}

	public void setEmeteur(String emeteur) {
		this.emeteur = emeteur;
	}

	public String getNbrExp() {
		return nbrExp;
	}

	public void setNbrExp(String nbrExp) {
		this.nbrExp = nbrExp;
	}

	public String getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}

	 
	public Procedures getProcedure() {
		return procedure;
	}

	public void setProcedure(Procedures procedure) {
		this.procedure = procedure;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getFrequence() {
		return frequence;
	}

	public void setFrequence(String frequence) {
		this.frequence = frequence;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	} 
	
      
      
      
}
