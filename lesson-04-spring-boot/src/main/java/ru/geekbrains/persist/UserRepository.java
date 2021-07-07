package ru.geekbrains.persist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUsernameStartsWith(String prefix);
}
