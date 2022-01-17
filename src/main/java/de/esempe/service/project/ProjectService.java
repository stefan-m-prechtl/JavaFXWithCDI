package de.esempe.service.project;

import java.util.Arrays;
import java.util.List;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.core.Response;

import de.esempe.model.project.Project;
import de.esempe.service.AbstractService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProjectService extends AbstractService
{
	public ProjectService()
	{
		super("http://localhost:8080/app/demo/ping");
		final JsonbConfig config = new JsonbConfig().withAdapters(new ProjectJsonAdapter());
		this.jsonb = JsonbBuilder.create(config);

	}

	@PostConstruct
	public void init()
	{
		this.initWebTarget();
	}

	public List<Project> loadAll()
	{
		final Response response = this.doGET("");
		final String content = response.readEntity(String.class);
		// final List<Project> result = this.jsonb.fromJson(content, new ArrayList<Project>(){}.getClass().getGenericSuperclass());
		final List<Project> result = Arrays.asList(new Project(1L, "Demo-Projekt"), new Project(2L, "Projekt R2"));

		return result;

	}
}
