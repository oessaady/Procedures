package procedures.ma.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class CadreLegal {
  
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long codeCadre;
    @NotEmpty
	private String description;

	@ManyToOne
	private Procedures procedure;
	
	 

	public CadreLegal() {
		super();
	}
   
	public CadreLegal(String description) {
		super();
		this.description = description;
	}
   
	public Long getCodeCadre() {
		return codeCadre;
	}

	public void setCodeCadre(Long codeCadre) {
		this.codeCadre = codeCadre;
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


}
