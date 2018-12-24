package procedures.ma.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("D")
public class Debit extends Operation{
	@Override
	public String toString() {
	 
		return "Debit";
	}
}
