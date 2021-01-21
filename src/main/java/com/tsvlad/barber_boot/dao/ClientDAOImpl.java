package com.tsvlad.barber_boot.dao;

import com.tsvlad.barber_boot.entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ClientDAOImpl implements ClientDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public Client getClient(String email) {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("FROM Client WHERE email =:email", Client.class).setParameter("email", email).getSingleResult();
    }

    @Override
    public void saveClient(Client client) {
        Session session = entityManager.unwrap(Session.class);
        session.save(client);
    }
}
