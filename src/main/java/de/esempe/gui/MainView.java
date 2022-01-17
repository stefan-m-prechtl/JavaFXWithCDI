package de.esempe.gui;

import de.esempe.gui.help.HelpDialog;
import de.esempe.gui.project.EditProjectView;
import de.esempe.gui.project.ProjectView;
import de.esempe.gui.project.SearchProjectView;
import de.esempe.gui.tree.TreeInfoView;
import de.esempe.model.UserSession;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

/**
 *
 * @author @author Stefan Prechtl (www.esempe.de)
 */
@ApplicationScoped
public class MainView extends BaseView<BorderPane, MainPresenter>
{
	enum PERSPECTIVE_TYPE
	{
		ADMIN, USER, TREE
	}

	@Inject
	private ApplicationRegistry registry;
	private UserSession session;

	// ### GUI elements: Controls, Layouts,...
	private MenuItem openFile;
	private MenuItem closeFile;
	private MenuItem exitFile;
	private RadioMenuItem perspectiveAdmin;
	private RadioMenuItem perspectiveUser;
	private RadioMenuItem perspectiveTree;
	private MenuItem openHelp;
	private MenuItem aboutHelp;

	@Inject
	public MainView(final MainPresenter presenter, final ApplicationRegistry registry)
	{
		super(presenter, registry);
	}

	@Override
	@PostConstruct
	protected void initView()
	{
		// this.session = this.registry.getUserSession();

		// create GUI
		this.createGui();
		// initialize data binding
		this.initDatabinding();
		// initialize GUI behavior
		this.initBehavior();
		// initialize action handler
		this.initActionHandler();

		// Initiale Perspektive setzen
		this.changePerspective(PERSPECTIVE_TYPE.TREE);

	}

	private void createGui()
	{
		this.root = new BorderPane();
		this.root.setTop(this.createMainMenu());

		// final ProjectView project = CDI.COMTAINTER.getType(ProjectView.class);
		// this.root.setCenter(project.getRoot());

		// final HBox menuBox = new HBox();
		// menuBox.getChildren().add(this.createMainMenu());
		//
		// final MenuButton xxxMenu = new MenuButton("User");
		// xxxMenu.getItems().addAll(Stream.of("Settings", "Change Role",
		// "Logout").map(MenuItem::new).collect(Collectors.toList()));
		// menuBox.getChildren().add(xxxMenu);
		//
		// this.root.setTop(menuBox);

	}

	private Node createMainMenu()
	{
		// create menu "File"
		final Menu fileMenu = new Menu("File");
		this.openFile = new MenuItem("Open File...");
		this.closeFile = new MenuItem("Close File");
		this.exitFile = new MenuItem("Exit");

		fileMenu.getItems().add(this.openFile);
		fileMenu.getItems().add(this.closeFile);
		fileMenu.getItems().add(new SeparatorMenuItem());
		fileMenu.getItems().add(this.exitFile);

		// create menu Menu "Window"
		final Menu windowMenu = new Menu("Window");

		final Menu perspectiveWindow = new Menu("Perspective");
		final ToggleGroup perspectiveGroup = new ToggleGroup();
		this.perspectiveAdmin = new RadioMenuItem("Admin");
		this.perspectiveUser = new RadioMenuItem("User");
		this.perspectiveTree = new RadioMenuItem("Tree");
		this.perspectiveAdmin.setToggleGroup(perspectiveGroup);
		this.perspectiveUser.setToggleGroup(perspectiveGroup);
		this.perspectiveTree.setToggleGroup(perspectiveGroup);

		perspectiveWindow.getItems().addAll(this.perspectiveUser, this.perspectiveAdmin, this.perspectiveTree);

		final MenuItem preferencesWindow = new MenuItem("Preferences");

		windowMenu.getItems().add(perspectiveWindow);
		windowMenu.getItems().add(preferencesWindow);

		// create menu Menu "Help"
		final Menu helpMenu = new Menu("Help");
		this.openHelp = new MenuItem("Open Help...");
		this.aboutHelp = new MenuItem("About");

		helpMenu.getItems().add(this.openHelp);
		helpMenu.getItems().add(this.aboutHelp);

		// add all menus to bar
		final MenuBar menu = new MenuBar();
		menu.getMenus().addAll(fileMenu, windowMenu, helpMenu);

		return menu;
	}

	private void initDatabinding()
	{
	}

	private void initBehavior()
	{
		// final boolean isAdminUser = this.session.getUser().isAdmin();
		// this.perspectiveAdmin.setDisable(!isAdminUser);

	}

	private void initActionHandler()
	{
		this.openFile.setOnAction(e -> this.showFileChooser());
		this.closeFile.setOnAction(null);
		this.exitFile.setOnAction(e -> this.presenter.exit());

		this.perspectiveAdmin.setOnAction(e -> this.presenter.showAdminPerspective());
		this.perspectiveUser.setOnAction(e -> this.presenter.showUserPerspective());
		this.perspectiveTree.setOnAction(e -> this.presenter.showTreePerspective());

		this.openHelp.setOnAction(e -> this.showHelp());
		this.aboutHelp.setOnAction(null);
	}

	// ##### Action Handler ####

	private void showFileChooser()
	{
		final FileChooser dlg = new FileChooser();
		dlg.setTitle("Select file");
		dlg.showOpenDialog(this.getRoot().getScene().getWindow());
	}

	private void showHelp()
	{
		final HelpDialog dlg = CDI.COMTAINTER.getType(HelpDialog.class);
		dlg.showAndWait();
	}

	public void changePerspective(final PERSPECTIVE_TYPE type)
	{
		if (type.equals(PERSPECTIVE_TYPE.ADMIN))
		{
			final SearchProjectView searchView = CDI.COMTAINTER.getType(SearchProjectView.class);
			final EditProjectView editView = CDI.COMTAINTER.getType(EditProjectView.class);

			this.root.setLeft(searchView.getRoot());
			this.root.setCenter(editView.getRoot());
		}
		if (type.equals(PERSPECTIVE_TYPE.USER))
		{
			final ProjectView project = CDI.COMTAINTER.getType(ProjectView.class);
			this.root.setLeft(null);
			// this.root.setCenter(null);
			this.root.setCenter(project.getRoot());
		}
		if (type.equals(PERSPECTIVE_TYPE.TREE))
		{
			final TreeInfoView treeView = CDI.COMTAINTER.getType(TreeInfoView.class);
			this.root.setLeft(null);
			// this.root.setCenter(null);
			this.root.setCenter(treeView.getRoot());
		}

	}
}
