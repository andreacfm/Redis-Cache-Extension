##Railo Redis Cache Extension

The redis driver is based on Jedis. While this is a very robust driver and Redis is amaxing this project has to be considered in Beta stage.
Feel free to fork it and provide your feedbacks.

###Installation

Add the following address to your Railo Server Admin Providers list.

    http://preview.getrailo.org/ExtensionProvider.cfc

Install the extension from the Applications section. Please note that the extension is installable only in the *server* admin.
This means that is not possible to install it for a single web context.

###Create and configure the cache

Create a new cache selecting Redis Cache as Type.

Add some configuration:

* If you like you can use the driver to store the Session Scope. If this is your intention you can flag "Allow to use this cache as client/session storage."
* Server/Host => says to the Railo how to connect to Redis. By default this is setted to localhost:6379.
Please tune this following your environments needs. Note that the driver actually support a single Redis Server.
* Namesapce => choose the namespace that will be used to avoid keys name clashing between differents cache instances.

Well. You are done.

###Important

* Metadata:
    * The cache will return only the hits count for any single key.
    * The general counter (missed, hits) for the cache instance itself are not updated

* idletime:
  Not supported. Any passed value will ne ignored. Timespan is fully supported.




