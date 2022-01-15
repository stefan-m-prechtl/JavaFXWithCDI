package de.esempe.gui.project;

import de.esempe.gui.BasePresenter;
import de.esempe.model.project.Project;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

/**
 *
 * @author Stefan Prechtl (www.esempe.de)
 *
 */
@ApplicationScoped
public class EditProjectPresenter extends BasePresenter<EditProjectView>
{

	@Inject
	protected EditProjectPresenter(final EditProjectView view)
	{
		super(view);
	}

	public void projectChanged(@Observes final Project currentProject)
	{
		this.view.showProject(currentProject);
	}

}
