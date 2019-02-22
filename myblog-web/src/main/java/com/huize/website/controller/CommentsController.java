package com.huize.website.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.huize.website.dao.CommentsDao;
import com.huize.website.entity.Comments;

@Controller
public class CommentsController {
	private Comments comments = new Comments();
	@Autowired
	private CommentsDao commentsDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 评论管理
	 * 
	 * @return
	 */
	@RequestMapping("/comments")
	public ModelAndView comments(HttpSession httpSession) {
		String sql = "select comments.*,contents.title from comments inner join contents on comments.cid=contents.cid";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		ModelAndView mav = new ModelAndView("admin/comments");
		mav.addObject("list", list);
		return mav;
	}
	
	
	@RequestMapping("/comments/{cid}")
	public String comments(HttpServletRequest request,HttpServletResponse response, HttpSession session,@PathVariable Integer cid) {
		String cm_nick = request.getParameter("cm_nick");
		String cm_email = request.getParameter("cm_email");
		String cm_text = request.getParameter("cm_text");
		Comments comments = new Comments();
		int cmid = 0, parent = 0;
		boolean state = false;
		Date cm_date = new Date();
		comments.setCid(cid);
		comments.setCmid(cmid);
		comments.setCm_date(cm_date);
		comments.setCm_email(cm_email);
		comments.setCm_nick(cm_nick);
		comments.setCm_text(cm_text);
		comments.setState(state);
		comments.setParent(parent);
		commentsDao.save(comments);
		return "redirect:/lookarticle/"+cid;
	}
	
	@RequestMapping("/comments_audit/{cmid}")
	public String commentsAudit(@PathVariable Integer cmid) {
		comments = commentsDao.findByCmid(cmid);
		comments.setState(true);
		commentsDao.save(comments);
		return "redirect:/comments";
	}
	
	@RequestMapping(value = "/delect_comments/{id}")
	public String delectComments(@PathVariable("id") Integer cmid) {
		commentsDao.delete(cmid);
		return "redirect:/comments";
	}
}
