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
public class RegleGestion implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codeRegle;
	@NotEmpty
	private String description;

	@ManyToOne
	private Procedures procedure;

	public RegleGestion() {
		super();

	}

	public RegleGestion(String description) {
		super();
		this.description = description;
	}

	public Long getCodeRegle() {
		return codeRegle;
	}

	public void setCodeRegle(Long codeRegle) {
		this.codeRegle = codeRegle;
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
