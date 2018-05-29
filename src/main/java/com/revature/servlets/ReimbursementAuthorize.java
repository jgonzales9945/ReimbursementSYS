package com.revature.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.daos.ReimbursementDAOImpl;
import com.revature.pojos.Reimbursement;
import com.revature.pojos.Users;

/**
 * Servlet implementation class ReimbursementInfoServ
 */
public class ReimbursementAuthorize extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementAuthorize() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/UserPage");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses = request.getSession(false);
		//session didn't exist, get out
		if(ses == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		Users use = (Users) ses.getAttribute("user");
		if(use == null) {//you did not login, get out
			response.sendRedirect("login.jsp");
			return;
		}
		if(use.getrID() == 1) {//you are not authorized to view this page, go back
			response.sendRedirect("./UserPage");
			return;
		}
		if(ses.getAttribute("reimview") != null) {
			//get the reimbursement from the attribute saved in session
			Reimbursement reim = (Reimbursement) ses.getAttribute("reimview");
			//pull the string from the parameter sent in from the ViewInfo servlet
			String str = request.getParameter("approval");
			boolean check = false;
			switch(str) {
			case "1"://approve
				reim.setStatusid(1);
				reim.setResolved(new Date());
				reim.setResolverID(reim.getID());
				check = new ReimbursementDAOImpl().updateReimbursement(reim, use);
				break;
			case "2"://deny
				reim.setStatusid(2);
				reim.setResolved(new Date());
				reim.setResolverID(reim.getID());
				check = new ReimbursementDAOImpl().updateReimbursement(reim, use);
				break;
			case "3"://deny
				reim.setStatusid(3);
				reim.setResolved(new Date());
				reim.setResolverID(reim.getID());
				check = new ReimbursementDAOImpl().updateReimbursement(reim, use);
				break;
			default://do nothing on invalid option
				reim.setStatusid(0);
				break;
			}
			if(check) {//success
				ses.setAttribute("reimview", null);
				ses.setAttribute("reim", null);
				request.setAttribute("successMessage","<h2>Reimbursement Approval Complete</h2>");
				request.getRequestDispatcher("./UserPage").forward(request, response);
			} else {//failure
				request.setAttribute("errorMessage", "<h2>Reimbursement could not be updated, an error has occured</h2>");
				request.getRequestDispatcher("./UserPage").forward(request, response);
			}
		}
	}

}
