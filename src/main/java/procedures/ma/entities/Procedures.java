package procedures.ma.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Procedures implements Serializable{
	   @Id
	   @GeneratedValue(strategy=GenerationType.IDENTITY)
	   private Long codeProcedure;
	   @NotEmpty
	   private String nom;

	   @OneToOne(fetch = FetchType.LAZY)
	   @JoinColumn(name = "objectif_id")
    	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	   private Objectif objectif;
	   
       @OneToMany(mappedBy="procedure")
    	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
       private Collection<Commentaire> commentaires;
	  
       @OneToMany(mappedBy="procedure")
   	  @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
       private Collection<RegleGestion> reglesGestions;
       
 
       @OneToMany(mappedBy="procedure")
    	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
       private Collection<CadreLegal> cadresLegales;
       
	 
       @ManyToOne
       private Processus processus;
       
	  
        @OneToMany(mappedBy="procedure",fetch=FetchType.LAZY)
    	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
       private Collection<Deroulement> deroulements;
       
 
       @OneToMany(mappedBy="procedure",fetch=FetchType.LAZY)
    	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
       private Collection<VueArchivage> vueArchivages;
       
	 
       @OneToMany(mappedBy="procedure",fetch=FetchType.LAZY)
    	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
       private Collection<VueMatrice> vueMatrices;
       
	 
       @OneToMany(mappedBy="procedure",fetch=FetchType.LAZY)
   	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
       private Collection<VueComptable> vueComptables;
       
 
       @OneToMany(mappedBy="procedure",fetch=FetchType.LAZY)
    	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
       private Collection<VueSysteme> vueSystemes;

	public Procedures() {
		super();
	}

	public Procedures(String nom) {
		super();
		this.nom = nom;
  
	  
	}

	
	public Long getCodeProcedure() {
		return codeProcedure;
	}

	public void setCodeProcedure(Long codeProcedure) {
		this.codeProcedure = codeProcedure;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Collection<RegleGestion> getReglesGestions() {
		return reglesGestions;
	}

	public void setReglesGestions(Collection<RegleGestion> reglesGestions) {
		this.reglesGestions = reglesGestions;
	}

	public Collection<CadreLegal> getCadresLegales() {
		return cadresLegales;
	}

	public void setCadresLegales(Collection<CadreLegal> cadresLegales) {
		this.cadresLegales = cadresLegales;
	}

	public Processus getProcessus() {
		return processus;
	}

	public void setProcessus(Processus processus) {
		this.processus = processus;
	}

	public Collection<Deroulement> getDeroulements() {
		return deroulements;
	}

	public void setDeroulements(Collection<Deroulement> deroulements) {
		this.deroulements = deroulements;
	}

	public Collection<VueArchivage> getVueArchivages() {
		return vueArchivages;
	}

	public void setVueArchivages(Collection<VueArchivage> vueArchivages) {
		this.vueArchivages = vueArchivages;
	}

	public Collection<VueMatrice> getVueMatrices() {
		return vueMatrices;
	}

	public void setVueMatrices(Collection<VueMatrice> vueMatrices) {
		this.vueMatrices = vueMatrices;
	}

	public Collection<VueComptable> getVueComptables() {
		return vueComptables;
	}

	public void setVueComptables(Collection<VueComptable> vueComptables) {
		this.vueComptables = vueComptables;
	}

	public Collection<VueSysteme> getVueSystemes() {
		return vueSystemes;
	}

	public void setVueSystemes(Collection<VueSysteme> vueSystemes) {
		this.vueSystemes = vueSystemes;
	}

	public Objectif getObjectif() {
		return objectif;
	}

	public void setObjectif(Objectif objectif) {
		this.objectif = objectif;
	}

	public Collection<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(Collection<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}
 

	
       
       
 
}
