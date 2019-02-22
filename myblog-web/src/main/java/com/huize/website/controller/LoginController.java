package com.huize.website.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.huize.website.dao.UserDao;
import com.huize.website.entity.User;

@Controller
public class LoginController {
	private User user = new User();

	@Autowired
	private UserDao userdao;

	
	/**
	 * 登录
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/admin")
	public String login(HttpServletRequest request, HttpSession session) {
		String str = "";
		user = (User) session.getAttribute("userLogin");
		if (user != null) {
			str = "redirect:/index";
		} else {
			str = "admin/login";
		}
		return str;
	}

	/**
	 * 登录验证
	 * 
	 * @param request
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping("/ulogin")
	public String ulogin(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		user = userdao.findByNameAndPassword(username, password);
		String str = "";
		if (user != null) {
			session.setAttribute("userLogin", user);
			str = "redirect:/index";
		} else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print("<script>alert('用户名或密码错误');</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			str = "admin/login";
		}
		return str;
	}

	/**
	 * 注册界面
	 * 
	 * @return
	 */
	@RequestMapping("/register")
	public String register() {
		return "admin/register";
	}

	/**
	 * 注册验证
	 * 
	 * @return
	 */
	@RequestMapping("/uregister")
	public String register(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String str = "";
		if (password.equals(password2)) {
			user = userdao.findByName(username);
			if (user == null) {
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				userdao.save(user);
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out;
				try {
					out = response.getWriter();
					out.print("<script>alert('注册成功，正在为您跳转至登录界面');</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
				str = "admin/login";
			} else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out;
				try {
					out = response.getWriter();
					out.print("<script>alert('用户名已存在！请重新输入');</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
				str = "admin/register";
			}
		} else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print("<script>alert('两次密码不一致！');</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			str = "admin/register";
		}
		return str;
	}
	
	/**
	 * 注销登录
	 * @param request
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpSession httpSession) {
		ModelAndView result = new ModelAndView("redirect:/");
		HttpSession session = request.getSession();//获取当前session
		if(session!=null){
			//User user = (User)session.getAttribute("userLogin");//从当前session中获取用户信息
			session.removeAttribute("userLogin");
			session.invalidate();//关闭session
		}
		return result;
	}
}
