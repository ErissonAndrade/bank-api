package me.dio.bankapi.service.impl;

import me.dio.bankapi.domain.model.User;
import me.dio.bankapi.domain.repository.UserRepository;
import me.dio.bankapi.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new IllegalArgumentException("This Account number already exists.");
        }
        return userRepository.save(userToCreate);
    }

    @Override
    public User delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));

        userRepository.deleteById(id);
        return user;
    }

    @Override
    public User update(Long id, User userToUpdate) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));

        // Update the properties of existingUser with the properties of userToUpdate
        existingUser.setName(userToUpdate.getName());
        // Update other properties as needed

        return userRepository.save(existingUser);
    }
}