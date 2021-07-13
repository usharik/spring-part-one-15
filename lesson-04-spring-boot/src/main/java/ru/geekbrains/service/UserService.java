package ru.geekbrains.service;

import org.springframework.data.domain.Page;
import ru.geekbrains.controller.UserListParams;
import ru.geekbrains.persist.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Page<User> findWithFilter(UserListParams userListParams);

    Optional<User> findById(Long id);

    void save(User user);

    void deleteById(Long id);
}
