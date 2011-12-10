<cfset server.enableCache=true>

<cflock scope="server" timeout="10">
	<cfset cacheName="mongotest">
	<cfset cacheRemove(arrayToList(cacheGetAllIds()))>
    
	<cfset cachePut('abc','123')>
	<cfset cachePut('def','123')>
	<cfset cachePut('ghi','123')>
    <cfset cacheRemove('abc,def')>
    <cf_valueEquals left="#arrayToList(cachegetAllIds())#" right="GHI">
    
    
	<cfset cachePut('abc','123')>
	<cfset cachePut('def','123')>
	<cfset cachePut('ghi','123')>
    <cfset cacheRemove(' abc , def ,fff')>
    <cf_valueEquals left="#arrayToList(cachegetAllIds())#" right="GHI">
    
    <cftry>
        <cfset cacheRemove(' abc , def ,fff',true)>
        <cf_mustThrow message="can not remove the elements with the following id(s) [ABC,DEF,FFF]">
        <cfcatch></cfcatch>
    </cftry>

    
    
<cfif server.ColdFusion.ProductName EQ "railo">    
	<cfset cachePut('def','123',CreateTimeSpan(0,0,0,2),CreateTimeSpan(0,0,0,1),cacheName)>
</cfif>
</cflock>

