package com.huize.website.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.huize.website.entity.User;


@Controller
public class AdminController {
	private User user = new User();
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 仪表盘
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpSession httpSession, HttpServletResponse response) {
		user = (User)httpSession.getAttribute("userLogin");
		String str = "";
		String sql = "";
		if (user == null) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print("<script>alert('你未登录，不能这么做');</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			sql = "select * from contents";
			str = "admin/login";
		}else {
			sql = "select * from contents where userid=" + user.getUserid();
			str = "admin/index";
		}
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		ModelAndView mav = new ModelAndView(str);
		mav.addObject("list", list);
		return mav;
	}
	
	/**
	 * 发布文章
	 * 
	 * @return
	 */
	@RequestMapping("/article_publish")
	public String particle() {
		return "admin/article_publish";
	}

	/**
	 * 文章管理
	 * 
	 * @return
	 */
	@RequestMapping("/article_manager")
	public ModelAndView marticle(HttpSession httpSession) {
		String sql = "select * from contents";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		ModelAndView mav = new ModelAndView("admin/article_manager");
		mav.addObject("list", list);
		return mav;
	}

	/**
	 * 页面管理
	 * 
	 * @return
	 */
	@RequestMapping("/page")
	public String page() {
		return "admin/page";
	}

	

	/**
	 * 分类/标签
	 * 
	 * @return
	 */
	@RequestMapping("/category")
	public String category() {
		return "admin/category";
	}

	/**
	 * 文件管理
	 * 
	 * @return
	 */
	@RequestMapping("/attach")
	public String attach() {
		return "admin/attach";
	}

	/**
	 * 友链管理
	 * 
	 * @return
	 */
	@RequestMapping("/links")
	public String links() {
		return "admin/links";
	}

	/**
	 * 系统设置
	 * 
	 * @return
	 */
	@RequestMapping("/setting")
	public String setting() {
		return "admin/setting";
	}

}
