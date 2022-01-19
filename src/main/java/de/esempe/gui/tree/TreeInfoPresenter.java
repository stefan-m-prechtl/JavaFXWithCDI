package de.esempe.gui.tree;

import java.util.ArrayList;
import java.util.List;

import de.esempe.gui.BasePresenter;
import de.esempe.model.tree.Tree;
import de.esempe.model.tree.TreeInfo;
import de.esempe.model.tree.Treenode;
import de.esempe.service.tree.TreeService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TreeInfoPresenter extends BasePresenter<TreeInfoView>
{
	// Model
	private List<TreeInfo> listTreeInfo;
	private Tree tree;

	@Inject
	TreeService service;

	@Inject
	protected TreeInfoPresenter(final TreeInfoView view)
	{
		super(view);
		this.listTreeInfo = new ArrayList<>();
	}

	void loadTreeInfos()
	{
		this.listTreeInfo = this.service.loadAll();
		this.view.showTreeInfoList(this.listTreeInfo);
	}

	public void loadTree(final TreeInfo treeinfo)
	{
		this.tree = this.service.loadTree(treeinfo.getId());
		this.view.showTree(this.tree);
	}

	public void loadChildren(final Treenode node)
	{
		final var expandedTreenode = this.service.loadNode(this.tree.getId(), node.getId());
		this.view.expandNode(expandedTreenode);
	}

}
