package procedures.ma.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class Credit extends Operation{

	@Override
	public String toString() {
	 
		return "Credit";
	}

}
