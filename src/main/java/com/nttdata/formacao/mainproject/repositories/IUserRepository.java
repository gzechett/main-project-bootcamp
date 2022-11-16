package com.nttdata.formacao.mainproject.repositories;

import com.nttdata.formacao.mainproject.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity,Long> {
}
