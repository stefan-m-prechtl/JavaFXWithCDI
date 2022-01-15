package de.esempe.service.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.esempe.model.project.Project;
import de.esempe.service.AbstractService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProjectService extends AbstractService
{
	public ProjectService()
	{
		super("http://localhost:8080/monolith/rext/projectmgmt", "project");
	}

	@PostConstruct
	public void init()
	{
		this.initWebTarget();
	}

	public List<Project> loadAll()
	{
		final ArrayList<Project> result = new ArrayList<>();

		// final Response response = this.doGET();
		// final String content = response.readEntity(String.class);
		// final List<ProjectDTO> listTO = this.jsonb.fromJson(content, new ArrayList<ProjectDTO>(){}.getClass().getGenericSuperclass());

		final List<ProjectDTO> listTO = Arrays.asList(new ProjectDTO(new Project(1L, "Demo-Projekt")), new ProjectDTO(new Project(2L, "Projekt R2")));

		for (final ProjectDTO to : listTO)
		{
			result.add(to.getData());
			System.out.println(to.getData());
		}

		return result;

	}
}
