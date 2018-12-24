package procedures.ma.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(length=1)
public class Operation implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
     private Long codeOp;
	 @NotEmpty
     private String numeroCompte;
	 @NotEmpty
     private String libelle;
     private String montant;
     @ManyToOne
     private VueComptable vueComptable;
	public Operation() {
		super();
	 
	}
	public Operation(String numeroCompte, String libelle, String montant) {
		super();
		this.numeroCompte = numeroCompte;
		this.libelle = libelle;
		this.montant = montant;
	}
	public Long getCodeOp() {
		return codeOp;
	}
	public void setCodeOp(Long codeOp) {
		this.codeOp = codeOp;
	}
	public String getNumeroCompte() {
		return numeroCompte;
	}
	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getMontant() {
		return montant;
	}
	public void setMontant(String montant) {
		this.montant = montant;
	}
	public VueComptable getVueComptable() {
		return vueComptable;
	}
	public void setVueComptable(VueComptable vueComptable) {
		this.vueComptable = vueComptable;
	}
	@Override
	public String toString() {
		 
		return "";
	}
	
     
     
}
