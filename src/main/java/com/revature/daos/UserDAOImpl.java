/**
 * 
 */
package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.pojos.Users;
import com.revature.rbconnect.ReimAccess;

/**
 * @author Joseph Gonzales
 *
 */
public class UserDAOImpl implements UserDAO {

	/**
	 * 
	 */
	public UserDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Users> getAllUsers() {
		ArrayList<Users> users = new ArrayList<Users>();

		try(Connection conn = ReimAccess.getInstance().getConnection();) {

			String sql = "SELECT * FROM ERS_USERS";
			Statement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			//check results, create a user until result set is empty
			while(rs.next()) {
				Users use = new Users();
				use.setId(rs.getInt("ERS_USER_ID"));
				use.setUserName(rs.getString("ERS_USERNAME"));
				use.setfName(rs.getString("ERS_FIRST_NAME"));
				use.setlName(rs.getString("ERS_LAST_NAME"));
				use.setEmail(rs.getString("ERS_USER_EMAIL"));
				use.setrID(rs.getInt("ERS_USER_ROLE_ID"));
				users.add(use);
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return users;
	}

	public Users getUserById(int id) {
		Users use = new Users();

		try(Connection conn = ReimAccess.getInstance().getConnection()) {

			String sql = "SELECT * FROM ERS_USERS WHERE ERS_USER_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			//check results
			if(rs.next()) {
				use.setId(rs.getInt("ERS_USER_ID"));
				use.setUserName(rs.getString("ERS_USERNAME"));
				use.setfName(rs.getString("ERS_FIRST_NAME"));
				use.setlName(rs.getString("ERS_LAST_NAME"));
				use.setEmail(rs.getString("ERS_USER_EMAIL"));
				use.setrID(rs.getInt("ERS_USER_ROLE_ID"));
				return use;
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return null;
	}

	public Users getUserByName(String email, String pswd) {
		Users use = new Users();

		try(Connection conn = ReimAccess.getInstance().getConnection();) {

			String sql = "SELECT * FROM ERS_USERS WHERE ERS_USER_EMAIL = ? and ERS_PASSWORD = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, pswd);
			ResultSet rs = pstmt.executeQuery();

			//check results
			if(rs.next()) {
				System.out.println("user found");//debug
				use.setId(rs.getInt("ERS_USER_ID"));
				use.setUserName(rs.getString("ERS_USERNAME"));
				use.setfName(rs.getString("ERS_FIRST_NAME"));
				use.setlName(rs.getString("ERS_LAST_NAME"));
				use.setEmail(rs.getString("ERS_USER_EMAIL"));
				use.setrID(rs.getInt("ERS_USER_ROLE_ID"));
				System.out.println(use.toString());//debug
				return use;
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("Could not reliably connect to the server");
		}
		return null;
	}

	public boolean checkUserByName(String name, String email) {
		try(Connection conn = ReimAccess.getInstance().getConnection();) {

			String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERNAME = ? OR ERS_USER_EMAIL = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			ResultSet rs = pstmt.executeQuery();

			//check results
			if(rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("Could not reliably connect to the server");
		}

		return false;
	
	}

	public Users addUser(Users newUser) {
		Users user = null;

		try(Connection conn = ReimAccess.getInstance().getConnection();) {

			conn.setAutoCommit(false);

			String sql = "INSERT INTO ERS_USERS (ERS_USERNAME, ERS_USER_EMAIL, ERS_FIRST_NAME, ERS_LAST_NAME, ERS_PASSWORD, ERS_USER_ROLE_ID) VALUES (?, ?, ?, ?, ?, ?)";
			//remember to set the key to use the pk id of the table
			String[] keys = new String [1];
			keys[0] = "ERS_USER_ID";
			
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			//System.out.println("preparing");
			pstmt.setString(1, newUser.getUserName());
			pstmt.setString(2, newUser.getEmail());
			pstmt.setString(3, newUser.getfName());
			pstmt.setString(4, newUser.getlName());
			pstmt.setString(5, newUser.getPassword());
			pstmt.setInt(6, newUser.getrID());
			//System.out.println(pstmt.toString());
			int rowsUpdated = pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next() && rowsUpdated != 0) {
				System.out.println("Key get");
				user = new Users();
				user.setId(rs.getInt(1));
				user.setUserName(newUser.getUserName());
				user.setEmail(newUser.getEmail());
				user.setrID(newUser.getrID());
				conn.commit();
				
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("Could not reliably connect to the server");
		}

		return null;
	}

	public boolean updateUser(Users updatedUser) {
		try(Connection conn = ReimAccess.getInstance().getConnection();) {

			conn.setAutoCommit(false);
			//update everything, even if only one or two things got changed
			String sql = "UPDATE ERS_USERS SET ERS_USER_EMAIL = ?, ERS_PASSWORD = ? WHERE AccountID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updatedUser.getEmail());
			pstmt.setString(2, updatedUser.getPassword());
			pstmt.setInt(3, updatedUser.getId());

			int rowsUpdated = pstmt.executeUpdate();

			if(rowsUpdated == 1) {
				conn.commit();
				return true;
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (NullPointerException e) {
			System.err.println("Could not reliably connect to the server");
		}
		return false;
	}

}
