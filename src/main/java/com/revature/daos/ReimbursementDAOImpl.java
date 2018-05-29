package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.revature.rbconnect.*;
import com.revature.pojos.Reimbursement;
import com.revature.pojos.Users;

//the most broken class in the package
public class ReimbursementDAOImpl implements ReimbursementDAO {

	public ArrayList<Reimbursement> getAllPendingReimbursements() {
		ArrayList<Reimbursement> tickets = new ArrayList<Reimbursement>();

		try(Connection conn = ReimAccess.getInstance().getConnection()) {

			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_STAT_ID = 1";
			Statement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				Reimbursement temp = new Reimbursement();
				temp.setID(rs.getInt("REIMB_ID"));
				temp.setAmount(rs.getDouble("REIMB_AMOUNT"));
				temp.setSubmitted(new java.util.Date(rs.getTimestamp("REIMB_SUBMITTED").getTime()));
				if(rs.getDate("REIMB_RESOLVED") != null) temp.setResolved(new java.util.Date(rs.getTimestamp("REIMB_SUBMITTED").getTime()));
				if(rs.getString("REIMB_DESC") != null) temp.setDescription(rs.getString("REIMB_DESC"));
				//if(rs.getString("REIMB_RECEIPT") != null) temp.setPhoto(rs.getBlob("REIMB_RECEIPT").getBinaryStream());
				temp.setUserID(rs.getInt("REIMB_AUTHOR"));
				if(rs.getString("REIMB_RESOLVER") != null)temp.setResolverID(rs.getInt("REIMB_RESOLVER"));
				temp.setTypeid(rs.getInt("REIMB_TYPE_ID"));
				temp.setStatusid(rs.getInt("REIMB_STAT_ID"));
				tickets.add(temp);
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return tickets;
	}
	public ArrayList<Reimbursement> getAllPendingReimbursementsByUser(Users currentUser) {
		ArrayList<Reimbursement> tickets = new ArrayList<Reimbursement>();

		try(Connection conn = ReimAccess.getInstance().getConnection()) {

			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHOR = ? AND REIMB_STAT_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, currentUser.getId());
			pstmt.setInt(2, 1);
			System.out.println(pstmt.toString());
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				Reimbursement temp = new Reimbursement();
				temp.setID(rs.getInt("REIMB_ID"));
				temp.setAmount(rs.getDouble("REIMB_AMOUNT"));
				temp.setSubmitted(new java.util.Date(rs.getTimestamp("REIMB_SUBMITTED").getTime()));
				if(rs.getDate("REIMB_RESOLVED") != null) temp.setResolved(new java.util.Date(rs.getTimestamp("REIMB_SUBMITTED").getTime()));
				if(rs.getString("REIMB_DESC") != null) temp.setDescription(rs.getString("REIMB_DESC"));
				//if(rs.getString("REIMB_RECEIPT") != null) temp.setPhoto(rs.getBlob("REIMB_RECEIPT").getBinaryStream());
				temp.setUserID(rs.getInt("REIMB_AUTHOR"));
				if(rs.getString("REIMB_RESOLVER") != null)temp.setResolverID(rs.getInt("REIMB_RESOLVER"));
				temp.setTypeid(rs.getInt("REIMB_TYPE_ID"));
				temp.setStatusid(rs.getInt("REIMB_STAT_ID"));
				tickets.add(temp);
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return tickets;
	}

	public ArrayList<Reimbursement> getAllReimbursementsByUser(Users currentUser) {
		ArrayList<Reimbursement> tickets = new ArrayList<Reimbursement>();

		try(Connection conn = ReimAccess.getInstance().getConnection()) {

			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHOR = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			System.out.println(currentUser.getId());
			pstmt.setInt(1, currentUser.getId());
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				Reimbursement temp = new Reimbursement();
				temp.setID(rs.getInt("REIMB_ID"));
				temp.setAmount(rs.getDouble("REIMB_AMOUNT"));
				temp.setSubmitted(new java.util.Date(rs.getTimestamp("REIMB_SUBMITTED").getTime()));
				if(rs.getDate("REIMB_RESOLVED") != null) temp.setResolved(new java.util.Date(rs.getTimestamp("REIMB_SUBMITTED").getTime()));
				if(rs.getString("REIMB_DESC") != null) temp.setDescription(rs.getString("REIMB_DESC"));
				//if(rs.getString("REIMB_RECEIPT") != null) temp.setPhoto(rs.getBlob("REIMB_RECEIPT").getBinaryStream());
				temp.setUserID(rs.getInt("REIMB_AUTHOR"));
				if(rs.getString("REIMB_RESOLVER") != null)temp.setResolverID(rs.getInt("REIMB_RESOLVER"));
				temp.setTypeid(rs.getInt("REIMB_TYPE_ID"));
				temp.setStatusid(rs.getInt("REIMB_STAT_ID"));
				tickets.add(temp);
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return tickets;
	}

	public ArrayList<Reimbursement> getAllReimbursementsByResolver(Users currentResolver) {
		ArrayList<Reimbursement> tickets = new ArrayList<Reimbursement>();

		try(Connection conn = ReimAccess.getInstance().getConnection()) {

			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_RESOLVER = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, currentResolver.getId());
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				Reimbursement temp = new Reimbursement();
				temp.setID(rs.getInt("REIMB_ID"));
				temp.setAmount(rs.getDouble("REIMB_AMOUNT"));
				temp.setSubmitted(new java.util.Date(rs.getTimestamp("REIMB_SUBMITTED").getTime()));
				if(rs.getDate("REIMB_RESOLVED") != null) temp.setResolved(new java.util.Date(rs.getTimestamp("REIMB_SUBMITTED").getTime()));
				if(rs.getString("REIMB_DESC") != null) temp.setDescription(rs.getString("REIMB_DESC"));
				//if(rs.getString("REIMB_RECEIPT") != null) temp.setPhoto(rs.getBlob("REIMB_RECEIPT").getBinaryStream());
				temp.setUserID(rs.getInt("REIMB_AUTHOR"));
				if(rs.getString("REIMB_RESOLVER") != null)temp.setResolverID(rs.getInt("REIMB_RESOLVER"));
				temp.setTypeid(rs.getInt("REIMB_TYPE_ID"));
				temp.setStatusid(rs.getInt("REIMB_STAT_ID"));
				tickets.add(temp);
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return tickets;
	}

	@Override
	public ArrayList<Reimbursement> getAllReimbursementsByType(int t) {
		ArrayList<Reimbursement> tickets = new ArrayList<Reimbursement>();

		try(Connection conn = ReimAccess.getInstance().getConnection()) {

			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_TYPE_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t);
			ResultSet rs = pstmt.executeQuery(sql);

			while(rs.next()) {
				Reimbursement temp = new Reimbursement();
				temp.setID(rs.getInt("REIMB_ID"));
				temp.setAmount(rs.getDouble("REIMB_AMOUNT"));
				temp.setSubmitted(new java.util.Date(rs.getTimestamp("REIMB_SUBMITTED").getTime()));
				if(rs.getDate("REIMB_RESOLVED") != null) temp.setResolved(new java.util.Date(rs.getTimestamp("REIMB_SUBMITTED").getTime()));
				if(rs.getString("REIMB_DESC") != null) temp.setDescription(rs.getString("REIMB_DESC"));
				//if(rs.getString("REIMB_RECEIPT") != null) temp.setPhoto(rs.getBlob("REIMB_RECEIPT").getBinaryStream());
				temp.setUserID(rs.getInt("REIMB_AUTHOR"));
				if(rs.getString("REIMB_RESOLVER") != null)temp.setResolverID(rs.getInt("REIMB_RESOLVER"));
				temp.setTypeid(rs.getInt("REIMB_TYPE_ID"));
				temp.setStatusid(rs.getInt("REIMB_STAT_ID"));
				tickets.add(temp);
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return tickets;
	}
	
	/**
	 * 
	 */
	public Reimbursement getReimbursementById(int id) {
		Reimbursement ticket = new Reimbursement();

		try(Connection conn = ReimAccess.getInstance().getConnection()) {

			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				System.out.println("id checked");
				ticket.setID(rs.getInt("REIMB_ID"));
				ticket.setAmount(rs.getDouble("REIMB_AMOUNT"));
				ticket.setSubmitted(new java.util.Date(rs.getTimestamp("REIMB_SUBMITTED").getTime()));
				if(rs.getString("REIMB_RESOLVED") != null) ticket.setResolved(new java.util.Date(rs.getTimestamp("REIMB_RESOLVED").getTime()));
				if(rs.getString("REIMB_DESC") != null) ticket.setDescription(rs.getString("REIMB_DESC"));
				//if(rs.getString("REIMB_RECEIPT") != null) ticket.setPhoto(rs.getBlob("REIMB_RECEIPT").getBinaryStream());
				ticket.setUserID(rs.getInt("REIMB_AUTHOR"));
				if(rs.getString("REIMB_RESOLVER") != null)ticket.setResolverID(rs.getInt("REIMB_RESOLVER"));
				ticket.setTypeid(rs.getInt("REIMB_TYPE_ID"));
				ticket.setStatusid(rs.getInt("REIMB_STAT_ID"));
				return ticket;
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return null;
	}

	public String getReimbursementStatus(Reimbursement reim) {

		try(Connection conn = ReimAccess.getInstance().getConnection()) {

			String sql = "SELECT REIMB_STATUS FROM ERS_REIMBURSEMENT_STATUS WHERE REIMB_STATUS_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reim.getStatusid());
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				return rs.getString(1);
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return null;
	}

	public String getReimbursementType(Reimbursement reim) {
		try(Connection conn = ReimAccess.getInstance().getConnection()) {

			String sql = "SELECT REIMB_TYPE FROM ERS_REIMBURSEMENT_TYPE WHERE REIMB_TYPE_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reim.getTypeid());
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				return rs.getString(1);
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return null;
	}

	public Reimbursement addReimbursement(Reimbursement reim, Users newUser, int type) {
		Reimbursement act = new Reimbursement();

		try(Connection conn = ReimAccess.getInstance().getConnection();) {

			conn.setAutoCommit(false);

			String sql = "INSERT INTO ERS_REIMBURSEMENT (REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_DESC, REIMB_RECEIPT, REIMB_AUTHOR, REIMB_STAT_ID, REIMB_TYPE_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
			if(reim.getPhoto() == null) sql = "INSERT INTO ERS_REIMBURSEMENT (REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_DESC, REIMB_AUTHOR, REIMB_STAT_ID, REIMB_TYPE_ID) VALUES (?, ?, ?, ?, ?, ?)";
			String[] keys = new String [1];
			keys[0] = "REIMB_ID";
			
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setDouble(1, reim.getAmount());
			pstmt.setTimestamp(2, new java.sql.Timestamp(reim.getSubmitted().getTime()));
			pstmt.setString(3, reim.getDescription());
			if(reim.getPhoto() != null) {
				pstmt.setBlob(4, reim.getPhoto());
				pstmt.setInt(5, newUser.getId());
				pstmt.setInt(6, 1);
				pstmt.setInt(7, type);
			}
			else {
				pstmt.setInt(4, newUser.getId());
				pstmt.setInt(5, 1);
				pstmt.setInt(6, type);
			}
			int rowsUpdated = pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();

			if(rowsUpdated != 0) {

				while(rs.next()) {
					act.setID(rs.getInt(1));
				}
				act.setSubmitted(new java.util.Date(reim.getSubmitted().getTime()));
				act.setDescription(reim.getDescription());
				act.setUserID(newUser.getId());
				act.setAmount(reim.getAmount());
				act.setStatusid(1);
				act.setTypeid(type);
				conn.commit();
				return act;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateReimbursement(Reimbursement updatedReim, Users resolver) {
		try(Connection conn = ReimAccess.getInstance().getConnection();) {

			conn.setAutoCommit(false);

			String sql = "UPDATE ERS_REIMBURSEMENT SET REIMB_STAT_ID = ?, REIMB_RESOLVED = ?, REIMB_RESOLVER = ? WHERE REIMB_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, updatedReim.getStatusid());
			pstmt.setTimestamp(2, new java.sql.Timestamp(updatedReim.getResolved().getTime()));
			pstmt.setInt(3, resolver.getId());
			pstmt.setInt(4, updatedReim.getID());

			int rowsUpdated = pstmt.executeUpdate();

			if(rowsUpdated != 0) {
				conn.commit();
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean transact() {
		try(Connection conn = ReimAccess.getInstance().getConnection();) {
			conn.commit();
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return false;
	}
}
