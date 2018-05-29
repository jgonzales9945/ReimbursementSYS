package com.revature.servlets;

import java.io.IOException;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.daos.UserDAOImpl;
import com.revature.pojos.Users;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Login.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		String username = request.getParameter("email");
		String password = request.getParameter("password");
		
		Users use = new UserDAOImpl().getUserByName(username, password);
		if(use != null && !session.isNew()) {
			use.setPassword("");
			session.setAttribute("user", use);
			response.sendRedirect("./UserPage");
		}else {
			request.setAttribute("errorMessage", "Invalid login");
			request.getRequestDispatcher("./Login.jsp").forward(request, response);
		}
		
			
	
	}

}
