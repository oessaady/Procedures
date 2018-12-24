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
public class VueMatrice implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long codeVueMatrice;
	@NotEmpty
    private String control;
	 @NotEmpty
    private String support;
	 @NotEmpty
    private String materialisation;
	 @NotEmpty
    private String periodicite;
	 @NotEmpty
    private String finalite;
	 @NotEmpty
    private String auteur;
    
    @ManyToOne
    private Procedures procedure;

	public VueMatrice() {
		super();
	}

	public VueMatrice(String control, String support, String materialisation, String periodicite, String finalite) {
		super();
		this.control = control;
		this.support = support;
		this.materialisation = materialisation;
		this.periodicite = periodicite;
		this.finalite = finalite;
	}


	public Long getCodeVueMatrice() {
		return codeVueMatrice;
	}

	public void setCodeVueMatrice(Long codeVueMatrice) {
		this.codeVueMatrice = codeVueMatrice;
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	public String getMaterialisation() {
		return materialisation;
	}

	public void setMaterialisation(String materialisation) {
		this.materialisation = materialisation;
	}

	public String getPeriodicite() {
		return periodicite;
	}

	public void setPeriodicite(String periodicite) {
		this.periodicite = periodicite;
	}

	public String getFinalite() {
		return finalite;
	}

	public void setFinalite(String finalite) {
		this.finalite = finalite;
	}

	public Procedures getProcedure() {
		return procedure;
	}

	public void setProcedure(Procedures procedure) {
		this.procedure = procedure;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
    
    
    
}
