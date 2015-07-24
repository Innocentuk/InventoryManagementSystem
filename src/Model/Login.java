package Model;

import java.io.Serializable;

public class Login  implements Serializable
{
	private int id;
	private String username;
	private String password;
	private String role;
	private char[] password2;
		
	public Login() {
	}

	public Login(int id, String username, String password, String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setPassword2(char[] password2) {
		this.password2 = password2;
	}
	
}
