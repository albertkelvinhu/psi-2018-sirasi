package com.rawsanj.adminlte.service;

import java.util.List;

import com.rawsanj.adminlte.model.Role;
import com.rawsanj.adminlte.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user,String role);
	List<Role> findAllRoles();
	
}
