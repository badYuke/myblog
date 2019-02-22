package com.huize.website.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="comments")
public class Comments {
	@Id
	@GeneratedValue
	private int cmid;
	private int cid;
	private String cm_nick;
	private String cm_email;
	private Date cm_date;
	private String cm_text;
	private boolean state;
	private int parent;
	
	public int getCmid() {
		return cmid;
	}
	public void setCmid(int cmid) {
		this.cmid = cmid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCm_nick() {
		return cm_nick;
	}
	public void setCm_nick(String cm_nick) {
		this.cm_nick = cm_nick;
	}
	public String getCm_email() {
		return cm_email;
	}
	public void setCm_email(String cm_email) {
		this.cm_email = cm_email;
	}
	public Date getCm_date() {
		return cm_date;
	}
	public void setCm_date(Date cm_date) {
		this.cm_date = cm_date;
	}
	public String getCm_text() {
		return cm_text;
	}
	public void setCm_text(String cm_text) {
		this.cm_text = cm_text;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}

}
