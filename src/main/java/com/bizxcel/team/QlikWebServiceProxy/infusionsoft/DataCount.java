package com.bizxcel.team.QlikWebServiceProxy.infusionsoft;

import java.net.URL;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bizxcel.team.QlikWebServiceProxy.ProxyServiceBase;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

@Path("/DataCount")
public class DataCount extends ProxyServiceBase{
	
	private final Logger logger = LoggerFactory.getLogger(DataCount.class);

	@GET
	@Path("/")
	// @Consumes("application/json")
	public Response createProductInJSON(@Context HttpServletRequest request,
			@QueryParam("url") String url,
			@QueryParam("privateKey") String privateKey,
			@QueryParam("tableName") String tableName,
			@QueryParam("queryField") List<String> queryField) {

		

		Response ret = null;

		try {

			/*
			 * Check the IP whitelist
			 */
			checkForAllowClient(request);
			
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL(url));
			XmlRpcClient xmlrpc = new XmlRpcClient();
			xmlrpc.setConfig(config);
			Vector params = new Vector();
			params.addElement(privateKey);
			params.addElement(tableName);
			/*
			 * TODO:Fix to take actual filters instead of faking it
			 */
			params.addElement(new HashMap());
			
			logger.debug("DataCount proxy to " + url);
			

			Object result = xmlrpc.execute("DataService.count", params);
			
			XStream xstream = new XStream();
			xstream.alias("row", Map.class);
			xstream.registerConverter(new MapEntryConverter());
			String xml = xstream.toXML(result);
			
			//String xml = XML.toString(result);
			ret = Response.status(Status.OK).entity(xml).build();
			
		} catch (Exception e) {
			ret = Response.status(Status.INTERNAL_SERVER_ERROR).entity("")
					.build();
			e.printStackTrace();
		}

		return ret;

	}
	
	public static class MapEntryConverter implements Converter {
        public boolean canConvert(Class clazz) {
            return AbstractMap.class.isAssignableFrom(clazz);
        }

        public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
            AbstractMap map = (AbstractMap) value;
            for (Object obj : map.entrySet()) {
                Entry entry = (Entry) obj;
                writer.startNode(entry.getKey().toString());
                writer.setValue(entry.getValue().toString());
                writer.endNode();
            }
        }

        public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
            Map<String, String> map = new HashMap<String, String>();
            while(reader.hasMoreChildren()) {
                reader.moveDown();
                map.put(reader.getNodeName(), reader.getValue());
                reader.moveUp();
            }
            return map;
        }
    }

}
