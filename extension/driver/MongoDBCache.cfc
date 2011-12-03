<cfcomponent extends="Cache">
    <cfset fields=array(
			field(	displayName="Server Host",
					name="hosts",
					defaultValue="localhost:27017",
					required=true,
					description="One or more MongoDB hosts (one per line). Use up to 7 hosts in a Replica Sets (MongoDb cluster environment)",
					type="textarea"
				),
			field(	displayName="Database",
					name="database",
					defaultValue="",
					required=true,
					description="The name of the database on the MongoDB server to use",
					type="text"
				),

			field(	displayName="Username",
					name="username",
					defaultValue="",
					required=false,
					description="",
					type="text"
				),
			field(	displayName="Password",
					name="password",
					defaultValue="",
					required=false,
					description="",
					type="text"
				),

			field(	displayName="Collection",
					name="collection",
					defaultValue="",
					required=true,
					description="The name of the collection in the MongoDb database that will be used to store the data.",
					type="text"
				),
										
			field(	displayName="Max connection per host",
					name="connectionsPerHost",
					defaultValue="10",
					required=true,
					description="The max number of connection allowed per host. The exceeding connection will be queued.",
					type="text"
				),

			field(	displayName="Persists over server restart",
					name="persist",
					values="true,false",	
					defaultValue=true,
					required=true,
					description="",
					type="radio"
				)
		)>

	<cffunction name="getClass" returntype="string">
    	<cfreturn "railo.extension.io.cache.mongodb.MongoDBCache">
    </cffunction>
    
	<cffunction name="getLabel" returntype="string" output="no">
    	<cfreturn "MongoDBCache">
    </cffunction>

	<cffunction name="getDescription" returntype="string" output="no">
    	<cfset var c="">
    	<cfsavecontent variable="c">
		This is the MongoDB Cache implementation for Railo. This allows you to cache objects, primitives and queries in a MongoDB server that can be used as a cache. 
        </cfsavecontent>
    	<cfreturn trim(c)>
    </cffunction>

</cfcomponent>