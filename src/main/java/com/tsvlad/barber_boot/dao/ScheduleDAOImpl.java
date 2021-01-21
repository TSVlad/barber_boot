package com.tsvlad.barber_boot.dao;


import com.tsvlad.barber_boot.entity.ScheduleItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.DayOfWeek;
import java.util.List;

@Repository
public class ScheduleDAOImpl implements ScheduleDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public ScheduleItem getScheduleItem(int barberId, DayOfWeek weekDay) {
        Session session = entityManager.unwrap(Session.class);
        Query<ScheduleItem> query = session.createQuery("FROM ScheduleItem WHERE scheduleItemKey.barber.id = :barberId AND scheduleItemKey.weekDay = :weekDay", ScheduleItem.class);
        query.setParameter("barberId", barberId);
        query.setParameter("weekDay", weekDay);
        return query.getSingleResult();
    }

    @Override
    public List<ScheduleItem> getScheduleItemsForDay(DayOfWeek weekDay) {
        Session session = entityManager.unwrap(Session.class);
        Query<ScheduleItem> query = session.createQuery("FROM ScheduleItem WHERE scheduleItemKey.weekDay=:weekDay", ScheduleItem.class);
        query.setParameter("weekDay", weekDay);
        return query.getResultList();
    }
}
