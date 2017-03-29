package com.miskevich.users.service;

import com.miskevich.users.dao.GenericDao;
import com.miskevich.users.entity.User;

import java.util.List;

public class UserService implements IUserService {

    private GenericDao<User, String> userDao;

    public List<User> getAll() {
        return userDao.getAll();
    }


    public void save(User user) {

    }

    public void setUserDao(GenericDao<User, String> userDao) {
        this.userDao = userDao;
    }
}
