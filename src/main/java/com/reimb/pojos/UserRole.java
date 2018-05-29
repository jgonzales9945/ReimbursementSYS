/**
 * 
 */
package com.revature.pojos;

/**
 * @author tritonium
 *
 */
public class UserRole {
	
	private int[] ID = {1,2};
	private String[] role = {"employee", "manager"};
	/**
	 * 
	 */
	public UserRole() {
		
	}
	public int getID(int i) {
		return ID[--i];
	}
	public String getRole(int id) {
		return role[id];
	}

}
