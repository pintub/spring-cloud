# spring-cloud
Spring Cloud 2.1


Micro-service Challenges:
----------------------------
1.What is boundary for a MS
2.Centralised Configuration management of MS(scale up /down)-->**Spring cloud config server**
3.centralised Monitoring 
4.Fault Tolerance

Spring cloud config server
------------------------------------------
@ConfigurationProperties - Create a POJO To map to a configuration Prefix(ie, VVIMP) which is used in app.properties or remote configuration present in GIT and use those properties in application.

@EnableConfigServer - TO enable a service as config server
http://localhost:8888/limits-service/qa
http://localhost:8888/{service}/{profile}

**Implementation KeyNote**

Create MS(MicroService) and profile specific properties in GIT(Microservice name should be same as the mS spring application name)
Commit them without fail
Add the local git repository or remote repository in spring cloud config server's app.properties
Add the spring cloud config server's in the actual MS bootstrap.properties(it will override app.properties . app.prop is for app specific params , bootstrap can have remote configs)

**NB**

At app start it pick the configs from config server
So, If git config changes ,commit AND restart app MS

Multiple MS and profiles(dev,qa,default) can be handled
{service}-{profile}.properties

Reference
--------------
cloud.spring.io

