package edu.school21.annotations;

@HtmlForm(fileName = "user_form.html", action = "/users", method = "post")
public class UserForm {

	@HtmlInput(type = "text", name = "first_name", placeholder = "Enter First Name")
	private String firstName;

	@HtmlInput(type = "text", name = "last_name", placeholder = "Enter Last Name")
	private String lastName;

	@HtmlInput(type = "password", name = "password", placeholder = "Enter Password")
	private String password;
}
