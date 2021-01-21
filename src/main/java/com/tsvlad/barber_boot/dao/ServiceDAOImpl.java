package com.tsvlad.barber_boot.dao;

import com.tsvlad.barber_boot.entity.Service;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ServiceDAOImpl implements ServiceDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Service> getServices() {
        Session session = entityManager.unwrap(Session.class);
        Query<Service> query = session.createQuery("FROM Service WHERE isActive = true");
        return query.getResultList();
    }

    @Override
    public Service getService(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Service.class, id);
    }

    @Override
    public void deleteService(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("UPDATE Service set isActive = false WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void saveService(Service service) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(service);
    }
}
