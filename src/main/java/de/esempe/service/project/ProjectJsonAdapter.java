package de.esempe.service.project;

import java.time.LocalDateTime;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

import de.esempe.model.project.Project;

public class ProjectJsonAdapter implements JsonbAdapter<Project, JsonObject>
{
	@Override
	public Project adaptFromJson(JsonObject json) throws Exception
	{
		final Long id = json.getJsonNumber("id").longValue();
		final String name = json.getString("name");

		final Project result = new Project(id, name);

		result.setStartdate(LocalDateTime.parse(json.getString("startdate")));
		result.setEnddate(LocalDateTime.parse(json.getString("enddate")));

		return result;
	}

	@Override
	public JsonObject adaptToJson(Project objProject) throws Exception
	{
		// @formatter:off
        final JsonObject result = Json.createObjectBuilder()
                .add("id", objProject.getId())
                .add("name", objProject.getName())
                .build();
        // @formatter:on

		return result;
	}
}
