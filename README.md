# spring-cloud
Spring Cloud 2.1


Micro-service Challenges:
----------------------------
1.What is boundary for a MS
2.Centralised Configuration management of MS(scale up /down)-->**Spring cloud config server**
3.centralised Monitoring --> Zipkin
4.Fault Tolerance --> Hystrix

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

**NB Spring cloud config server**

At app starts, it picks the configs from config server
So, If git config changes ,commit AND restart app MS

Multiple MS and profiles(dev,qa,default) can be handled
{service}-{profile}.properties

**Play with Application Properties**

Environment to get property(Spring Core)
Override app.properties by using VM args

**RestTemplate** Rest client [CurrencyConversionController](currency-conversion-service/src/main/java/com/p2/microservices/currencyconversionservice/CurrencyConversionController.java)    
**Feign**  for rest client to remove boiler code used by RestTemplate 
**@EnableFeignClients** Spring Boot App which uses Feign Client [CurrencyConversionServiceApplication](currency-conversion-service/src/main/java/com/p2/microservices/currencyconversionservice/CurrencyConversionServiceApplication.java)    
**@FeignClient** Service for Rest Call using Feign [CurrencyExchangeServiceProxy](currency-conversion-service/src/main/java/com/p2/microservices/currencyconversionservice/CurrencyExchangeServiceProxy.java)


**Ribbon** Client side load balancer    
**@RibbonClient** [CurrencyExchangeServiceProxy](currency-conversion-service/src/main/java/com/p2/microservices/currencyconversionservice/CurrencyExchangeServiceProxy.java)

**Naming Server**: For registr/discoevry of MS  
**Problem solved with NS**: hardcode the host:port in Client MS
example ,if exchange-service(rest API Server) instance added ,change the property file in conversion-sevice(rest API client)[Refer commented property](currency-conversion-service/src/main/resources/application.properties)

**Eureka NS**   
     
http://localhost:8761/  
**@EnableEurekaServer** [NetflixEurekaNamingServerApplication](netflix-eureka-naming-server/src/main/java/com/p2/microservices/netflixeurekanamingserver/NetflixEurekaNamingServerApplication.java)

**Register/Discover NS**
@EnableDiscoveryClient

**calling remote rest API, and usage of config host:ip**
----------------------------------------------------------
1.feign(in feign client)
2.ribbon(in app.prop)
3.no config using NS

**_NB_** : NS takes time to pick the changes(scale-up/down info)

**zuul** API gateway
--------------------------
1.Filter request or reject
2.edit request
3.logging


**@EnableZuulProxy** for Gateway MS[NetflixZuulApiGatewayServerApplication](netflix-zuul-api-gateway-server/src/main/java/com/p2/microservices/netflixzuulapigatewayserver/NetflixZuulApiGatewayServerApplication.java)

**URL converion using zuul**        
http://localhost:8000/currency-exchange/from/USD/to/INR     
http://{zuul-server-host}:{zuul-server-port}/{appliction-name}/{URI}        
http://localhost:8765/currency-exchange-service/currency-exchange/from/USD/to/INR

**sleuth** - to assign a Unique request for each request so that on API request can be tracked across MS

**zipkin central taacing**
Another tracing stack is ELK stack - elastic search+kibana

**RabbitQ **:
**check installation success** 
Navigate to the sbin directory of the RabbitMQ Server installation directory. In my case the path is C:\Program Files (x86)\RabbitMQ Server\rabbitmq_server-3.3.4\sbin
Run the following command to enable the plugin rabbitmq-plugins.bat enable rabbitmq_management
Then, re-install the RabbitMQ service using the commands below:
rabbitmq-service.bat stop  
rabbitmq-service.bat install  
rabbitmq-service.bat start  

http://localhost:15672/mgmt
Username: guest 
Password: guest

**ZIPkin to Rabbit connection**
(Outside codebase)
set RABBIT_URI=amqp://localhost
java -jar zipkin-server-2.7.0-exec.jar
localhost:9411/zipkin/

Example Order of servers:
Naming Server -> Zipkin service(jar) - > Currency exchange -> currency converon -> api gateway

**How to refresh and get Git configs at runtime**
**actuator/refresh** - to refresh the configs

Reference
--------------
cloud.spring.io

Queries:
Client side load balancer vs server side
https://soaessentials.com/client-side-load-balancing-vs-server-side-load-balancing-how-client-side-load-balancing-works/
12 factor app