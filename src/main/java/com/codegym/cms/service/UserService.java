package com.codegym.cms.service;

import com.codegym.cms.model.User;

public interface UserService {
    Iterable<User> findAll();

    User findById(int id);

    void remove(int id);

    void save(User user);
}
