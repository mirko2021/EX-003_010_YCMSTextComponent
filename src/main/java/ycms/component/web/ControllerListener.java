package ycms.component.web;

import java.util.HashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import ycms.component.io.MongoDBTextDAO;
import ycms.server.process.controller.ProccessController;

/**
 * Ослушкивач за сесије који се користи за формирање контролера и 
 * адаптера. 
 * @author VM
 * @version 1.0
 */
@WebListener
public class ControllerListener implements HttpSessionListener {
    private static HashMap<HttpSession, MongoDBTextDAO> textDAOMongoMap = new HashMap<>();
	private static HashMap<HttpSession, ProccessController> textPCMap = new HashMap<>();  
    
    public static synchronized HashMap<HttpSession, MongoDBTextDAO> textDAOMongoMap(){
    	return new HashMap<>(textDAOMongoMap); 
    }
    
	public static synchronized HashMap<HttpSession, ProccessController> textPCMap(){
		return new HashMap<>(textPCMap); 
	}
	
    public ControllerListener() {}

    public void sessionCreated(HttpSessionEvent se)  { 
    	synchronized(ControllerListener.class) {
    		MongoDBTextDAO textDAOMongo = new MongoDBTextDAO();
    		ProccessController textPC = new ProccessController(); 
    		textDAOMongoMap.put(se.getSession(), textDAOMongo); 
    		textPCMap.put(se.getSession(), textPC);
    	}
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	synchronized(ControllerListener.class) {
    		textDAOMongoMap.remove(se.getSession());
    		textPCMap.remove(se.getSession()); 
    	}
    }
    
    public synchronized static MongoDBTextDAO getTextDAOMongo(HttpSession session) {
    	return textDAOMongoMap.get(session); 
    }
    
    public synchronized static ProccessController getTextPC(HttpSession session) {
    	return textPCMap.get(session); 
    }
}
