package com.bp.ensayo.service;

import java.util.List;

public interface GenericService<D> {
    D save(D data);

    D update(long id, D data);

    void delete(long id);

    List<D> getAll();

    D getById(long id);
}
