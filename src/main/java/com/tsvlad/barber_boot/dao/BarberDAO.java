package com.tsvlad.barber_boot.dao;


import com.tsvlad.barber_boot.entity.Barber;

import java.util.List;

public interface BarberDAO {
    List<Barber> getBarbers();
    List<Barber> getArchivedBarbers();
    Barber getBarber(int id);
    Barber getBarberByUsername(String username);
    Barber saveBarber(Barber barber);
}
