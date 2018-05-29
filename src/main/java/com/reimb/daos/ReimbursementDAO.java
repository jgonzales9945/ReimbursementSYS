package com.revature.daos;

import java.util.ArrayList;

import com.revature.pojos.Reimbursement;
import com.revature.pojos.Users;


public interface ReimbursementDAO {
	public ArrayList<Reimbursement> getAllPendingReimbursements();//manager
	public ArrayList<Reimbursement> getAllPendingReimbursementsByUser(Users currentUser);//user
	
	public ArrayList<Reimbursement> getAllReimbursementsByUser(Users currentUser);//user
	public ArrayList<Reimbursement> getAllReimbursementsByResolver(Users currentUser);//manager
	public ArrayList<Reimbursement> getAllReimbursementsByType(int t);//manager
	
	public Reimbursement getReimbursementById(int id);//user and manager
	public String getReimbursementStatus(Reimbursement reim);//user and manager
	public String getReimbursementType(Reimbursement reim);//manager
	
	public Reimbursement addReimbursement(Reimbursement reim, Users newUser, int type);
	//public Reimbursement addReimbursement(Users newUser, String type, Object obj);//send receipt data to the database
	public boolean updateReimbursement(Reimbursement updatedReim, Users resolver);//update status, type, or other information
	//public boolean transferToAccount(double amount, int accountID);//
	

	public boolean transact();
}
