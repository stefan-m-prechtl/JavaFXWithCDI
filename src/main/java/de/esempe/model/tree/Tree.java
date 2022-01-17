package de.esempe.model.tree;

public class Tree
{
	private long id;
	private String name;
	private Treenode rootnode;

	public Tree()
	{

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

	public Treenode getRootnode()
	{
		return this.rootnode;
	}

	public void setRootnode(final Treenode rootNode)
	{
		this.rootnode = rootNode;
	}

}
