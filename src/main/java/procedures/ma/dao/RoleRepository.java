package procedures.ma.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import procedures.ma.entities.Role;



public interface RoleRepository extends JpaRepository<Role, String> {

}
