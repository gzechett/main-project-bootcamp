package com.nttdata.formacao.mainproject.services.implementation;

import com.nttdata.formacao.mainproject.entities.UserEntity;
import com.nttdata.formacao.mainproject.repositories.IUserRepository;
import com.nttdata.formacao.mainproject.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserEntity getUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public void delete(UserEntity user) {
        userRepository.delete(user);
    }

    @Override
    public boolean existUser(UserEntity user) {
        return userRepository.exists(Example.of(user));
    }
}
