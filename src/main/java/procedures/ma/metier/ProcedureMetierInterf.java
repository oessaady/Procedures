package procedures.ma.metier;

import org.springframework.data.jpa.repository.query.Procedure;

import procedures.ma.entities.Operation;
import procedures.ma.entities.VueComptable;

public interface ProcedureMetierInterf {
     
	public Procedure updateProcedureByVue(Procedure p,VueComptable vue,Operation o);
	public Procedure updateProcedureByDesc(Procedure p,VueComptable vue,String description);
}
