package procedures.ma.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import procedures.ma.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long>{

}
