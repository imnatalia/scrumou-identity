package br.ifsp.scrumou.identity.repository;

import br.ifsp.scrumou.identity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { 
    java.util.Optional<User> findByEmail(String email);
}