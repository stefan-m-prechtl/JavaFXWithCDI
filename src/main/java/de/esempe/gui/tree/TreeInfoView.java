package de.esempe.gui.tree;

import java.util.ArrayList;
import java.util.List;

import de.esempe.gui.ApplicationRegistry;
import de.esempe.gui.BaseView;
import de.esempe.model.tree.Tree;
import de.esempe.model.tree.TreeInfo;
import de.esempe.model.tree.Treenode;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

@ApplicationScoped
public class TreeInfoView extends BaseView<BorderPane, TreeInfoPresenter>
{
	// ### GUI elements: Controls, Layouts,...
	private Button btnLoad;
	private ListView<TreeInfo> lvwOverview;
	private TreeView<Treenode> tvwTree;

	private TreeItem<Treenode> currentSelectedNode;

	// ### view data for binding to controls ####
	private final ListProperty<TreeInfo> searchResultList = new SimpleListProperty<>();
	private final ObjectProperty<TreeInfo> currentTreeInfo = new SimpleObjectProperty<>();

	@Inject
	protected TreeInfoView(final TreeInfoPresenter presenter, final ApplicationRegistry registry)
	{
		super(presenter, registry);
	}

	@Override
	@PostConstruct
	protected void initView()
	{
		// create GUI
		this.createGui();
		// initialize data binding
		this.initDatabinding();
		// initialize GUI behavior
		// this.initBehavior();
		// initialize action handler
		this.initActionHandler();
	}

	private void createGui()
	{
		this.root = new BorderPane();
		this.root.setLeft(this.createOverviewPane());
		this.root.setCenter(this.createTreePane());
	}

	private VBox createOverviewPane()
	{
		final VBox result = new VBox();
		result.setPadding(new Insets(10, 20, 20, 20));
		result.setSpacing(15);

		this.lvwOverview = new ListView<>();
		this.btnLoad = new Button("Laden");

		result.getChildren().addAll(this.lvwOverview, this.btnLoad);

		return result;
	}

	private VBox createTreePane()
	{
		final VBox result = new VBox();
		result.setPadding(new Insets(10, 20, 20, 20));
		result.setSpacing(15);

		this.tvwTree = new TreeView<>();
		result.getChildren().addAll(this.tvwTree);

		return result;

	}

	private void initDatabinding()
	{
		this.lvwOverview.itemsProperty().bind(this.searchResultList);
	}

	private void initActionHandler()
	{
		this.btnLoad.setOnAction(e -> this.presenter.loadAll());
		this.lvwOverview.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> {
			System.out.println("lade Baum: " + newValue);
			this.presenter.loadTree(newValue);
		});
		this.tvwTree.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> {
			System.out.println("lade Knoten: " + newValue);
			this.currentSelectedNode = newValue;
			this.presenter.loadChildren(newValue);
		});

	}

	// ### Interface for Presenter ####
	public void showTreeInfoList(final ObservableList<TreeInfo> treeinfos)
	{
		this.searchResultList.clear();
		this.searchResultList.set(treeinfos);
	}

	public void showTree(final Tree tree)
	{
		final List<TreeItem<Treenode>> childitems = new ArrayList<>();

		final var rootNode = tree.getRootnode();
		rootNode.getChildren().forEach(child -> {
			final var childItem = new TreeItem<Treenode>(child);
			childitems.add(childItem);
		});

		final var rootItem = new TreeItem<Treenode>(rootNode);
		rootItem.getChildren().addAll(childitems);
		this.tvwTree.setRoot(rootItem);
	}

	public void expandNode(final Treenode expandedTreenode)
	{
		final List<TreeItem<Treenode>> childitems = new ArrayList<>();

		expandedTreenode.getChildren().forEach(child -> {
			final var childItem = new TreeItem<Treenode>(child);
			childitems.add(childItem);
		});

		this.currentSelectedNode.getChildren().clear();
		this.currentSelectedNode.getChildren().addAll(childitems);

	}
}
