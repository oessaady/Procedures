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
public class VueSysteme implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
     private Long codeVueSystem;
	 @NotEmpty
     private String application;
	 @NotEmpty
     private String fonctionnalite;
     
     @ManyToOne
     private Procedures procedure;

	public VueSysteme() {
		super();
	}

	public VueSysteme(String application, String fonctionnalite) {
		super();
		this.application = application;
		this.fonctionnalite = fonctionnalite;
	}

	

	public Long getCodeVueSystem() {
		return codeVueSystem;
	}

	public void setCodeVueSystem(Long codeVueSystem) {
		this.codeVueSystem = codeVueSystem;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getFonctionnalite() {
		return fonctionnalite;
	}

	public void setFonctionnalite(String fonctionnalite) {
		this.fonctionnalite = fonctionnalite;
	}

	public Procedures getProcedure() {
		return procedure;
	}

	public void setProcedure(Procedures procedure) {
		this.procedure = procedure;
	}
	
     
     
}
