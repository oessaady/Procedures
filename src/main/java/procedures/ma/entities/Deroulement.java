package procedures.ma.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Deroulement implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	  private Long codeDeroulement;
	  private String numero;
	  @NotEmpty
      private String evenementDeclencheur;
	  @NotEmpty
      private String operation;
	  @NotEmpty
      private String acteurs;
	  @NotEmpty
      private String description;
	  @NotEmpty
      private String si;
	  @NotEmpty
      private String delai;
	  @NotEmpty
      private String documentAssocie;
      @ManyToOne
      private Procedures procedure; 
      
	public Deroulement() {
		super();
	}
	public Deroulement(String evenementDeclencheur, String operation, String acteurs, String description,
			String si, String delai, String documentAssocie) {
		super();
		 
		this.evenementDeclencheur = evenementDeclencheur;
		this.operation = operation;
		this.acteurs = acteurs;
		this.description = description;
		this.si = si;
		this.delai = delai;
		this.documentAssocie = documentAssocie;
	}
	
	public Long getCodeDeroulement() {
		return codeDeroulement;
	}
	public void setCodeDeroulement(Long codeDeroulement) {
		this.codeDeroulement = codeDeroulement;
	}
	public Procedures getProcedure() {
		return procedure;
	}
	public void setProcedure(Procedures procedure) {
		this.procedure = procedure;
	}
	 
	public String getEvenementDeclencheur() {
		return evenementDeclencheur;
	}
	public void setEvenementDeclencheur(String evenementDeclencheur) {
		this.evenementDeclencheur = evenementDeclencheur;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getActeurs() {
		return acteurs;
	}
	public void setActeurs(String acteurs) {
		this.acteurs = acteurs;
	}
	 
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSi() {
		return si;
	}
	public void setSi(String si) {
		this.si = si;
	}
	public String getDelai() {
		return delai;
	}
	public void setDelai(String delai) {
		this.delai = delai;
	}
	public String getDocumentAssocie() {
		return documentAssocie;
	}
	public void setDocumentAssocie(String documentAssocie) {
		this.documentAssocie = documentAssocie;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
      
      
}
