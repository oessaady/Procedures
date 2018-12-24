package procedures.ma.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Commentaire implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long codeCommentaire;
	@NotEmpty
    private String description;
    private Date date;
    @ManyToOne
    private Procedures procedure;
    @ManyToOne
    private User user;
    
    private String reponse;
	public Commentaire() {
		super();
		
	}
	public Commentaire(String description, Date date) {
		super();
		this.description = description;
		this.date = date;
	}
	public Long getCodeCommentaire() {
		return codeCommentaire;
	}
	public void setCodeCommentaire(Long codeCommentaire) {
		this.codeCommentaire = codeCommentaire;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Procedures getProcedure() {
		return procedure;
	}
	public void setProcedure(Procedures procedure) {
		this.procedure = procedure;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getReponse() {
		return reponse;
	}
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	
    
}
