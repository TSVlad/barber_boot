package com.tsvlad.barber_boot.dao;


import com.tsvlad.barber_boot.entity.ActiveOrder;
import com.tsvlad.barber_boot.entity.ArchiveOrder;
import com.tsvlad.barber_boot.entity.complexKeys.OrderKey;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public ActiveOrder createOrder(ActiveOrder activeOrder) {
        Session session = entityManager.unwrap(Session.class);
        session.save(activeOrder);
        return activeOrder;
    }

    @Override
    public List<ActiveOrder> getActiveBarberOrdersForDate(int barberId, LocalDate date) {
        Session session = entityManager.unwrap(Session.class);
        Query<ActiveOrder> query = session.createQuery("FROM ActiveOrder WHERE orderKey.date = :date AND orderKey.barber.id = :barberId");
        query.setParameter("barberId", barberId);
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<ActiveOrder> getActiveOrdersForBarber(int barberId) {
        Session session = entityManager.unwrap(Session.class);
        Query<ActiveOrder> query = session.createQuery("FROM ActiveOrder WHERE orderKey.barber.id = :barberId");
        query.setParameter("barberId", barberId);
        return query.getResultList();
    }

    @Override
    public List<ActiveOrder> getActiveOrders() {
        Session session = entityManager.unwrap(Session.class);
        Query<ActiveOrder> query = session.createQuery("FROM ActiveOrder");
        return query.getResultList();
    }

    @Override
    public ActiveOrder getActiveOrder(OrderKey orderKey) {
        Session session = entityManager.unwrap(Session.class);
        Query<ActiveOrder> activeOrderQuery = session.createQuery("FROM ActiveOrder WHERE orderKey = :orderKey");
        activeOrderQuery.setParameter("orderKey", orderKey);
        return activeOrderQuery.getSingleResult();
    }

    @Override
    public ArchiveOrder saveArchiveOrder(ArchiveOrder archiveOrder) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(archiveOrder);
        return archiveOrder;
    }

    @Override
    public void deleteActiveOrder(ActiveOrder activeOrder) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(activeOrder);
    }

    @Override
    public List<ArchiveOrder> getArchiveOrdersForBarberAndDate(int barberId, LocalDate date) {
        Session session = entityManager.unwrap(Session.class);
        Query<ArchiveOrder> query = session.createQuery("FROM ArchiveOrder WHERE barber.id = :barberId AND date = :date");
        query.setParameter("barberId", barberId);
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<ArchiveOrder> getArchiveOrdersForDate(LocalDate date) {
        Session session = entityManager.unwrap(Session.class);
        Query<ArchiveOrder> query = session.createQuery("FROM ArchiveOrder WHERE date = :date");
        query.setParameter("date", date);
        return query.getResultList();
    }
}
