package com.tsvlad.barber_boot.dao;


import com.tsvlad.barber_boot.entity.Role;
import com.tsvlad.barber_boot.entity.User;

public interface AuthDAO {
    User getUserByUsername(String username);
    void saveUser(User user);
    void deleteUser(User user);

    void saveRole(Role role);
    Role getRoleByName(String name);
}
