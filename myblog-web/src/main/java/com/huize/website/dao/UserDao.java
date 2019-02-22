package com.huize.website.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.huize.website.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	@Query("select s from user as s where s.username=?1 and s.password=?2")
	public User findByNameAndPassword(String username, String password);
	@Query("select s from user as s where s.username=?")
	public User findByName(String username);

}
