<cfsilent>
	<cfparam name="attributes.label" default="">
	<cfparam name="attributes.cs" default="#false#" type="boolean">
</cfsilent><cfif isDefined("attributes.right")><cfif (not attributes.cs and attributes.left NEQ attributes.right) or (attributes.cs and Compare(attributes.left,attributes.right) NEQ 0)>
    	<cfif server.ColdFusion.ProductName EQ "railo">
        	<cfset context=GetCurrentContext()>
			<cfset test=context[2]>
        <cfelse>
        	<cfset test=structNew()>
		</cfif>
		<cfset test.value.left=attributes.left>
		<cfset test.value.right=attributes.right>
		<cfset test.type.left=attributes.left.getClass().getName()>
		<cfset test.type.right=attributes.right.getClass().getName()>
        
        
		<cfdump var="#test#" label="#attributes.label#">
	</cfif><cfelse><cfif not FindNoCase(attributes.startWith ,attributes.left)>
    	<cfif server.ColdFusion.ProductName EQ "railo">
			<cfset context=GetCurrentContext()>
			<cfset test=context[2]>
        <cfelse>
        	<cfset test=structNew()>
		</cfif>
		<cfset test.startWith=attributes.startWith>
		<cfset test.left=attributes.left>
		<cfset test.startWith_type=attributes.startWith.getClass().getName()>
		<cfset test.left_type=attributes.left.getClass().getName()>
		<cfdump var="#test#" label="#attributes.label#">
	</cfif></cfif>