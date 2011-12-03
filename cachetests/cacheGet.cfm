<cflock scope="server" timeout="1">
<cfset cacheName="sample">
	<cfset cacheRemove(arrayToList(cacheGetAllIds()))>
<cfset cachePut('abc','123')>

<cf_valueEquals left="#cacheGet('abc')#" right="123">
<cfset cacheGetKey=cacheGet('def')>
<cf_valueEquals left="#structKeyExists(variables,'cacheGetKey')#" right="#false#">

<cfif server.ColdFusion.ProductName EQ "railo">
    <cftry>
        <cfset cacheGet('def',true)>
        <cf_mustThrow message="there is no entry in cache with key [DEF]">
        <cfcatch></cfcatch>
    </cftry>

	<cf_valueEquals left="#cacheGet('abc',false)#" right="123">
</cfif>
</cflock>