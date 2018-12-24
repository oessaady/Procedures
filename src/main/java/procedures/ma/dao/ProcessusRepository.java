package procedures.ma.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import procedures.ma.entities.Processus;

public interface ProcessusRepository extends JpaRepository<Processus, Long>{

}
