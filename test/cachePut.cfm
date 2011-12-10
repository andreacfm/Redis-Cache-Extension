<cfset server.enableCache=true>
<cfset cacheClear()>

<cflock scope="server" timeout="10">
	<cfset cacheName="mongotest">
	<cfset cacheRemove(arrayToList(cacheGetAllIds()))>
	<cfset start = getTickCount()>
	<cfset cachePut('abc','_cachePut',CreateTimeSpan(0,0,0,1))>
	<cfset cachePut('def','_cachePut',CreateTimeSpan(0,0,0,2),CreateTimeSpan(0,0,0,1))>
	<cfset cachePut('ghi','_cachePut',CreateTimeSpan(0,0,0,0),CreateTimeSpan(0,0,0,0))>
    
    <cfset a=cacheGet('abc')>
	<cfset b=cacheGet('def')>
    <cfset c=cacheGet('ghi')>    

    <cf_valueEquals left="#structKeyExists(variables,'a')#" right="true">
    <cf_valueEquals left="#structKeyExists(variables,'b')#" right="true">
    <cf_valueEquals left="#structKeyExists(variables,'c')#" right="true">
	
	

    <cfset sleep(2000)>
    <cfset d=cacheGet('abc')>
    <cfset e=cacheGet('def')>
    <cfset f=cacheGet('ghi')>
    <cf_valueEquals left="#structKeyExists(variables,'d')#" right="false">
    <cf_valueEquals left="#structKeyExists(variables,'e')#" right="false">
    <cf_valueEquals left="#structKeyExists(variables,'f')#" right="true">
    
<cfif server.ColdFusion.ProductName EQ "railo">    
	<cfset cachePut('def','123',CreateTimeSpan(0,0,0,2),CreateTimeSpan(0,0,0,1),cacheName)>
</cfif>
</cflock>

