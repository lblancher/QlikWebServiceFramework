REM
REM
REM
REM You will need the windows libraries from http://commons.apache.org/proper/commons-daemon/procrun.html
REM 

set SERVICE_NAME=QlikWebServiceProxy
set INSTALL_LOCATION=C:\QlikWSProxy
set PR_INSTALL=%INSTALL_LOCATION%\prunsrv.exe
 
REM Service log configuration
set PR_LOGPREFIX=%SERVICE_NAME%
set PR_LOGPATH=INSTALL_LOCATION%
set PR_STDOUTPUT=cINSTALL_LOCATION%\stdout.txt
set PR_STDERROR=INSTALL_LOCATION%\stderr.txt
set PR_LOGLEVEL=Info
 
REM Path to java installation
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
prunsrv.exe //IS//%SERVICE_NAME%