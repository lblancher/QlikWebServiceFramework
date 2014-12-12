package com.bizxcel.team.QlikWebServiceProxy;

import javax.servlet.http.HttpServletRequest;

public class ProxyServiceBase {
	
	public void checkForAllowClient(HttpServletRequest request) throws Exception{
		
		
		if(ProxyApp.configs.containsKey("ipwhitelist")){
			
			if(!ProxyApp.configs.getList("ipwhitelist").contains(request.getRemoteAddr())){
				throw new Exception("Host is not allowed");
			}
			
		}
		
	}

}
