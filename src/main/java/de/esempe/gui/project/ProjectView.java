package de.esempe.gui.project;

import java.time.LocalDate;

import de.esempe.gui.ApplicationRegistry;
import de.esempe.gui.BaseView;
import de.esempe.model.project.Project;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

@ApplicationScoped
public class ProjectView extends BaseView<BorderPane, ProjectPresenter>
{

	// ### GUI elements: Controls, Layouts,...
	private TextField txtTitle;

	private Button btnLoad;
	private ListView<String> lvwSearchResult;

	// ### view data for binding to controls ####
	private final ListProperty<String> searchResultList = new SimpleListProperty<>();
	private final ObjectProperty<Project> currentProject = new SimpleObjectProperty<>();

	@Inject
	public ProjectView(final ProjectPresenter presenter, final ApplicationRegistry registry)
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
		this.initBehavior();
		// initialize action handler
		this.initActionHandler();
	}

	private void createGui()
	{
		this.root = new BorderPane();
		this.root.setLeft(this.createSearchPane());
		this.root.setCenter(this.createEditPane());
	}

	private VBox createSearchPane()
	{
		final VBox result = new VBox();
		result.setPadding(new Insets(10, 20, 20, 20));
		result.setSpacing(15);

		this.lvwSearchResult = new ListView<>();
		this.btnLoad = new Button("Laden");

		result.getChildren().addAll(this.lvwSearchResult, this.btnLoad);

		return result;
	}

	private GridPane createEditPane()
	{
		final GridPane result = new GridPane();

		result.setGridLinesVisible(false);
		result.setAlignment(Pos.TOP_LEFT);
		result.setHgap(10);
		result.setVgap(10);
		result.setPadding(new Insets(25, 25, 25, 25));

		final Text scenetitle = new Text("Edit Project");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

		result.add(scenetitle, 0, 0, 2, 1);

		// add(...,Col,Row)
		result.add(new Label("Project name"), 0, 1);
		this.txtTitle = new TextField();
		result.add(this.txtTitle, 1, 1);

		result.add(new Label("Project start date"), 0, 2);
		final DatePicker startDatePicker = new DatePicker();
		startDatePicker.setValue(LocalDate.now());
		result.add(startDatePicker, 1, 2);

		result.add(new Label("Project end date"), 0, 3);
		final DatePicker endDatePicker = new DatePicker();
		endDatePicker.setValue(LocalDate.now().plusDays(14));
		result.add(endDatePicker, 1, 3);

		return result;

	}

	// initialize data binding
	private void initDatabinding()
	{
		this.lvwSearchResult.itemsProperty().bind(this.searchResultList);
	}

	// initialize GUI behavior
	private void initBehavior()
	{

	}

	private void initActionHandler()
	{
		this.btnLoad.setOnAction(e -> this.load());
		this.lvwSearchResult.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> this.loadProject(newValue));
	}

	// ##### Action Handler ####

	private void load()
	{
		this.presenter.load();
	}

	private void loadProject(final String projectName)
	{
		this.presenter.loadProject(projectName);
	}

	// ### Interface for Presenter ####
	void showList(final ObservableList<String> projectnames)
	{
		this.searchResultList.clear();
		this.searchResultList.set(projectnames);
	}

	void showProject(final Project project)
	{
		this.currentProject.set(project);

	}

}
