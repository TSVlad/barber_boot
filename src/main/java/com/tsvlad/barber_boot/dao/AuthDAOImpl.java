package com.tsvlad.barber_boot.dao;

import com.tsvlad.barber_boot.entity.Role;
import com.tsvlad.barber_boot.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class AuthDAOImpl implements AuthDAO{
    @Autowired
    private EntityManager entityManager;

    @Override
    public User getUserByUsername(String username) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(User.class, username);
    }

    @Override
    public void saveUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(user);
    }

    @Override
    public void deleteUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(user);
    }


    @Override
    public void saveRole(Role role) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(role);
    }

    @Override
    public Role getRoleByName(String name) {
        Session session = entityManager.unwrap(Session.class);
        Query<Role> query = session.createQuery("FROM Role WHERE role_name = :name");
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
