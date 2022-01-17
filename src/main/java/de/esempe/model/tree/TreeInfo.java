package de.esempe.model.tree;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;

public class TreeInfo implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long id;
	private long countnodes;
	private int maxlevel;

	private SimpleStringProperty propName = new SimpleStringProperty();

	public TreeInfo()
	{

	}

	public TreeInfo(final long id, final String name)
	{
		this.id = id;
		this.setName(name);
	}

	public long getId()
	{
		return this.id;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return this.propName.get();
	}

	public void setName(final String name)
	{
		this.propName.set(name);
	}

	public long getCountnodes()
	{
		return this.countnodes;
	}

	public void setCountnodes(final long countnodes)
	{
		this.countnodes = countnodes;
	}

	public int getMaxlevel()
	{
		return this.maxlevel;
	}

	public void setMaxlevel(final int maxlevel)
	{
		this.maxlevel = maxlevel;
	}

	@Override
	public String toString()
	{
		return this.getName() + "(ID: " + this.id + ")";
	}

}
