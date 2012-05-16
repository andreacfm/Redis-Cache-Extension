<cfset cacheClear()>

<cfcache action="put" id="susi" value="Susanne">
<cfcache action="get" id="susi" name="s">
<cfcache action="get" id="susi" name="s" metadata="smd">

<cf_valueEquals left="#smd.hits#" right="2">