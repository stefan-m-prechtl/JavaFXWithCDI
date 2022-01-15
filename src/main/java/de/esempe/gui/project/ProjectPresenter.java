package de.esempe.gui.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.esempe.gui.BasePresenter;
import de.esempe.model.project.Project;
import de.esempe.service.project.ProjectService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@ApplicationScoped
public class ProjectPresenter extends BasePresenter<ProjectView>
{
	// Model
	List<Project> projectList;

	// Service(s)
	@Inject
	ProjectService service;

	@Inject
	protected ProjectPresenter(final ProjectView view)
	{
		super(view);
		this.projectList = new ArrayList<>();
	}

	void load()
	{
		this.projectList = this.service.loadAll();

		// prepare model as viewmodel
		final ObservableList<String> searchresult = FXCollections.observableArrayList();

		for (final Project project : this.projectList)
		{
			searchresult.add(project.getName());
		}

		this.view.showList(searchresult);

	}

	void loadProject(final String name)
	{
		final Optional<Project> filterResult = this.projectList.stream().filter(p -> p.getName().equals(name)).findFirst();

		if (filterResult.isPresent())
		{
			this.view.showProject(filterResult.get());
		}
	}

}
