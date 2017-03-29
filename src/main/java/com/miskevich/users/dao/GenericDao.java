package com.miskevich.users.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, K extends Serializable> {

    List<T> getAll();

    void save(T value);

    T getById(K id);
}
