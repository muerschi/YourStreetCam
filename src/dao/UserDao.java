package dao;

import java.util.List;

import model.User;

public interface UserDao {
	public void save(User book);
	public void delete(Long id);
	public User get(Long id);
	public User get(String name);
	public List<User> list();
}

