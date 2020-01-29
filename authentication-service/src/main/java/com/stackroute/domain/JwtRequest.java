package com.stackroute.domain;


import java.io.Serializable;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	private String emailId;
	private String password;
	
	//need default constructor for JSON Parsing
	public JwtRequest()
    {
        
    }
    public JwtRequest(String emailId, String password) {
        this.setEmailId(emailId);
        this.setPassword(password);
    }
    public String getEmailId() {
        return this.emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}
