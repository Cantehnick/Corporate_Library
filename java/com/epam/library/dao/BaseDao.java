
package com.epam.library.dao;

import java.util.List;

import com.epam.library.domain.Entity;

public interface BaseDao<T extends Entity> {

	void create(T entity);

	T read(int id);

	void update(T entity);

	void delete(int id);

	List<T> readAll();
}
