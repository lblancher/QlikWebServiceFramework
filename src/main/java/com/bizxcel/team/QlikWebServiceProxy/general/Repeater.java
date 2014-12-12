package com.bizxcel.team.QlikWebServiceProxy.general;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bizxcel.team.QlikWebServiceProxy.ProxyServiceBase;

@Path("/repeater")
public class Repeater extends ProxyServiceBase{
	
	private final Logger logger = LoggerFactory.getLogger(Repeater.class);

	@POST
	@Path("/")
	// @Consumes("application/json")
	public Response repeat(@Context HttpServletRequest request) {

		

		Response ret = null;

		try {
			
			/*
			 * Check the IP whitelist
			 */
			checkForAllowClient(request);

			String requestBody = IOUtils.toString(request.getInputStream());
			
			System.out.println(requestBody);

			ret = Response.status(Status.OK).entity(requestBody).build();

		} catch (Exception e) {
			ret = Response.status(Status.INTERNAL_SERVER_ERROR).entity("")
					.build();
			e.printStackTrace();
		}

		return ret;

	}

}
