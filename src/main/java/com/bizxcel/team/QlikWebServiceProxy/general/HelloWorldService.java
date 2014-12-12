package com.bizxcel.team.QlikWebServiceProxy.general;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.bizxcel.team.QlikWebServiceProxy.ProxyServiceBase;

@Path("/HelloWorld")
public class HelloWorldService extends ProxyServiceBase {

	@GET
	@Path("/")
	// @Consumes("application/json")
	public Response createProductInJSON(@Context HttpServletRequest request) {

		Response ret = null;
		try {
			/*
			 * Check the IP whitelist
			 */
			checkForAllowClient(request);

			String result = "Hello World";
			
			ret = Response.status(201).entity(result).build();
		} catch (Exception e) {
			ret = Response.status(Status.INTERNAL_SERVER_ERROR).entity("")
					.build();
		}
		return ret;

	}

}
