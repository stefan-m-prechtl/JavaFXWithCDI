package de.esempe.model;

import java.time.LocalDateTime;

public class UserSession
{
	private final User user;
	private final LocalDateTime loginDateTime;

	public UserSession(final User user)
	{
		this.user = user;
		this.loginDateTime = LocalDateTime.now();
	}

	public User getUser()
	{
		return this.user;
	}

	public LocalDateTime getLoginDateTime()
	{
		return this.loginDateTime;
	}
}
