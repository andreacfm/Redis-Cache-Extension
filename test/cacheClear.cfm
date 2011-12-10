<cfif server.ColdFusion.ProductName EQ "railo">
<cflock scope="server" timeout="1">
<cfset cacheName="mongotest">
<cfset cacheClear()>
	<cfset cachePut('abc','123')>
  	<cf_valueEquals left="#cacheCount()#" right="1">
    <cfset cacheClear()>
    <cf_valueEquals left="#cacheCount()#" right="0">
    
    <cfset cachePut('abc','123')>
    <cf_valueEquals left="#cacheCount()#" right="1">
    <cfset cacheClear("*")>
    <cf_valueEquals left="#cacheCount()#" right="0">
    
    <cfset cacheClear("",cacheName)>
    <cfset cachePut('abc','123',CreateTimeSpan(1,1,1,1),CreateTimeSpan(1,1,1,1),cacheName)>
    <cf_valueEquals left="#cacheCount(cacheName)#" right="1">
    <cfset cacheClear("",cacheName)>
    <cf_valueEquals left="#cacheCount(cacheName)#" right="0">
    
    <cfset cachePut('abc','123',CreateTimeSpan(1,1,1,1),CreateTimeSpan(1,1,1,1),cacheName)>
    <cfset cachePut('abe','456',CreateTimeSpan(1,1,1,1),CreateTimeSpan(1,1,1,1),cacheName)>
    <cfset cachePut('afg','789',CreateTimeSpan(1,1,1,1),CreateTimeSpan(1,1,1,1),cacheName)>

    <cf_valueEquals left="#cacheCount(cacheName)#" right="3">
    <cfset cacheClear("ab*",cacheName)>

    <cf_valueEquals left="#cacheCount(cacheName)#" right="1">

</cflock>

</cfif>
