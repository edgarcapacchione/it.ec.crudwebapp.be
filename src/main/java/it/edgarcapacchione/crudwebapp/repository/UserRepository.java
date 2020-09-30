package it.edgarcapacchione.crudwebapp.repository;

import it.edgarcapacchione.crudwebapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
