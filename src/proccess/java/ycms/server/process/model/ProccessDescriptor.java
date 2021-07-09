package ycms.server.process.model;

import com.sun.jdi.Method;

/**
 * Општи облик описа процеса. 
 * @author VM
 * @version 1.0 
 */
public interface ProccessDescriptor {
	public String description();
	public Method method(); 
	public Class<?> controllerType(); 
	public Object controller();
	public String id(); 
}
