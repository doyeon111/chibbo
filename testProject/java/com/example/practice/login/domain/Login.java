package com.example.practice.login.domain;

public class Login {
	
	private int user_no;
	private String user_id;
	private String user_pwd;
	private String user_name;
	private String user_gender;
	private String user_age;
	private String user_phone;
	private String user_addr;
	private String user_birth;
	private String use_yn;
	
	public Login() {
		
	}
	
	public Login(int user_no, String user_id, String user_pwd, String user_name, String user_gender, String user_age,
			String user_phone, String user_addr, String user_birth, String use_yn) {
		super();
		this.user_no = user_no;
		this.user_id = user_id;
		this.user_pwd = user_pwd;
		this.user_name = user_name;
		this.user_gender = user_gender;
		this.user_age = user_age;
		this.user_phone = user_phone;
		this.user_addr = user_addr;
		this.user_birth = user_birth;
		this.use_yn = use_yn;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}
	public String getUser_age() {
		return user_age;
	}
	public void setUser_age(String user_age) {
		this.user_age = user_age;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_addr() {
		return user_addr;
	}
	public void setUser_addr(String user_addr) {
		this.user_addr = user_addr;
	}
	public String getUser_birth() {
		return user_birth;
	}
	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}

	@Override
	public String toString() {
		return "Login [user_no=" + user_no + ", user_id=" + user_id + ", user_pwd=" + user_pwd + ", user_name="
				+ user_name + ", user_gender=" + user_gender + ", user_age=" + user_age + ", user_phone=" + user_phone
				+ ", user_addr=" + user_addr + ", user_birth=" + user_birth + ", use_yn=" + use_yn + "]";
	}
}