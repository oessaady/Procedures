package procedures.ma.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Processus {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codeProcessus;
	@NotEmpty
	private String nom;

	@OneToMany(mappedBy="processus")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private Collection<Procedures> procedures;
	
	@ManyToOne
	private Domain domaine;

	public Processus() {
		super();

	}

	public Processus(String nom) {
		super();
		this.nom = nom;
	}

	public Long getCodeProcessus() {
		return codeProcessus;
	}

	public void setCodeProcessus(Long codeProcessus) {
		this.codeProcessus = codeProcessus;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Collection<Procedures> getProcedures() {
		return procedures;
	}

	public void setProcedures(Collection<Procedures> procedures) {
		this.procedures = procedures;
	}

	public Domain getDomaine() {
		return domaine;
	}

	public void setDomaine(Domain domaine) {
		this.domaine = domaine;
	}
   
    

}
