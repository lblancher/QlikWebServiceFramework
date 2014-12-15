REM
REM
REM This setup is based on Commons Daemon that turns Java code into a windows Service.
REM You will need the windows libraries from http://commons.apache.org/proper/commons-daemon/procrun.html
REM 

set SERVICE_NAME=QlikWebServiceProxy
set INSTALL_LOCATION=C:\QlikWSProxy
set PR_INSTALL=%INSTALL_LOCATION%\prunsrv.exe
 
REM Service log configuration
set PR_LOGPREFIX=%SERVICE_NAME%
set PR_LOGPATH=%INSTALL_LOCATION%
set PR_STDOUTPUT=%INSTALL_LOCATION%\stdout.txt
set PR_STDERROR=%INSTALL_LOCATION%\stderr.txt
set PR_LOGLEVEL=Info
 
REM Path to java installation
REM Required Library List (lib folder).  These are all accessible via Maven repositories.  If you have built via Maven, these files can be found in your .m2 directory
REM   - commons-configuration-1.10.jar
REM   - commons-daemon-1.0.15.jar
REM   - commons-io-2.4.jar
REM   - commons-lang-2.6.jar
REM   - commons-logging-api-1.1.jar
REM   - httpclient-4.2.6.jar
REM   - httpcore-4.2.5.jar
REM   - jaxrs-api-3.0.10.Final.jar
REM   - json-20090211.jar
REM   - log4j-1.2.17.jar
REM   - resteasy-jaxrs-3.0.10.Final.jar
REM   - servlet-api-2.5.jar
REM   - slf4j-api-1.7.7.jar
REM   - slf4j-log4j12-1.7.7.jar
REM   - tjws-3.0.10.Final.jar
REM   - ws-commons-util-1.0.2.jar
REM   - xmlrpc-client-3.1.3.jar
REM   - xmlrpc-common-3.1.3.jar
REM   - xstream-1.3.1.jar

set PR_JVM=auto
set PR_CLASSPATH=QlikWebServiceProxy-0.0.1-SNAPSHOT.jar;lib/*
 
REM Startup configuration
set PR_STARTUP=auto
set PR_STARTMODE=jvm
set PR_STARTCLASS=com.bizxcel.team.QlikWebServiceProxy.ProxyAppService
set PR_STARTMETHOD=windowsService
set PR_STARTPARAMS=start
REM Shutdown configuration
set PR_STOPMODE=jvm
set PR_STOPCLASS=com.bizxcel.team.QlikWebServiceProxy.ProxyAppService
set PR_STOPMETHOD=windowsService
set PR_STOPPARAMS=stop
 
REM JVM configuration
REM set PR_JVMMS=256
REM set PR_JVMMX=1024
REM set PR_JVMSS=4000
set PR_JVMOPTIONS=-Dlog4j.configuration=file:log4j.xml
REM Register the service
prunsrv.exe //IS//%SERVICE_NAME%

REM Use this command to edit the setting for the service
REM prunmgr.exe //DS//%SERVICE_NAME%