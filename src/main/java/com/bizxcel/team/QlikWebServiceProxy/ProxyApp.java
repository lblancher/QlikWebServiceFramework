package com.bizxcel.team.QlikWebServiceProxy;




import org.apache.commons.configuration.PropertiesConfiguration;
import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bizxcel.team.QlikWebServiceProxy.general.Get2Get_JSON2XML;
import com.bizxcel.team.QlikWebServiceProxy.general.Get2Post_XML2XML;
import com.bizxcel.team.QlikWebServiceProxy.general.HelloWorldService;
import com.bizxcel.team.QlikWebServiceProxy.general.Repeater;
import com.bizxcel.team.QlikWebServiceProxy.infusionsoft.DataCount;
import com.bizxcel.team.QlikWebServiceProxy.infusionsoft.DataQuery;

/**
 * Hello world!
 *
 */
public class ProxyApp extends Thread {
	
	public TJWSEmbeddedJaxrsServer tjws = null;
	public static PropertiesConfiguration configs = null;
	
	
	private final Logger logger = LoggerFactory.getLogger(ProxyApp.class);
	
	public Boolean exit = false;
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init() throws Exception {
		
		configs = new  PropertiesConfiguration("proxyapp.properties");
		
		tjws = new TJWSEmbeddedJaxrsServer();
		tjws.setBindAddress(configs.getString("bindaddress","localhost"));
		tjws.setPort(configs.getInt("bindport",8080));
		
	}

	public void run() {
		logger.debug("Entering run()");
		tjws.start();
		tjws.getDeployment().getRegistry().addPerRequestResource(HelloWorldService.class,"test");
		tjws.getDeployment().getRegistry().addPerRequestResource(Repeater.class,"test");
		
		
		tjws.getDeployment().getRegistry().addPerRequestResource(Get2Get_JSON2XML.class,"general");
		tjws.getDeployment().getRegistry().addPerRequestResource(Get2Post_XML2XML.class,"general");
		
		
		tjws.getDeployment().getRegistry().addPerRequestResource(DataQuery.class,"Infusionsoft");
		tjws.getDeployment().getRegistry().addPerRequestResource(DataCount.class,"Infusionsoft");
		
		try {
		    synchronized(exit) {
		        
		         exit.wait();
		        
		        
		    }
		} catch (InterruptedException e) {
		    logger.error("Error waiting for Exit",e);
		}
		
		logger.debug("Exiting run()");
		
	}

	public void shutdown() throws Exception {
		if(tjws != null){
			tjws.stop();
		}
		
		exit = true;
		
	}
	
	
	
	
	
	
}
