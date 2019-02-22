package com.huize.website.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.huize.website.dao.ArticleDao;
import com.huize.website.entity.Article;
import com.huize.website.entity.User;

@Controller
public class ArticleController {
	private User user = new User();
	private Article article = new Article();
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ArticleDao articleDao;
	/**
	 * 发布文章
	 * @param request
	 * @param response
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("/uedit")
	public String uedit(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
		user = (User)httpSession.getAttribute("userLogin");
		String title = request.getParameter("title");
		String summary = request.getParameter("summary");
		String content = request.getParameter("content");
		article = articleDao.findByTitle(title);
		String str = "";
		if (article == null) {
			Article article = new Article();
			int cid = 0,hits = 0;
			Date created = new Date();
			article.setCid(cid);
			article.setUserid(user.getUserid());
	        article.setTitle(title);
	        article.setHits(hits);
	        article.setCreated(created);
	        article.setModified(created);
	        article.setSummary(summary);
	        article.setContent(content);
	        articleDao.save(article);
	        str = "redirect:/index";
		}else {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print("<script>alert('文章名不能重复');</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			str = "admin/article_publish";
		}
		return str;
	}
	
	/**
	 * 更新文章页面验证
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping("/goupdate/{id}")
	public ModelAndView  goupdate(HttpSession session, @PathVariable Integer id) {
		String sql = "select * from contents where cid="+ id;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		ModelAndView mav = new ModelAndView("admin/article_manager_updateart");
		mav.addObject("list", list);
		return mav;
	}
	
	/**
	 * update更新文章
	 * @param id
	 * @param title
	 * @param summary
	 * @param content
	 * @return
	 */
	@RequestMapping("/updateart/{id}")
	public String updataEdit(@PathVariable("id") int id,
			@RequestParam("title") String title,
			@RequestParam("summary") String summary,
			@RequestParam("content") String content,
			HttpSession httpSession) {
		user = (User)httpSession.getAttribute("userLogin");
		article = articleDao.findById(id);
		Date modified = new Date();
		article.setTitle(title);
		article.setCreated(article.getCreated());
		article.setModified(modified);
		article.setSummary(summary);
		article.setContent(content);
		articleDao.save(article);
		return "redirect:/article_manager";
	}
	
	/**
	 * 删除文章
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delect/{id}")
	public String delectArtcle(@PathVariable("id") Integer id) {
		articleDao.delete(id);
		return "redirect:/article_manager";
	}

}
