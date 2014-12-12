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
import org.json.JSONArray;
import org.json.XML;

import com.bizxcel.team.QlikWebServiceProxy.ProxyServiceBase;

@Path("/Get2Get_JSON2XML")
public class Get2Get_JSON2XML extends ProxyServiceBase{
	
	@GET
	@Path("/")
	//@Consumes("application/json")
	public Response createProductInJSON(@Context HttpServletRequest request,
				@QueryParam("url") String url) {
 
		
		
		Response ret = null;
		
		
		try {
			
			/*
			 * Check the IP whitelist
			 */
			checkForAllowClient(request);
			
			ClientRequest proxyRequest = new ClientRequest(new URL(url).toExternalForm());
			proxyRequest.accept("application/json");
			ClientResponse<String> responseObj = proxyRequest.get(String.class);
			
			String responseBody = responseObj.getEntity(String.class);
			
			if(responseObj.getResponseStatus() != ClientResponse.Status.OK){
				System.out.println("Called Faild");
				ret = Response.status(Status.INTERNAL_SERVER_ERROR).entity("").build();
			}else{
				
				//TODO: check to see if parsing should be done by and array [
				// or an object
				JSONArray json = new JSONArray(responseBody);
				String xml = XML.toString(json);
				
				ret = Response.status(Status.OK).entity(xml).build();
			}
			
		
		} catch (Exception e) {
			ret = Response.status(Status.INTERNAL_SERVER_ERROR).entity("").build();
			e.printStackTrace();
		}

		
		
		
		return ret;
 
	}

}
