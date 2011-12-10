<cfsetting requesttimeout="300">

<cfset total = 0>

<cfloop from="1" to="10" index="j">
	<cfset start = gettickcount()>
	<cfloop from="1" to="5000" index="i">
		<cfcache action="put" id="a#i#" value="#i#">
		<cfcache action="get" id="a#i#" name="v">
	</cfloop>
	<cfset end = gettickcount()>

	<cfset time = end -start>
	<cfset total = total + time >
	<cfoutput>#time/1000# s<br/></cfoutput>
	<cfflush>
</cfloop>

<cfoutput>Average sec: #(total/10)/1000#</cfoutput>
<cfoutput>Average millis per operation : #100000/time#</cfoutput>
