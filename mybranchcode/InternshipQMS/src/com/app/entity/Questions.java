package com.app.entity;

public class Questions {
	


	    public int question_id;
	    public int quiz_id;
	    private String text;
	    private String a, b, c, d;
	    private String correct_option;

	
	


	public Questions() {
		super();
	}

	public Questions(int question_id, int quiz_id, String text, String a, String b, String c, String d, String correct_option) {
		this.question_id = question_id;
		this.quiz_id = quiz_id;
		this.text = text;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.correct_option = correct_option;
	}

	public int getId() {
		return question_id;
	}

	public void setId(int question_id) {
		this.question_id = question_id;
	}

	public int getQuizid() {
		return quiz_id;
	}

	public void setQuizid(int quiz_id) {
		this.quiz_id = quiz_id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getCorrect() {
		return correct_option;
	}

	public void setCorrect(String correct) {
		this.correct_option = correct;
	}


}
