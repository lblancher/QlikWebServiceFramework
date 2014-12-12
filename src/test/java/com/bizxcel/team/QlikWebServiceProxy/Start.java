package com.bizxcel.team.QlikWebServiceProxy;

import java.io.IOException;

import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;
import org.junit.Test;

import com.bizxcel.team.QlikWebServiceProxy.general.Get2Get_JSON2XML;
import com.bizxcel.team.QlikWebServiceProxy.general.Get2Post_XML2XML;
import com.bizxcel.team.QlikWebServiceProxy.general.HelloWorldService;
import com.bizxcel.team.QlikWebServiceProxy.general.Repeater;
import com.bizxcel.team.QlikWebServiceProxy.infusionsoft.DataCount;
import com.bizxcel.team.QlikWebServiceProxy.infusionsoft.DataQuery;

public class Start {

	@Test
	public void doTest() {
		
		
		ProxyApp app = new ProxyApp();
		
		try {
			app.init();
			app.start();
			System.in.read();
			app.shutdown();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
