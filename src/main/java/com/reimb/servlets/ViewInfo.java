package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.daos.ReimbursementDAOImpl;
import com.revature.htmlcontainers.StandardLayout;
import com.revature.pojos.Reimbursement;
import com.revature.pojos.Users;

/**
 * Servlet implementation class ViewInfo
 */
@WebServlet("/ViewInfo")
public class ViewInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("./ViewTicket.jsp");
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
		StandardLayout st = new StandardLayout();
		//check view attribute, display info if it contains a number
		//remember to get by parameter for form posts
		if(request.getParameter("view") != null && Integer.parseInt(request.getParameter("view")) > 0) {
			Reimbursement reimview = new ReimbursementDAOImpl().getReimbursementById(Integer.parseInt(request.getParameter("view")));
			//get id then output it to the reimview
			System.out.println("view " + request.getParameter("view"));
			if(reimview == null) {
				System.out.println("id broke");
			}
			else request.setAttribute("reimview", st.reimView(reimview, use));
			sess.setAttribute("reimview", reimview);
			request.getRequestDispatcher("./ViewTicket.jsp").forward(request, response);
		} else System.out.println("request has no value");
	}

}
