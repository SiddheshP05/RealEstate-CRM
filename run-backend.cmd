@echo off
setlocal
cd /d "%~dp0backend"
if exist "C:\Program Files\Eclipse Adoptium\jdk-17.0.20.101-hotspot\bin\java.exe" set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.20.101-hotspot"
if exist "%~dp0..\..\tools\apache-maven-3.9.16\bin\mvn.cmd" set "MAVEN_HOME=%~dp0..\..\tools\apache-maven-3.9.16"
if exist "%MAVEN_HOME%\bin\mvn.cmd" set "PATH=%MAVEN_HOME%\bin;%JAVA_HOME%\bin;%PATH%"
if not defined SPRING_DATASOURCE_URL set "SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/sai_vandan_crm"
if not defined SPRING_DATASOURCE_USERNAME set "SPRING_DATASOURCE_USERNAME=crm"
if not defined SPRING_DATASOURCE_PASSWORD set "SPRING_DATASOURCE_PASSWORD=crm"
if not defined JWT_SECRET set "JWT_SECRET=local-development-secret-key-change-before-production-123456789"
call mvn spring-boot:run "-Dspring-boot.run.profiles=local"
