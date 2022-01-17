package de.esempe.model.tree;

import java.util.ArrayList;
import java.util.List;

public class Treenode
{
	private long id;
	private String name;
	private int level;
	private List<Treenode> children;

	public Treenode()
	{
		this.children = new ArrayList<>();
	}

	public Treenode(final long id, final String name)
	{
		this.id = id;
		this.name = name;
		this.children = new ArrayList<>();
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
		return this.name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public int getLevel()
	{
		return this.level;
	}

	public void setLevel(final int level)
	{
		this.level = level;
	}

	public List<Treenode> getChildren()
	{
		return this.children;
	}

	public void setChildren(final List<Treenode> children)
	{
		this.children = children;
	}

	@Override
	public String toString()
	{
		return this.getName() + "(ID: " + this.id + ")";
	}

}
