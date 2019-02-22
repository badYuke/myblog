package com.huize.website.validation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huize.website.dao.UserDao;
import com.huize.website.entity.User;

@Controller
public class LoginValidation extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user = new User();

	@Autowired
	private UserDao userDao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@RequestMapping(value = "/loginvalidation", method = RequestMethod.POST)
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = "";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
//		System.out.println("NAME: " + username + "\nPASSWORD: " + password);
		user = userDao.findByNameAndPassword(username, password);
		if (user == null) {
			str = "1";
		} else {
			str = "0";
		}
		response.getWriter().print(str);
	}
}