<cfcomponent extends="Cache">
    <cfset fields = array(
        field(displayName = "Server Host",
            name = "hosts",
            defaultValue = "localhost:6379",
            required = true,
            description = "Redis host and port",
            type = "textarea"
            ),
        field(
            displayName = "Namespace",
            name = "namespace",
            defaultValue = getapplicationsettings().name,
            required = true,
            description = "Keys namespace. Be sure that any cache use a unique namespace to avoid keys names clashing."
               )
            )>

    <cffunction name="getClass" returntype="string">
        <cfreturn "railo.extension.io.cache.redis.RedisCache">
    </cffunction>

    <cffunction name="getLabel" returntype="string" output="no">
        <cfreturn "RedisCache">
    </cffunction>

    <cffunction name="getDescription" returntype="string" output="no">
        <cfset var c = "">
        <cfsavecontent variable="c">
            This is the Redis Cache implementation for Railo. This allows you to cache objects, primitives and queries in a Redis server that can be used as a cache.
        </cfsavecontent>
        <cfreturn trim(c)>
    </cffunction>

</cfcomponent>