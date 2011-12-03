package railo.extension.util;

import java.lang.reflect.Method;

import railo.loader.engine.CFMLEngine;
import railo.loader.engine.CFMLEngineFactory;
import railo.runtime.PageContext;
import railo.runtime.exp.PageException;
import railo.runtime.util.Cast;

public class Functions {

	private static final String SERIALIZE_JSON_CLASS="railo.runtime.functions.conversion.SerializeJSON";
	private static final String SERIALIZE_CLASS="railo.runtime.functions.dynamicEvaluation.Serialize";
	private static final String EVALUATE_CLASS="railo.runtime.functions.dynamicEvaluation.Evaluate";
	private static final String DESERIALIZE_JSON_CLASS="railo.runtime.functions.conversion.DeserializeJSON";
	
	private Method serializeJSON;
	private Method deserializeJSON;
	private Method serialize;
	private Method evaluate;
	
	private CFMLEngine engine;
	

	public String serializeJSON(Object var,boolean serializeQueryByColumns) throws PageException{
		return serializeJSON(pc(), var, serializeQueryByColumns);
	}
	
	public String serializeJSON(PageContext pc, Object var,boolean serializeQueryByColumns) throws PageException{
		Cast caster = engine.getCastUtil();
		try {
			if(serializeJSON==null){
				// Need ClassLoader from core
				ClassLoader cl = pc.getClass().getClassLoader();
			
				// load method
				Class clazz = cl.loadClass(SERIALIZE_JSON_CLASS);
				serializeJSON=clazz.getMethod("call", new Class[]{PageContext.class, Object.class, boolean.class});
			}
			return caster.toString(serializeJSON.invoke(pc, new Object[]{pc,var,caster.toBoolean(serializeQueryByColumns)}));
		}
		catch(Throwable t){
			throw caster.toPageException(t);
		}
	}
	
	
	public String serialize(Object var) throws PageException{
		return serialize(pc(), var);
	}
	
	public String serialize(PageContext pc, Object var) throws PageException{
		Cast caster = engine.getCastUtil();
		try {
			if(serialize==null){
				// Need ClassLoader from core
				ClassLoader cl = pc.getClass().getClassLoader();
			
				// load method
				Class clazz = cl.loadClass(SERIALIZE_CLASS);
				serialize=clazz.getMethod("call", new Class[]{PageContext.class,Object.class});
			}
			return caster.toString(serialize.invoke(null, new Object[]{pc,var}));
		}
		catch(Throwable t){
			throw caster.toPageException(t);
		}
	}
	
	
	public Object deserializeJSON(String obj) throws PageException{
		return deserializeJSON(pc(), obj);
	}
	
	public Object deserializeJSON(PageContext pc, String obj) throws PageException{
		Cast caster = engine.getCastUtil();
		try {
			if(deserializeJSON==null){
				// Need ClassLoader from core
				ClassLoader cl = pc.getClass().getClassLoader();
			
				// load method
				Class clazz = cl.loadClass(DESERIALIZE_JSON_CLASS);
				deserializeJSON=clazz.getMethod("call", new Class[]{PageContext.class, String.class});
			}
			Object obj2 = deserializeJSON.invoke(pc, new Object[]{pc,obj});
			return obj2;
		}
		catch(Throwable t){
			throw caster.toPageException(t);
		}
		
		//call(PageContext pc, String JSONVar)
	}
	private PageContext pc() {
		return engine().getThreadPageContext();
	}
	private CFMLEngine engine() {
		if(engine==null)
			engine=CFMLEngineFactory.getInstance();
		return engine;
	}

	public Object evaluate(Object obj) throws PageException{
		return evaluate(pc(), obj);
	}
	
	public Object evaluate(PageContext pc, Object obj) throws PageException{
		Cast caster = engine.getCastUtil();
		try {
			
			if(evaluate==null){
				// Need ClassLoader from core
				ClassLoader cl = pc.getClass().getClassLoader();
			
				// load method
				Class clazz = cl.loadClass(EVALUATE_CLASS);
				evaluate=clazz.getMethod("call", new Class[]{PageContext.class, Object[].class});
			}
			Object obj2 = evaluate.invoke( pc, new Object[]{ pc,new Object[]{obj}});
			return  obj2;
		}
		catch(Throwable t){
			throw caster.toPageException(t);
		}
		
	}
}
