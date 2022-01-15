package de.esempe.service;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AbstractService
{
	protected Client client;
	protected WebTarget webtarget;

	protected String applicationPath;
	protected String resourcePath;

	protected Jsonb jsonb;

	protected AbstractService(final String applicationPath, final String resourcePath)
	{
		this.applicationPath = applicationPath;
		this.resourcePath = resourcePath;

		this.jsonb = JsonbBuilder.create(new JsonbConfig());
	}

	protected void initWebTarget()
	{
		this.client = ClientBuilder.newClient();
		this.webtarget = this.client.target(this.applicationPath).path(this.resourcePath);
	}

	protected Response doGET()
	{
		return this.webtarget.request(MediaType.APPLICATION_JSON).get();
	}

}
