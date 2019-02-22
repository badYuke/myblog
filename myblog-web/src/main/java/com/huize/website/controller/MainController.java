package com.huize.website.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 主页
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView mainpage() {
		String sql = "select * from contents";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		ModelAndView mav = new ModelAndView("main/main");
		mav.addObject("main", list);
		return mav;
	}
	
	/**
	 * 归档
	 * @return
	 */
	@RequestMapping("/pigeonhole")
	public String pigeonhole() {
		return "main/pigeonhole";
	}
	
	/**
	 * 友链
	 * @return
	 */
	@RequestMapping("/friendlink")
	public String friendlink() {
		return "main/friendlink";
	}
	
	/**
	 * 查看文章
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping("/lookarticle/{id}")
	public ModelAndView  lookarticle(HttpSession session, @PathVariable Integer id) {
		//根据文章id查询文章内容
		String sql = "select * from contents where cid="+ id;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		//根据文章id查询父级评论内容
		String sql1 = "select * from comments where state=true and cid="+ id;
		List<Map<String, Object>> comm1 = jdbcTemplate.queryForList(sql1);
		//根据文章id查询父级评论内容
		String sql2 = "select * from comments where cid="+ id;
		List<Map<String, Object>> comm2 = jdbcTemplate.queryForList(sql2);
		ModelAndView mav = new ModelAndView("main/lookarticle");
		mav.addObject("list", list);
		mav.addObject("comm1", comm1);
		mav.addObject("comm2", comm2);
		return mav;
	}

}
