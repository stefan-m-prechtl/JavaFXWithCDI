package de.esempe.model;

public class User
{
	private final String login;
	// private String firstname;
	// private String lastname;
	private boolean isAdmin;

	public User(final String login)
	{
		this.login = login;
	}

	public boolean isAdmin()
	{
		return this.isAdmin;
	}

	public void setAdmin(boolean isAdmin)
	{
		this.isAdmin = isAdmin;
	}

	public String getLogin()
	{
		return this.login;
	}

}
