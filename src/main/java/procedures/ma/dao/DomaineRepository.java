package procedures.ma.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import procedures.ma.entities.Domain;

public interface DomaineRepository extends JpaRepository<Domain, Long>{

}
