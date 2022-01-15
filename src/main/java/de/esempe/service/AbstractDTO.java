package de.esempe.service;

import javax.json.bind.annotation.JsonbProperty;

public class AbstractDTO<T>
{
	@JsonbProperty("link")
	protected String linklist;
	@JsonbProperty("self")
	protected String self;

	protected AbstractDTO(T data)
	{
		this.data = data;
	}

	// @JsonbProperty(...) is set by concrete class
	protected T data;

	public String getLinklist()
	{
		return this.linklist;
	}

	public void setLinklist(String linklist)
	{
		this.linklist = linklist;
	}

	public String getSelf()
	{
		return this.self;
	}

	public void setSelf(String self)
	{
		this.self = self;
	}

	public T getData()
	{
		return this.data;
	}

	public void setData(T data)
	{
		this.data = data;
	}
}
