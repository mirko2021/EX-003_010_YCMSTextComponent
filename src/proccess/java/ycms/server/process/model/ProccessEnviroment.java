package ycms.server.process.model;

/**
 * Окружење које се односи на процесе. 
 * @author VM
 * @version 1.0
 */
public interface ProccessEnviroment {
	public Thread thread(); 
	public Runnable task(); 
	public Object getObject(String id); 
	public Object getEnviroment(String id);
}
