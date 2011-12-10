<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Cache Tests</title>
</head>
<body>

<cfdirectory action="list" directory="#expandPath(".")#" filter="*.cfm" name="cachetests">
	
<cfloop query="cachetests">
<cfif name NEQ "index.cfm">
	<cfoutput>	Running #name#<cfflush interval="10"><br></cfoutput>
	<cfinclude template="#name#">
</cfif>
	<cfflush>
</cfloop>	

</body>
</html>
