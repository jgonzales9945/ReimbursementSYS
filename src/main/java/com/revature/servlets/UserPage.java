package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.htmlcontainers.StandardLayout;
import com.revature.pojos.Users;


/**
 * Servlet implementation class UserPage
 */
public class UserPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession(false);
		
		Users use;
		if(sess == null) {
			response.sendRedirect("index.jsp");
		} else {
			use = (Users) sess.getAttribute("user");
			StandardLayout st = new StandardLayout();
			request.setAttribute("linkbar", st.setTitle("Home Page") +st.setLinkBar("<a href=\"./CreateReimbursement\">Create Ticket</a> <a href=\"./ViewReimbursements\">View Tickets</a> <a href=\"./Logout\">Log out</a>"));
			request.setAttribute("information", "<h2>Welcome back, "+ use.getfName() + " " + use.getlName() +"</h2>");
			request.getRequestDispatcher("HomePage.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
