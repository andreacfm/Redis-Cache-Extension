<cfset request.tempdir = expandPath("temp/")>

<cfcache
	action = "clientcache"   
	directory = "#request.tempdir#"   
	timespan = "#CreateTimeSpan(0,0,1,10)#">
<cfcache
	action = "clientcache"   
	directory = "#request.tempdir#"   
	timespan = "#CreateTimeSpan(0,0,1,10)#">
	
<cfset server.enableCache=true>


<cfcache action="put" id="susi" value="Susanne">
<cfcache action="put" id="peter" value="Peter">
<cf_valueEquals left="#cacheGet('susi')#" right="Susanne">

<cfcache action="put" id="susi" value="Susanne">
<cf_valueEquals left="#cacheKeyExists('susi')#" right="true">

<cfcache action="get" id="susi" name="val">
<cf_valueEquals left="#val#" right="Susanne">

<cfcache action="flush" id="susi">
<cfcache action="get" id="susi" name="s">
<cfcache action="get" id="peter" name="p">
<cf_valueEquals left="#isDefined('s')#" right="false">
<cf_valueEquals left="#isDefined('p')#" right="true">

<cfcache action="flush" id="susi,peter">
<cfcache action="get" id="susi" name="s">
<cfcache action="get" id="peter" name="p">

<cf_valueEquals left="#isDefined('s')#" right="false">
<cf_valueEquals left="#isDefined('p')#" right="false">

<cftry>
    <cfcache action="flush" id="susi,peter" throwOnError=1>
    <cf_mustThrow message="can not remove the elements with the following id(s) [SUSI,PETER]">
    <cfcatch></cfcatch>
</cftry>

<cfcache action="put" id="susi" value="Susanne">
<cfcache action="get" id="susi" name="s" metadata="smd">
<cfcache action="get" id="peter" name="p" metadata="pmd">

<cf_valueEquals left="#isDefined('s')#" right="true">

<cf_valueEquals left="#isDefined('p')#" right="false">
<cf_valueEquals left="#structCount(pmd)#" right="0">
<cf_valueEquals left="#structCount(smd) GT 0#" right="true">