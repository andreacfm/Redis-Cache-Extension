<cfcomponent output="false">
<cfscript>
	this.name = "";
	this.age = "";
	
	
	function setName(name){
		this.name = name;
	}
	
	function setAge(age){
		this.age = age;
	}
	
	function getName(){return this.name;};
	function getAge(){return this.age;};
</cfscript>
</cfcomponent>