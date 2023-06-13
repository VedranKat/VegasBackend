package com.example.vegasBackend.service.api;


import com.example.vegasBackend.exception.EntityNotFoundException;

import java.util.Set;

public interface CrudService <T, ID, R>{

    R create(T entity);
    Set<R> getAll();
    R getById(ID id) throws EntityNotFoundException;
    R update(T entity, ID id) throws EntityNotFoundException;
    R deleteById(ID id) throws EntityNotFoundException;
}
