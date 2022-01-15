package de.esempe.service.project;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTypeAdapter;

import de.esempe.model.project.Project;
import de.esempe.service.AbstractDTO;

public class ProjectDTO extends AbstractDTO<Project>
{
	public final static String PROP_NAME = "project";

	@JsonbCreator
	public ProjectDTO(@JsonbProperty(PROP_NAME) Project data)
	{
		super(data);
	}

	@JsonbTypeAdapter(ProjectJsonAdapter.class)
	@JsonbProperty(PROP_NAME)
	public Project getData()
	{
		return this.data;
	}

}
