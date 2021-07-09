package ycms.server.process.model;

import java.util.Date;
import java.util.Timer;

public interface ProccessObject {
	public Timer timer(); 
	public Date startedIn(); 
	public Date endedIn(); 
	public boolean paused(); 
	public void ended();
	public void pause(); 
	public void unpause();
	public void execute();
	public ProccessModel model(); 
	public ProccessEnviroment enviroment();
	public void run(); 
	public void start(); 
}
