package com.tsvlad.barber_boot.dao;


import com.tsvlad.barber_boot.entity.ActiveOrder;
import com.tsvlad.barber_boot.entity.ArchiveOrder;
import com.tsvlad.barber_boot.entity.complexKeys.OrderKey;

import java.time.LocalDate;
import java.util.List;

public interface OrderDAO {
    ActiveOrder createOrder(ActiveOrder activeOrder);
    List<ActiveOrder> getActiveBarberOrdersForDate(int barberId, LocalDate date);
    List<ActiveOrder> getActiveOrdersForBarber(int barberId);
    List<ActiveOrder>  getActiveOrders();
    ActiveOrder getActiveOrder(OrderKey orderKey);
    ArchiveOrder saveArchiveOrder(ArchiveOrder archiveOrder);
    void deleteActiveOrder(ActiveOrder activeOrder);
    List<ArchiveOrder> getArchiveOrdersForBarberAndDate(int barberId, LocalDate date);
    List<ArchiveOrder> getArchiveOrdersForDate(LocalDate date);
}
