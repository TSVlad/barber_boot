package com.tsvlad.barber_boot.dao;


import com.tsvlad.barber_boot.entity.Client;

public interface ClientDAO {
    Client getClient(String email);
    void saveClient(Client client);
}
