<cfset cacheClear()>
<cfcache action="put" id="peter" value="Peter">
<cfcache action="put" id="susi" value="Susanna" timespan="#createTimeSpan(0,0,0,2)#">
<cfcache action="put" id="andrea" value="Andrea" timespan="#createTimeSpan(0,0,0,10)#">

<cfset sleep(3000)>

<cfcache action="get" id="peter" name="p">
<cfcache action="get" id="susi" name="s">
<cfcache action="get" id="andrea" name="a">

<cf_valueEquals left="#isDefined('p')#" right="true">
<cf_valueEquals left="#isDefined('s')#" right="false">
<cf_valueEquals left="#isDefined('a')#" right="true">

<!--- idle not supported--->
<!---
<cfset cacheClear()>
<cfcache action="put" id="peter" value="Peter">
<cfcache action="put" id="susi" value="Susanna" idletime="#createTimeSpan(0,0,0,2)#">

<cfset sleep(3000)>

<cfcache action="get" id="peter" name="p">
<cfcache action="get" id="susi" name="s">

<cf_valueEquals left="#isDefined('s')#" right="false">
<cf_valueEquals left="#isDefined('p')#" right="true">
--->


