package com.MyApplication.interfaces;

import com.MyApplication.configuration.DatabaseConfigs;

import java.sql.Connection;
import java.util.ArrayList;

public interface IService<E> {
    public final Connection connection = DatabaseConfigs.connection;
    int create(E e);
    ArrayList<E> findAll();
    int update(E e);
    int remove(E e);
    E findById(int id);
}
