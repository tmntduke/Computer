package com.example.computer.Model;



public class Psychological {
	
	//Psychological (心理上的) 
	
	/**
	 * 心理考题类型
	 */
	
	private int id;
	private int quizNo;
	private String quizName;
	private String qOptionA;
	private String qOptionB;
	private int aJumpTo;
	private int bJumpTo;
	
	public Psychological(int id, int quizNo, String quizName, String qOptionA,
			String qOptionB, int aJumpTo, int bJumpTo) {
		super();
		this.id = id;
		this.quizNo = quizNo;
		this.quizName = quizName;
		this.qOptionA = qOptionA;
		this.qOptionB = qOptionB;
		this.aJumpTo = aJumpTo;
		this.bJumpTo = bJumpTo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuizNo() {
		return quizNo;
	}
	public void setQuizNo(int quizNo) {
		this.quizNo = quizNo;
	}
	public String getQuizName() {
		return quizName;
	}
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}
	public String getqOptionA() {
		return qOptionA;
	}
	public void setqOptionA(String qOptionA) {
		this.qOptionA = qOptionA;
	}
	public String getqOptionB() {
		return qOptionB;
	}
	public void setqOptionB(String qOptionB) {
		this.qOptionB = qOptionB;
	}
	public int getaJumpTo() {
		return aJumpTo;
	}
	public void setaJumpTo(int aJumpTo) {
		this.aJumpTo = aJumpTo;
	}
	public int getbJumpTo() {
		return bJumpTo;
	}
	public void setbJumpTo(int bJumpTo) {
		this.bJumpTo = bJumpTo;
	}
	@Override
	public String toString() {
		return "Psychological [id=" + id + ", quizNo=" + quizNo + ", quizName="
				+ quizName + ", qOptionA=" + qOptionA + ", qOptionB="
				+ qOptionB + ", aJumpTo=" + aJumpTo + ", bJumpTo=" + bJumpTo
				+ "]";
	}
}

