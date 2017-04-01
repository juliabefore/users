package com.miskevich.users.service;

import com.miskevich.users.dao.GenericDao;
import com.miskevich.users.entity.User;

import java.util.List;

public class UserService implements IUserService {

    private GenericDao<User, Integer> userDao;

    public List<User> getAll() {
        return userDao.getAll();
    }


    public void save(User user) {
        userDao.save(user);
    }

    public void edit(User user){
        userDao.edit(user);
    }

    public User getById(Integer id){
        return userDao.getById(id);
    }

    public void setUserDao(GenericDao<User, Integer> userDao) {
        this.userDao = userDao;
    }
}
