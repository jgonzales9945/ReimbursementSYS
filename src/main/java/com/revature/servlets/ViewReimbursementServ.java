package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.*;

import com.revature.daos.ReimbursementDAOImpl;
import com.revature.htmlcontainers.StandardLayout;
import com.revature.pojos.ReimStatus;
import com.revature.pojos.ReimType;
import com.revature.pojos.Reimbursement;
import com.revature.pojos.Users;

/**
 * Servlet implementation class ViewReimbursementServ
 */
public class ViewReimbursementServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private	StandardLayout st = new StandardLayout();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewReimbursementServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession(false);
		if(sess == null) {
			response.sendRedirect("./Login");
		}
		Users use = (Users) sess.getAttribute("user");
		if(use == null) {
			response.sendRedirect("./UserPage");
		}
		if(request.getAttribute("view") != null) {
			ArrayList<Reimbursement> rem = null;
			switch((String) request.getParameter("tickets")) {
			case"1":
				rem = (use.getrID() == 2) ? new ReimbursementDAOImpl().getAllPendingReimbursements() : new ReimbursementDAOImpl().getAllPendingReimbursementsByUser(use);
				break;
			case"2":
				rem = (use.getrID() == 2) ? new ReimbursementDAOImpl().getAllReimbursementsByResolver(use) : new ReimbursementDAOImpl().getAllReimbursementsByUser(use);
				break;
			case"3":
				rem = (use.getrID() == 2) ? new ReimbursementDAOImpl().getAllReimbursementsByType(1) : null;
				break;
			case"4":
				rem = (use.getrID() == 2) ? new ReimbursementDAOImpl().getAllReimbursementsByType(2) : null;
				break;
			case"5":
				rem = (use.getrID() == 2) ? new ReimbursementDAOImpl().getAllReimbursementsByType(3) : null;
				break;
			case"6":
				rem = (use.getrID() == 2) ? new ReimbursementDAOImpl().getAllReimbursementsByType(4) : null;
				break;
			}
			if(rem != null) {
				JSONArray jsa = new JSONArray();
				for(Reimbursement r : rem) {
					JSONObject js = new JSONObject();
					js.append("id", r.getID());
					js.append("amount", r.getAmount());
					js.append("submitted", r.getSubmitted().toString());
					js.append("resolved", r.getResolved().toString());
					js.append("user", r.getUserID());
					js.append("resolve", r.getResolverID());
					js.append("type", new ReimType().getType(r.getTypeid()));
					js.append("status", new ReimStatus().getStatus(r.getStatusid()));
					jsa.put(js);
				}
				response.getWriter().print(jsa.toString());
			}
		}
		System.out.println(use.getrID());
		request.setAttribute("getform", st.viewForm((use.getrID() == 1) ? false : true));
		request.getRequestDispatcher("ViewTicket.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession(false);
		if(sess == null) {
			response.sendRedirect("./Login");
		}
		Users use = (Users) sess.getAttribute("user");
		if(use == null) {
			response.sendRedirect("./UserPage");
		}
		
		if((Integer)request.getAttribute("view") != null) {
			System.out.println(request.getAttribute("view"));
			request.getRequestDispatcher("./ViewInfo").forward(request, response);
		}
		//set the form to use the proper selection according to user role
		request.setAttribute("getform", st.viewForm((use.getrID() == 1) ? false : true));
		
		ArrayList<Reimbursement> reim = null;
		//get reimb array and then display it
		switch(request.getParameter("tickets")) {
			case "1":
				if(use.getrID() == 1) reim = new ReimbursementDAOImpl().getAllPendingReimbursementsByUser(use);
				else reim = new ReimbursementDAOImpl().getAllPendingReimbursements();
				break;
			case "2":
				if(use.getrID() == 1) reim = new ReimbursementDAOImpl().getAllReimbursementsByUser(use);
				else reim = new ReimbursementDAOImpl().getAllReimbursementsByResolver(use);
				break;
			case "3"://user should not access this area
				if(use.getrID() == 1) break;
				reim = new ReimbursementDAOImpl().getAllReimbursementsByType(1);
				break;
			case "4":
				if(use.getrID() == 1) break;
				reim = new ReimbursementDAOImpl().getAllReimbursementsByType(2);
				break;
			case "5":
				if(use.getrID() == 1) break;
				reim = new ReimbursementDAOImpl().getAllReimbursementsByType(3);
				break;
			case "6":
				if(use.getrID() == 1) break;
				reim = new ReimbursementDAOImpl().getAllReimbursementsByType(4);
				break;	
		}
		System.out.println("printing table " + reim.toString());
		//nothing returned? 
		if(reim != null) {
			sess.setAttribute("reim", reim);
			request.setAttribute("infotable", st.reimTable(reim, use));
		}
		request.getRequestDispatcher("./ViewTicket.jsp").forward(request, response);
	}

}
