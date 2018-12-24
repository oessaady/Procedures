package procedures.ma.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class VueComptable implements Serializable{
	   @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Long codeVueCompt;
	    @NotEmpty
        private String description;
        
        @OneToMany(mappedBy="vueComptable",fetch=FetchType.LAZY)
        @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
        private Collection<Operation> operations;
        
        @ManyToOne
        private Procedures procedure;

		public VueComptable() {
			super();
		}

		public VueComptable(String description) {
			super();
			this.description = description;
		}

	 	

		public Long getCodeVueCompt() {
			return codeVueCompt;
		}

		public void setCodeVueCompt(Long codeVueCompt) {
			this.codeVueCompt = codeVueCompt;
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

		public Collection<Operation> getOperations() {
			return operations;
		}

		public void setOperations(Collection<Operation> operations) {
			this.operations = operations;
		} 
        
        
}
