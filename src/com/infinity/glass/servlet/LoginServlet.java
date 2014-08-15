package com.infinity.glass.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infinity.glass.manager.ManagerFactory;
import com.infinity.glass.manager.UserIdentityManager;
import com.infinity.glass.model.UserIdentity;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);
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
		if(request.isUserInRole("GUEST")) {
			LOGGER.info("User is a guest");
		} else {
			LOGGER.info("User is not a guest");
		}
		UserIdentityManager uim = ManagerFactory.getUserIdentityManager();			// get user identity manager
		UserIdentity ui = uim.getUserIdentity(request);								// get the user's identity.
		request.getSession().setAttribute("glass.user.id", ui.getUserId());			// set unique id into session
		request.getRequestDispatcher("index.html").forward(request, response);
	}

}
