package de.esempe.gui;

/**
 *
 * @author Stefan Prechtl (www.esempe.de)
 */
public class MessageEvent
{

	private final String msg;

	public MessageEvent(final String msg)
	{
		this.msg = msg;
	}

	public String getMsg()
	{
		return this.msg;
	}

}
