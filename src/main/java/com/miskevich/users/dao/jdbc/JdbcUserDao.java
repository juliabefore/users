package com.miskevich.users.dao.jdbc;

import com.miskevich.users.dao.GenericDao;
import com.miskevich.users.entity.User;

import java.util.List;

public class JdbcUserDao implements GenericDao<User, String>{

    public List<User> getAll() {
        return null;
    }

    public void save(User value) {

    }

    public User getById(String id) {
        return null;
    }
}
