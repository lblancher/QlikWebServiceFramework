package com.bizxcel.team.QlikWebServiceProxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 * 
 */
public class ProxyAppService  {

	private static ProxyApp proxyAppInstance = null;
	private final static Logger logger = LoggerFactory.getLogger(ProxyAppService.class);
	
	public static void windowsService(String args[]) {
		logger.debug("windowsService(String args[])");
		String cmd = "start";
		if (args.length > 0) {
			cmd = args[0];
		}
		try {
			if ("start".equals(cmd)) {
				proxyAppInstance = new ProxyApp();
				proxyAppInstance.init();

				proxyAppInstance.start();
				
				proxyAppInstance.join();
				
				logger.debug("start cmd finished");
			} else {
				if(proxyAppInstance != null){
					proxyAppInstance.shutdown();
					proxyAppInstance.stop();
				}else{
					logger.error("proxyAppInstance is null!!!");
				}
				
			}
		} catch (Exception e) {
			logger.error("Error controlling program",e);
		}
	}


}
