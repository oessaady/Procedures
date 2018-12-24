package procedures.ma.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import procedures.ma.entities.Procedures;

public interface ProceduresRepository extends JpaRepository<Procedures, Long>{

}
