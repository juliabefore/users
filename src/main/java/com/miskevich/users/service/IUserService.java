package com.miskevich.users.service;

import com.miskevich.users.entity.User;

import java.util.List;

public interface IUserService {

    List<User> getAll();

    void save(User user);

}
