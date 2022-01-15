package de.esempe.gui;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

/**
 * CDI-Container als Singleton gekapselt.
 *
 * @author Stefan Prechtl (www.esempe.de)
 */
public enum CDI
{
	COMTAINTER;

	private SeContainer container;

	CDI()
	{
		this.container = SeContainerInitializer.newInstance().initialize();
	}

	public boolean isRunning()
	{
		return this.container.isRunning();
	}

	void shutdown()
	{
		if (this.container.isRunning())
		{
			this.container.close();
		}
	}

	public <T extends Object> T getType(final Class<T> clazz)
	{
		final T result = this.container.select(clazz).get();
		return result;
	}

}
