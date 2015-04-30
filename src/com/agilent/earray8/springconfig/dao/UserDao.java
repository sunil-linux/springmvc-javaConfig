package com.agilent.earray8.springconfig.dao;

import java.util.List;

import com.agilent.earray8.springconfig.beans.User;

public interface UserDao {

	public void save(User user);

	public void delete(User user);

	public List<User> getAll();

	public User getByEmail(String email);

	public User getById(long id);

	public void update(User user);
}
