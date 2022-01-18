package de.esempe.service.tree;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import de.esempe.model.tree.Tree;
import de.esempe.model.tree.TreeInfo;
import de.esempe.model.tree.Treenode;
import de.esempe.service.AbstractService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TreeService extends AbstractService
{
	public TreeService()
	{
		super("http://localhost:8080/treebackend/demo/treemgmt/fulltree");
	}

	@PostConstruct
	public void init()
	{
		this.initWebTarget();
	}

	public List<TreeInfo> loadAll()
	{
		final Response response = this.doGET("");
		final String content = response.readEntity(String.class);
		final List<TreeInfo> result = this.jsonb.fromJson(content, new ArrayList<TreeInfo>()
		{
		}.getClass().getGenericSuperclass());
		// final List<TreeInfo> result = Arrays.asList(new TreeInfo(1L, "Demo-Tree 1"), new TreeInfo(2L, "Projekt R2"));

		return result;
	}

	public Tree loadTree(final long treeId)
	{
		final Response response = this.doGET("/" + String.valueOf(treeId));
		final String content = response.readEntity(String.class);
		System.out.println(content);
		final Tree result = this.jsonb.fromJson(content, Tree.class);

		return result;
	}

	public Treenode loadNode(final long treeId, final long nodeId)
	{
		final Response response = this.doGET("/" + String.valueOf(treeId) + "/node/" + String.valueOf(nodeId));
		final String content = response.readEntity(String.class);
		System.out.println(content);
		final Treenode result = this.jsonb.fromJson(content, Treenode.class);

		return result;
	}

}
