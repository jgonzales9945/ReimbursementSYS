/**
 * 
 */
package com.revature.pojos;

/**
 * @author Joseph Gonzales
 *
 */
public class Users {
	
	private int id;
	private String userName;
	private String password = "";
	private String fName;
	private String lName;
	private String email;
	private int rID;
	/**
	 * 
	 */
	public Users() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param id
	 * @param userName
	 * @param password
	 * @param fName
	 * @param lName
	 * @param email
	 * @param rID
	 */
	public Users(int id, String userName, String password, String fName, String lName, String email, int rID) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.rID = new UserRole().getID(rID);
	}
	public Users(String userName, String password, String fName, String lName, String email, int rID) {
		super();
		this.userName = userName;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.rID = new UserRole().getID(rID);
	}
	
	public Users(int id) {
		super();
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public int getrID() {
		return rID;
	}
	public void setId(int id) {
		this.id = id;
	}

	public void setrID(int rID) {
		this.rID = new UserRole().getID(rID);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
