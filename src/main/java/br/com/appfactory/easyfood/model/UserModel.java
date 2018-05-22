package br.com.appfactory.easyfood.model;

import javax.validation.constraints.Pattern;

public class UserModel {

	@Pattern(message = "The login must be alphanumeric only.", regexp = "^[a-zA-Z0-9]*$")
	private final String login;

	@Pattern(message = "The password must contain letter, number, uppercase letter and symbols.", regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
	private final String pass;

	public UserModel(String login, String pass) {
		this.login = login;
		this.pass = pass;
	}

	public String getLogin() {
		return login;
	}

	public String getPass() {
		return pass;
	}

}
