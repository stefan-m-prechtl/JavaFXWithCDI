package de.esempe.gui;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApplicationRegistry
{
	private final Map<String, Object> objMap = new HashMap<>();

	public void putJsonWebToken(final String token)
	{
		this.objMap.put("jwt", token);
	}

	public String getJsonWebToken()
	{
		return (String) this.objMap.get("jwt");
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
