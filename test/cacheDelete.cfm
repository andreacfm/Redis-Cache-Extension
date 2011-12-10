<cfif server.ColdFusion.ProductName EQ "railo">
<cflock scope="server" timeout="1">
	<cfset cacheName="sample">
	<cfset cacheClear()>
	
	<cfset cachePut('abc','123')>
    <cf_valueEquals left="#cacheCount()#" right="1">
    <cfset cacheDelete('abc')>
    <cf_valueEquals left="#cacheCount()#" right="0">
    <cfset cacheDelete('feg')>
    
    <cftry>
        <cfset cacheDelete('def',true)>
        <cf_mustThrow message="there is no entry in cache with key [DEF]">
        <cfcatch></cfcatch>
    </cftry>
    
    <cfset cachePut('abc','123')>
    <cf_valueEquals left="#cacheCount()#" right="1">
    <cfset cacheDelete('abc',false)>
    <cf_valueEquals left="#cacheCount()#" right="0">
    <cfset cacheDelete('feg')>
    
</cflock>

</cfif>