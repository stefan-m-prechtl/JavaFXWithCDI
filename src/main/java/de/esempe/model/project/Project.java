package de.esempe.model.project;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Project implements Serializable
{
	private static final long serialVersionUID = 1L;

	private SimpleStringProperty propName = new SimpleStringProperty();

	private long id;
	private String description;

	public Project(final long id, final String name)
	{
		this.id = id;
		this.setName(name);
	}

	public long getId()
	{
		return this.id;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(final String description)
	{
		this.description = description;
	}

	public void setName(final String value)
	{
		this.propName.set(value);
	}

	public String getName()
	{
		return this.propName.get();
	}

	public StringProperty nameProperty()
	{
		return this.propName;
	}

	@Override
	public String toString()
	{
		return this.getName() + "(ID: " + this.id + ")";
	}

}
