OAuth2 Resource Server Demo using Okta 

This is a demo of an OAuth2 Resource Server using Okta for authorization.   
  
I followed parts of the guide here - https://developer.okta.com/blog/2020/11/20/spring-data-jpa for implementing the resource server.  
Contrary to what the article mentions in the properties file, we do not need the clientId and clientSecret for a resource server. This is probably just common code they use for the OAuth client demo. Also, the guide uses an OidcUser as the principal in the Controller method, which will be null when using Jwt authorization.   
  
Also, this article on Spring Security OAuth is very good for understanding what is actually happening behind all the Spring auto configuration magic - https://www.marcobehler.com/guides/spring-security-oauth2  
  
