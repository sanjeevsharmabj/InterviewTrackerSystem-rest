package com.wellsfargo.fsd.its.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellsfargofsd.its.entity.UserEntity;

@Repository
public interface Userdao extends JpaRepository<UserEntity,Integer>{

}
