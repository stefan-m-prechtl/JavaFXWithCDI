package de.esempe.gui.project;

import java.time.LocalDate;

import de.esempe.gui.BaseView;
import de.esempe.model.project.Project;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Stefan Prechtl (www.esempe.de)
 *
 */
@ApplicationScoped
public class EditProjectView extends BaseView<GridPane, EditProjectPresenter>
{

	// ### GUI elements: Controls, Layouts,...
	private TextField txtTitle;

	// ### view data for binding to controls ####
	private final ObjectProperty<Project> currentProject = new SimpleObjectProperty<>();

	@Inject
	public EditProjectView(final EditProjectPresenter presenter)
	{
		super(presenter, null);
	}

	@Override
	@PostConstruct
	protected void initView()
	{
		this.root = new GridPane();
		this.root.setGridLinesVisible(false);
		this.root.setAlignment(Pos.TOP_LEFT);
		this.root.setHgap(10);
		this.root.setVgap(10);
		this.root.setPadding(new Insets(25, 25, 25, 25));

		final Text scenetitle = new Text("Edit Project");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

		this.root.add(scenetitle, 0, 0, 2, 1);

		// add(...,Col,Row)
		this.root.add(new Label("Project name"), 0, 1);
		this.txtTitle = new TextField();
		this.root.add(this.txtTitle, 1, 1);

		this.root.add(new Label("Project admin"), 0, 2);
		final ChoiceBox<String> cb = new ChoiceBox<>();
		cb.setItems(FXCollections.observableArrayList("Eva Mustermann", "Anton Admin", "Donald Duck"));
		cb.setTooltip(new Tooltip("Select the admin"));

		this.root.add(cb, 1, 2);

		this.root.add(new Label("Project start date"), 0, 3);
		final DatePicker startDatePicker = new DatePicker();
		startDatePicker.setValue(LocalDate.now());
		this.root.add(startDatePicker, 1, 3);

		this.root.add(new Label("Project end date"), 0, 4);
		final DatePicker endDatePicker = new DatePicker();
		endDatePicker.setValue(LocalDate.now().plusDays(14));
		this.root.add(endDatePicker, 1, 4);

	}

	public void showProject(final Project project)
	{
		if (null != this.currentProject.getValue())
		{
			this.txtTitle.textProperty().unbindBidirectional(this.currentProject.getValue().nameProperty());

		}

		this.currentProject.set(project);
		this.txtTitle.textProperty().bindBidirectional(this.currentProject.getValue().nameProperty());
	}

}
