package me.dio.bankapi.service;

import me.dio.bankapi.domain.model.User;


public interface UserService {

    Iterable<User> findAll();
    User findById(Long id);

    User create(User userToCreate);

    User delete(Long id);

    User update(Long id, User userToUpdate);
}