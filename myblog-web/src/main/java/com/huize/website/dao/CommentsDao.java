package com.huize.website.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.huize.website.entity.Comments;

@Repository
public interface CommentsDao extends JpaRepository<Comments, Integer>{
	@Query("select s from comments as s where cid=?")
	public Comments findById(Integer cid);
	@Query("select s from comments as s where cmid=?")
	public Comments findByCmid(Integer cmid);
	@Query("select s from comments as s where parent=?")
	public Comments findByTitle(Boolean ranks);

}
