package procedures.ma.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import procedures.ma.entities.User;



public interface UserRepository extends JpaRepository<User, String> {
}
