package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.daos.ReimbursementDAOImpl;
import com.revature.pojos.ReimType;
import com.revature.pojos.Reimbursement;
import com.revature.pojos.Users;

/**
 * Servlet implementation class CreateReimbursementServ
 */
public class CreateReimbursementServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateReimbursementServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession(false);
		if(sess == null) {
			response.sendRedirect("index.jsp");
		}
		Users use = (Users) sess.getAttribute("user");
		if(use == null) {
			response.sendRedirect("index.jsp");
		}
		request.getRequestDispatcher("CreateReimbursement.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession(false);
		if(sess == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		Users use = (Users) sess.getAttribute("user");
		if(use == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		Reimbursement reim = new Reimbursement();
		//get from type paramet
		reim.setTypeid(new ReimType().getType(Integer.parseInt(request.getParameter("types"))));
		reim.setAmount(Double.parseDouble(request.getParameter("amount")));
		reim.setSubmitted(new java.util.Date());
		//check for null values, reimbursement class should already have those set to null by default anyways
		if(request.getParameter("description") != null && !request.getParameter("description").isEmpty()) reim.setDescription(request.getParameter("description"));
		if(request.getParameter("file") != null  && !request.getParameter("file").isEmpty()) reim.setPhoto(request.getPart("file").getInputStream());
		System.out.println(reim.toString());
		if(createReim(reim, use)) {//success
			request.setAttribute("successMessage","<h2>Reimbursement Submission Complete</h2>");
			request.getRequestDispatcher("./UserPage").forward(request, response);;
		} else {//failure
			request.setAttribute("errorMessage", "<h2>Reimbursement could not be finalized, an error has occured</h2>");
			request.getRequestDispatcher("./CreateReimbursement.jsp").forward(request, response);
		}
	}
		protected boolean createReim(Reimbursement reim, Users use) {
			if(new ReimbursementDAOImpl().addReimbursement(reim, use, reim.getTypeid()) != null) {
				return true;
			}
			return false;
		}

}
