package com.app.entity;

import java.security.Timestamp;

public class Quiz {
	
	private  int quiz_id;
	private String title;
    private int creator_id;
    private Timestamp created_at;
    
    
	public Quiz() {
		super();
		
	}


	public Quiz(int quiz_id, String title, int creator_id, Timestamp created_at) {
		this.quiz_id = quiz_id;
		this.title = title;
		this.creator_id = creator_id;
		this.created_at = created_at;
	}


	public int getQuiz_id() {
		return quiz_id;
	}


	public void setQuiz_id(int quiz_id) {
		this.quiz_id = quiz_id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getCreator_id() {
		return creator_id;
	}


	public void setCreator_id(int creator_id) {
		this.creator_id = creator_id;
	}


	public Timestamp getCreated_at() {
		return created_at;
	}


	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
    
    

}
