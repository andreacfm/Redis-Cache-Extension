<cfif server.ColdFusion.ProductName EQ "railo">
<cflock scope="server" timeout="1">
	<cfset cacheName="sample">
	<cfset cacheClear()>
	<cfset cachePut('abc','123')>
    <cf_valueEquals left="#cacheCount()#" right="1">
    <cfset cacheClear()>
    <cf_valueEquals left="#cacheCount()#" right="0">
</cflock>

</cfif>