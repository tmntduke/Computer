package com.example.computer.Model;


public class PsychologicalAnswer {
	
	private int id;
	private int path;
	private String result;
	public PsychologicalAnswer(int id, int path, String result) {
		super();
		this.id = id;
		this.path = path;
		this.result = result;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPath() {
		return path;
	}
	public void setPath(int path) {
		this.path = path;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "PsychologicalAnswer [id=" + id + ", path=" + path + ", result="
				+ result + "]";
	}
}

