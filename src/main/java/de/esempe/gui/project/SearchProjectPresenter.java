package de.esempe.gui.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.esempe.gui.BasePresenter;
import de.esempe.gui.MessageEvent;
import de.esempe.model.project.Project;
import de.esempe.service.project.ProjectService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Stefan Prechtl (www.esempe.de)
 */
@ApplicationScoped
public class SearchProjectPresenter extends BasePresenter<SearchProjectView>
{

	// Model
	List<Project> projectList;

	// Service(s)
	@Inject
	ProjectService service;
	@Inject
	Event<Project> projectChanged;

	@Inject
	Event<MessageEvent> eventSource;

	@Inject
	protected SearchProjectPresenter(final SearchProjectView view)
	{
		super(view);
		final List<Project> projectList = new ArrayList<>();
	}

	void searchProjects(final String title)
	{
		// get data from service and set model
		this.projectList = this.service.loadAll();

		// prepare model as viewmodel
		final ObservableList<String> searchresult = FXCollections.observableArrayList();

		for (final Project project : this.projectList)
		{
			searchresult.add(project.getName());
		}

		this.view.showSearchResult(searchresult);

	}

	public void getDetails(final String projectName)
	{
		final Optional<Project> project = this.projectList.stream().filter(x -> projectName.equals(x.getName())).findFirst();

		if (project.isPresent())
		{
			this.projectChanged.fire(project.get());
		}
	}

}
