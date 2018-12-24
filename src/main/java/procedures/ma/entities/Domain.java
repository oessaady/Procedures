package procedures.ma.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
public class Domain implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codeDomain;
	@NotEmpty
	private String nom;
	@OneToMany(mappedBy="domaine",fetch=FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private Collection<Processus> processus; 
	 
	public Domain() {
		super();
	}
	
	public Domain(String nom) {
		super();
		this.nom = nom;
 
	}

	public Long getCodeDomain() {
		return codeDomain;
	}

	public void setCodeDomain(Long codeDomain) {
		this.codeDomain = codeDomain;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Collection<Processus> getProcessus() {
		return processus;
	}

	public void setProcessus(Collection<Processus> processus) {
		this.processus = processus;
	}
	
 

}
