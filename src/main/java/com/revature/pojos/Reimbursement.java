package com.revature.pojos;

import java.io.InputStream;
import java.util.Date;


public class Reimbursement {
	
	private int ID = 0;
	private double amount = 0.0;
	private Date submitted = new Date();
	private Date resolved = null;
	private String description = "empty";
	private InputStream photo = null;
	private int userID = 0;
	private int resolverID = 0;
	private int typeid = 1;
	private int statusid = 1;
	
	/**
	 * @param iD
	 * @param ammount
	 * @param submitted
	 * @param resolved
	 * @param description
	 * @param obj
	 * @param userName
	 * @param resolverName
	 * @param typeID
	 * @param statusID
	 */
	public Reimbursement(int iD, double amount, Date submitted, Date resolved, String description, InputStream pho,
			int userName, int resolverName) {
		super();
		ID = iD;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.photo = pho;
		this.userID = userName;
		this.resolverID = resolverName;
	}
	public Reimbursement() {
		// TODO Auto-generated constructor stub
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double ammount) {
		this.amount = ammount;
	}
	public Date getSubmitted() {
		return submitted;
	}
	public void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}
	public Date getResolved() {
		return resolved;
	}
	public void setResolved(Date resolved) {
		this.resolved = resolved;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public InputStream getPhoto() {
		return photo;
	}
	public void setPhoto(InputStream inputStream) {
		this.photo = inputStream;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userName) {
		this.userID = userName;
	}
	public int getResolverID() {
		return resolverID;
	}
	public void setResolverID(int resolverName) {
		this.resolverID = resolverName;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(String type) {
		this.typeid = new ReimType().getID(type);
	}
	public void setTypeid(int type) {
		this.typeid = new ReimType().getID(type);
	}
	public int getStatusid() {
		return statusid;
	}
	public void setStatusid(String status) {
		this.statusid = new ReimStatus().getID(status);
	}
	public void setStatusid(int i) {
		this.statusid = new ReimStatus().getID(i);
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	public int getID() {
		return ID;
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
