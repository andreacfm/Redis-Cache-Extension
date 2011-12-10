<cfif server.ColdFusion.ProductName EQ "railo">
<cflock scope="server" timeout="1">
	<cfset cacheName="mongotest">
	<cfset cacheClear()>
	
	<cfset cachePut('abc','123')>
    <cf_valueEquals left="#cacheKeyExists('abc')#" right="true">
    <cf_valueEquals left="#cacheKeyExists('def')#" right="false">
    <cf_valueEquals left="#cacheKeyExists('def',cacheName)#" right="false">
</cflock>

</cfif>