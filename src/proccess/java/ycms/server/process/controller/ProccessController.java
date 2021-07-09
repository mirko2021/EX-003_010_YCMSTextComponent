package ycms.server.process.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ycms.server.process.model.ProccessObject;

/**
 * Општи контролер и регистар за управљиве и респосибилне процесе. 
 * @author VM
 * @version 1.0
 */
public class ProccessController {
	private HashMap<String, ProccessObject> proccessMap = new HashMap<>(); 
	
	public synchronized Map<String, ProccessObject> proccessMap(){
		return new HashMap<>(proccessMap);
	}
	
	public synchronized List<String> proccessKeys(){
		return new ArrayList<>(proccessMap.keySet());
	}
	
	public synchronized ProccessObject getProccess(String id) {
		return proccessMap.get(id);
	}
	
	public synchronized void add(String id, ProccessObject process) {
		if(id==null) return; 
		if(process==null) return; 
		if(id.trim().length()==0) return;
		if(proccessMap.containsKey(id)) return; 
		proccessMap.put(id, process); 
	}
	
	public synchronized void put(String id, ProccessObject process) {
		if(id==null) return; 
		if(process==null) return; 
		if(id.trim().length()==0) return; 
		proccessMap.put(id, process); 
	} 
	
	
	public synchronized void remove(String id) {
		proccessMap.remove(id); 
	}
}
