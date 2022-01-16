package de.esempe.service;

import java.util.concurrent.TimeUnit;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class AbstractService
{
	protected Client client = null;
	protected WebTarget target = null;
	protected Invocation.Builder invocationBuilder = null;

	protected String resourcePath;

	protected Jsonb jsonb;

	protected AbstractService(final String resourcePath)
	{
		this.resourcePath = resourcePath;
		this.jsonb = JsonbBuilder.create(new JsonbConfig());
	}

	protected void initWebTarget()
	{
		this.client = ClientBuilder.newBuilder().connectTimeout(100, TimeUnit.MILLISECONDS).readTimeout(2, TimeUnit.MINUTES).build();
		this.target = this.client.target(this.resourcePath);
	}

	protected Response doGET()
	{
		this.invocationBuilder = this.target.request(MediaType.APPLICATION_JSON);
		final var res = this.invocationBuilder.get();
		return res;
	}

}
