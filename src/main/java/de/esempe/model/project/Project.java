package de.esempe.model.project;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Project implements Serializable
{
	private static final long serialVersionUID = 1L;

	private SimpleStringProperty propName = new SimpleStringProperty();

	private Long id;
	private LocalDateTime startDate;
	private LocalDateTime endDate;

	@JsonbCreator
	public Project(@JsonbProperty("id") Long id, @JsonbProperty("name") String name)
	{
		this.id = id;
		this.setName(name);
	}

	public Long getId()
	{
		return this.id;
	}

	public void setName(final String value)
	{
		this.propName.set(value);
	}

	public String getName()
	{
		return this.propName.get();
	}

	public void setStartdate(final LocalDateTime startDate)
	{
		this.startDate = startDate;
	}

	public void setEnddate(final LocalDateTime endDate)
	{
		this.endDate = endDate;
	}

	public StringProperty nameProperty()
	{
		return this.propName;
	}

	@Override
	public String toString()
	{
		return this.getName() + "(" + this.id + ")";
	}

}
