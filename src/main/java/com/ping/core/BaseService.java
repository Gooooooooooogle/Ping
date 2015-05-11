package com.ping.core;

import com.ping.domain.Account;

import java.util.List;

public interface BaseService<E> {
	
	public Account find(String id);
	
	public List<E> findAll();

	public void update(E e);

    public void delete(String id);
    
}
