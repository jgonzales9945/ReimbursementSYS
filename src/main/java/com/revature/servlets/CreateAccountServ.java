package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daos.UserDAOImpl;
import com.revature.pojos.Users;

/**
 * Servlet implementation class CreateAccountServ
 */
public class CreateAccountServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccountServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("CreateAccount.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String authkey = request.getParameter("authkey");
		String password = request.getParameter("password");
		//create admin account if key is valid
		if(authkey != null && !authkey.isEmpty()) {
			if(authkey.equals("Th3B0$$0fTh1$GYM") || authkey.equals("Cr4$h1ngTh1$Pl4N3")) {
				System.out.println("admin creating");
				switch(createAcc(new Users(username,password,fname,lname,email,2))) {
				case 1://redirect to home page, update error message
					request.setAttribute("errorMessage", "Account already exists");
					request.getRequestDispatcher("./index.jsp").forward(request, response);
					break;
				case 2://redirect to home page, user can login now
					request.setAttribute("successMessage", "Account Created! You can now sign in");
					request.getRequestDispatcher("./index.jsp").forward(request, response);
					break;
				case 3://go back to create page and tell the user their account can't be created
					request.setAttribute("errorMessage", "Account could not be created");
					request.getRequestDispatcher("./CreateAccount.jsp").forward(request, response);
					break;
				}
			}else {
				System.out.println("admin created");
				//this should occur if the user inputed the wrong key
				//we don't want them to make the account even after the key failing, they may want to retry
				request.setAttribute("errorMessage", "Invalid Key");
				request.getRequestDispatcher("./CreateAccount.jsp").forward(request, response);
			}
		} else { //create user account instead
			System.out.println("user creating");
			switch(createAcc(new Users(username,password,fname,lname,email,1))) {
			case 1://redirect to home page, update error message
				request.setAttribute("errorMessage", "Account already exists");
				request.getRequestDispatcher("./index.jsp").forward(request, response);
			case 2://redirect to home page, user can login now
				request.setAttribute("successMessage", "Account Created! You can now sign in");
				request.getRequestDispatcher("./index.jsp").forward(request, response);
			case 3://go back to create page and tell the user their account can't be created
				request.setAttribute("errorMessage", "Account could not be created");
				request.getRequestDispatcher("./CreateAccount.jsp").forward(request, response);
			}
			System.out.println("user created");
		}
	}
	protected int createAcc(Users use) {
		//check to see if the user already exists
		if(new UserDAOImpl().checkUserByName(use.getUserName(), use.getEmail())) {
			return 1;
		}
		//add new user, should return false if user name fails or some other condition 
		if(new UserDAOImpl().addUser(use) != null) {
			return 2;
		}
		return 3;
	}
}
