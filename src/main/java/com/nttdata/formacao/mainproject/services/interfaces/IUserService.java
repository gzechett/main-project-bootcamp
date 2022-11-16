package com.nttdata.formacao.mainproject.services.interfaces;

import com.nttdata.formacao.mainproject.entities.UserEntity;

import java.util.List;

public interface IUserService {

    UserEntity getUserById(long id);

    List<UserEntity> getAllUsers();

    void saveUser(UserEntity user);

    void delete(UserEntity user);

    boolean existUser(UserEntity user);
}
