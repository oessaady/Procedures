package procedures.ma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import procedures.ma.entities.Commentaire;
import procedures.ma.entities.Procedures;
import procedures.ma.entities.User;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long>{
	@Query("select c from Commentaire c where c.user.username like :x and c.procedure.codeProcedure like :y")
   public List<Commentaire> gelAllCommetaireByUser(@Param("x") String username,@Param("y") Long codePrcedure);
}
