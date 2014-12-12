package com.bizxcel.team.QlikWebServiceProxy.general;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import com.bizxcel.team.QlikWebServiceProxy.ProxyServiceBase;

@Path("/Get2Post_XML2XML")
public class Get2Post_XML2XML extends ProxyServiceBase{
	
	@GET
	@Path("/")
	//@Consumes("application/json")
	public Response createProductInJSON(@Context HttpServletRequest request,@QueryParam("url") String url,@QueryParam("post") String post) {
 
		
		
		Response ret = null;
		
		
		try {
			
			/*
			 * Check the IP whitelist
			 */
			checkForAllowClient(request);
			
			ClientRequest proxyRequest = new ClientRequest(new URL(url).toExternalForm());
			proxyRequest.accept("application/xml");
			proxyRequest.body("application/json", post);
			ClientResponse<String> responseObj = proxyRequest.post(String.class);
			
			String responseBody = responseObj.getEntity(String.class);
			
			if(responseObj.getResponseStatus() != ClientResponse.Status.OK){
				System.out.println("Called Faild");
				ret = Response.status(Status.INTERNAL_SERVER_ERROR).entity("").build();
			}else{
				
				ret = Response.status(Status.OK).entity(responseBody).build();
			}
			
		
		} catch (Exception e) {
			ret = Response.status(Status.INTERNAL_SERVER_ERROR).entity("").build();
			e.printStackTrace();
		}

		
		return ret;
 
	}

}
