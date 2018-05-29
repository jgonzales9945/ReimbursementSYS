package com.revature.daos;

import java.util.ArrayList;

import com.revature.pojos.Users;


public interface UserDAO {
	public ArrayList<Users> getAllUsers();
	public Users getUserById(int id);
	public Users getUserByName(String name, String pswd);
	public boolean checkUserByName(String name, String email);
	
	public Users addUser(Users newUser);
	public boolean updateUser(Users updatedUser);
}
