package com.tsvlad.barber_boot.service;


import com.tsvlad.barber_boot.entity.ActiveOrder;
import com.tsvlad.barber_boot.entity.ArchiveOrder;
import com.tsvlad.barber_boot.entity.Barber;
import com.tsvlad.barber_boot.entity.Service;
import com.tsvlad.barber_boot.entity.complexKeys.OrderKey;
import com.tsvlad.barber_boot.json_classes.ActiveOrderJSON;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public interface DataService {
    List<Barber> getBarbers();
    List<Barber> getArchivedBarbers();
    List<Barber> getBarbersWhichWorkAtDay(DayOfWeek weekDay);
    Barber getBarber(int id);
    void setBarberActivate(Barber barber, boolean isActive);
    void saveBarber(Barber barber);

    List<Service> getServices();
    Service getService(int id);
    void deleteService(int id);
    void saveService(Service service);

    ActiveOrder createOrder(ActiveOrderJSON activeOrderJSON);
    List<ActiveOrder> getActiveOrdersForUser(String username);
    ArchiveOrder acceptOrder(OrderKey orderKey);
    ArchiveOrder cancelOrder(OrderKey orderKey);
    List<ActiveOrder> getActiveOrders();
    List <ArchiveOrder> getArchiveOrdersForUserAndDate(String username, LocalDate date);
    List<ArchiveOrder> getArchiveOrdersForDate(LocalDate localDate);

    List<Integer> getAvailableWorkingHours(int barber_id, String date);
}
