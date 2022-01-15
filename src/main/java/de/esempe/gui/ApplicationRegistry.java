package de.esempe.gui;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import de.esempe.model.UserSession;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApplicationRegistry
{
	private final Map<String, Object> objMap = new HashMap<>();

	public void putUserSession(final UserSession session)
	{
		this.objMap.put("session", session);
	}

	public UserSession getUserSession()
	{
		return (UserSession) this.objMap.get("session");
	}

	public void putLocale(final Locale locale)
	{
		this.objMap.put("locale", locale);
	}

	public Locale getLocale()
	{
		return (Locale) this.objMap.get("locale");
	}

}
