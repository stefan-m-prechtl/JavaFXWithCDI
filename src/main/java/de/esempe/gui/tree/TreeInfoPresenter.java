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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

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

	void loadAll()
	{
		this.listTreeInfo = this.service.loadAll();

		// convert model to a view model
		final ObservableList<TreeInfo> viewmodel = FXCollections.observableArrayList();
		viewmodel.addAll(this.listTreeInfo);

		// call view to show the data
		this.view.showTreeInfoList(viewmodel);
	}

	public void loadTree(final TreeInfo treeinfo)
	{
		this.tree = this.service.loadTree(treeinfo.getId());

		// call view to show the data
		this.view.showTree(this.tree);
	}

	public void loadChildren(final TreeItem<Treenode> newValue)
	{
		final var treenode = newValue.getValue();
		System.out.println(treenode);
		final var expandedTreenode = this.service.loadNode(this.tree.getId(), treenode.getId());

		this.view.expandNode(expandedTreenode);
	}

}
