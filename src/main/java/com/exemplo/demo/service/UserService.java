package com.exemplo.demo.service;

import com.exemplo.demo.model.User;
import com.exemplo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;


    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }


    public User register(User user) {
        Optional<User> existing = repository.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("E-mail já cadastrado.");
        }
        return repository.save(user);
    }


    public User changePassword(String email, String newPassword) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        user.setPassword(newPassword);
        return repository.save(user);
    }
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado.");
        }
        repository.deleteById(id);
    }
    public User login(String email, String password) {
        return repository.findByEmail(email)
                .filter(user -> user.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));
    }
    public List<User> listarTodos() {
        return repository.findAll();
    }



}



