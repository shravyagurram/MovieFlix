package io.egen.service;

public class LoginResponse {

	private String token;

	public LoginResponse(final String token) {
        this.token = token;
    }
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}
