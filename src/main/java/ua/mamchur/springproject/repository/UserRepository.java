package ua.mamchur.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.mamchur.springproject.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    User getByUsername(String username);
}
