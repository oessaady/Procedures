package procedures.ma.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Objectif {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codeObjectif;
	@NotEmpty
	private String description;
	
	 private String intervenantInternes;
     private String intervenantExternes;

    @OneToOne(mappedBy = "objectif", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Procedures procedure;
    

	public Objectif() {
		super();
		 
	}

	
  
	public Objectif(String description, String intervenantInternes, String intervenantExternes) {
		super();
		this.description = description;
		this.intervenantInternes = intervenantInternes;
		this.intervenantExternes = intervenantExternes;
	}



	public Long getCodeObjectif() {
		return codeObjectif;
	}

	public void setCodeObjectif(Long codeObjectif) {
		this.codeObjectif = codeObjectif;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Procedures getProcedure() {
		return procedure;
	}

	public void setProcedure(Procedures procedure) {
		this.procedure = procedure;
	}

	public String getIntervenantInternes() {
		return intervenantInternes;
	}

	public void setIntervenantInternes(String intervenantInternes) {
		this.intervenantInternes = intervenantInternes;
	}

	public String getIntervenantExternes() {
		return intervenantExternes;
	}

	public void setIntervenantExternes(String intervenantExternes) {
		this.intervenantExternes = intervenantExternes;
	}

   
}
