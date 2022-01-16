package de.esempe.service.project;

import java.util.UUID;

import javax.json.JsonObject;
import javax.json.bind.adapter.JsonbAdapter;

import de.esempe.model.project.Project;

public class ProjectJsonAdapter implements JsonbAdapter<Project, JsonObject>
{
	@Override
	public Project adaptFromJson(final JsonObject jsonObj) throws Exception
	{
		final UUID objid = UUID.fromString(jsonObj.getString("projectid"));
		final String name = jsonObj.getString("projectname");
		final Project result = new Project(objid, name);

		final String description = jsonObj.getString("description");
		result.setDescription(description);
		// final UUID ownerid = UUID.fromString(jsonObj.getString("owner"));
		// result.setOwnerUserObjid(ownerid);

		return result;
	}

	@Override
	public JsonObject adaptToJson(Project objProject) throws Exception
	{
		// @formatter:off
        final JsonObject result = null; /*Json.createObjectBuilder()
                .add("id", objProject.getId())
                .add("name", objProject.getName())
                .build();*/
        // @formatter:on

		return result;
	}
}
