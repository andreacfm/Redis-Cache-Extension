<cflock scope="server" timeout="1">
<cfif server.ColdFusion.ProductName EQ "railo">

	<cfset cacheClear()>
	
	<cfset cachePut('abc','123')>
	<cfset cachePut('def','123')>
    <cf_valueEquals left="#ListSort(StructKeyList(cacheGetAll()),'textnocase')#" right="abc,def" cs=true>
    
	<cfset cachePut('abc','123')>
	<cfset cachePut('abd','123')>
	<cfset cachePut('def','123')>
    <cf_valueEquals left="#ListSort(StructKeyList(cacheGetAll("ab*")),'textnocase')#" right="abc,abd" cs=true>
    <cf_valueEquals left="#ListSort(StructKeyList(cacheGetAll("ab*")),'textnocase')#" right="abc,abd" cs=true>
</cfif>
</cflock>