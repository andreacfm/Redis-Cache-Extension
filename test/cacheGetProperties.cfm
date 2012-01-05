<cflock scope="server" timeout="1">


	<cfset cacheName="redis_objects">
	<cfset cacheRemove(arrayToList(cacheGetAllIds()))>
	
	<cfset cachePut('abc','123')>
	<cfset cacheGetProperties()>
	<cfset cacheGetProperties('template')>
	<cfset cacheGetProperties('object')>
	
	<cfif server.ColdFusion.ProductName EQ "railo"> 
		<cfset cacheGetProperties(cacheName)>
    </cfif>
</cflock>