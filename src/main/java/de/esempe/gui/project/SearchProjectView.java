package de.esempe.gui.project;

import de.esempe.gui.BaseView;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Stefan Prechtl (www.esempe.de)
 */
@ApplicationScoped
public class SearchProjectView extends BaseView<VBox, SearchProjectPresenter>
{

	// ### GUI elements: Controls, Layouts,...
	private TextField txtSearch;
	private Button btnOk;
	private ListView<String> lvwSearchResult;

	// ### view data for binding to controls ####
	private final StringProperty searchValue = new SimpleStringProperty();
	private final ListProperty<String> searchResultList = new SimpleListProperty<>();

	@Inject
	public SearchProjectView(final SearchProjectPresenter presenter)
	{
		super(presenter, null);
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
		this.root = new VBox();
		this.root.setPadding(new Insets(10));
		this.root.setSpacing(8);
		this.root.getChildren().add(new Text("Search"));

		final HBox searchBox = new HBox();
		searchBox.setSpacing(8);

		this.txtSearch = new TextField();
		this.txtSearch.setPromptText("Suche...");
		this.txtSearch.setFocusTraversable(false);
		searchBox.getChildren().add(this.txtSearch);
		this.btnOk = new Button("Ok");
		searchBox.getChildren().add(this.btnOk);

		this.root.getChildren().add(searchBox);

		this.lvwSearchResult = new ListView<>();
		this.root.getChildren().add(this.lvwSearchResult);
	}

	private void initDatabinding()
	{
		this.txtSearch.textProperty().bindBidirectional(this.searchValue);
		this.lvwSearchResult.itemsProperty().bind(this.searchResultList);
	}

	private void initBehavior()
	{
		final BooleanBinding searchValueEntered = this.txtSearch.textProperty().isNotEmpty().and(this.txtSearch.textProperty().length().greaterThan(2));
		this.btnOk.disableProperty().bind(searchValueEntered.not());

	}

	private void initActionHandler()
	{
		this.btnOk.setOnAction(e -> this.search());
		this.lvwSearchResult.getSelectionModel().selectedItemProperty()
				.addListener((final ObservableValue<? extends String> ov, final String oldValue, final String newValue) -> this.getDetails(newValue));

	}

	// ##### Action Handler ####
	private void search()
	{
		this.presenter.searchProjects(this.txtSearch.textProperty().get());
	}

	private void getDetails(final String projectName)
	{
		this.presenter.getDetails(projectName);
	}

	// ### Interface for Presenter ####
	void showSearchResult(final ObservableList<String> searchresult)
	{
		this.searchResultList.clear();
		this.searchResultList.set(searchresult);

	}

}
