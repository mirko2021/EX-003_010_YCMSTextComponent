package ycms.component.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ycms.component.engine.GeneralTextCollectionEngine;
import ycms.component.model.Text;

/**
 * Контролер за улаз процеса трансфера 
 * података о текстовима са разних извора података. 
 * @author VM
 * @version 1.0
 */
public class BasicTextTransferController extends GeneralTextTransferController{
	private GeneralTextCollectionEngine source; 
	private GeneralTextCollectionEngine destination; 
	
	public GeneralTextCollectionEngine getSource() {
		return source;
	}
	public void setSource(GeneralTextCollectionEngine source) {
		this.source = source;
	}
	public GeneralTextCollectionEngine getDestination() {
		return destination;
	}
	public void setDestination(GeneralTextCollectionEngine destination) {
		this.destination = destination;
	}
	
	
	@Override
	public void differentSource() {
		if(source==null) return;
		if(destination==null) return; 
		if(source==destination) return;
		
		List<String> sourceKeys = source.keys(); 
		List<String> destinationKeys = destination.keys();
		List<String> resultKeys = new ArrayList<>();
		
		for(String key: sourceKeys)
			if(!destinationKeys.contains(key))
				resultKeys.add(key); 
		
		destination.clearTexts(); 
		for(String key: resultKeys)
			destination.addText(key, source.getText(key));
	}
	@Override
	public void differentDestination() {
		if(source==null) return;
		if(destination==null) return; 
		if(source==destination) return;
		
		List<String> sourceKeys = source.keys(); 
		List<String> destinationKeys = destination.keys();
		List<String> resultKeys = new ArrayList<>();
		
		HashMap<String, Text> resultMap = new HashMap<>(); 
		
		for(String key: destinationKeys)
			if(!sourceKeys.contains(key)) {
				resultKeys.add(key); 
				resultMap.put(key, destination.text(key)); 
			}
		
		destination.clearTexts(); 
		for(String key: resultKeys)
			destination.addText(key, resultMap.get(key).text());
	}
	@Override
	public void differentaSymetric() {
		if(source==null) return;
		if(destination==null) return; 
		if(source==destination) return;
		
		List<String> sourceKeys = source.keys(); 
		List<String> destinationKeys = destination.keys();
		List<String> resultKeys = new ArrayList<>();
		
		HashMap<String, Text> resultMap = new HashMap<>(); 
		
		for(String sourceKey: sourceKeys) {
			if(!destinationKeys.contains(sourceKey)) {
				resultKeys.add(sourceKey); 
				resultMap.put(sourceKey, source.text(sourceKey)); 
			}
		}
		
		for(String destinationKey: destinationKeys) {
			if(!destinationKeys.contains(destinationKey)) {
				resultKeys.add(destinationKey); 
				resultMap.put(destinationKey, source.text(destinationKey)); 
			}
		}
		
		destination.clearTexts(); 
		for(String key: resultKeys)
			destination.addText(key, resultMap.get(key).text());
	}
	@Override
	public void unionData() {
		if(source==null) return;
		if(destination==null) return; 
		if(source==destination) return;
		
		List<String> sourceKeys = source.keys(); 
		List<String> destinationKeys = destination.keys();
		List<String> resultKeys = new ArrayList<>();
		
		HashMap<String, Text> resultMap = new HashMap<>(); 
		
		for(String destinationKey:  destinationKeys) {
			resultKeys.add(destinationKey);
			resultMap.put(destinationKey, destination.text(destinationKey));
		}
		
		for(String sourceKey: sourceKeys) {
			if(!destinationKeys.contains(sourceKey)) {
				resultKeys.add(sourceKey); 
				resultMap.put(sourceKey, source.text(sourceKey)); 
			}
		}
		
		destination.clearTexts(); 
		for(String key: resultKeys)
			destination.addText(key, resultMap.get(key).text());
	}
	@Override
	public void intersectData(){
		if(source==null) return;
		if(destination==null) return;
		if(source==destination) return;
		
		List<String> sourceKeys = source.keys(); 
		List<String> destinationKeys = destination.keys();
		List<String> resultKeys = new ArrayList<>();
		
		HashMap<String, Text> resultMap = new HashMap<String, Text>();
		
		for(String destinationKey: destinationKeys) {
			if(sourceKeys.contains(destinationKey)) {
				resultKeys.add(destinationKey); 
				resultMap.put(destinationKey, destination.text(destinationKey)); 
			}
		}
		
		destination.clearTexts(); 
		for(String key: resultKeys)
			destination.addText(key, resultMap.get(key).text());
	}
	@Override
	public void transferData() { 
		if(source==null) return;
		if(destination==null) return; 
		if(source==destination) return;
		
		List<String> sourceKeys = source.keys(); 
	
		destination.clearTexts();
		
		for(String key: sourceKeys) 
			destination.addText(key, source.text(key).text());
	}
	@Override
	public void synchronisationData() {
		if(source==null) return;
		if(destination==null) return; 
		if(source==destination) return;
		
		List<String> sourceKeys = source.keys(); 
		List<String> destinationKeys = destination.keys();
		List<String> sourceResultKeys = new ArrayList<>();
		List<String> destinationResultKeys = new ArrayList<>(); 
		
		HashMap<String, Text> sourceResultMap       = new HashMap<String, Text>();
		HashMap<String, Text> destinationResultMap  = new HashMap<String, Text>(); 
		
		for(String sourceKey: sourceKeys) {
			sourceResultKeys.add(sourceKey); 
			sourceResultMap.put(sourceKey, source.text(sourceKey));
		}
		
		for(String destinationKey: destinationKeys) {
			if(!sourceKeys.contains(destinationKey)) {
				sourceResultKeys.add(destinationKey); 
				sourceResultMap.put(destinationKey, destination.text(destinationKey));
			}
		}

		for(String destinationKey: destinationKeys) {
			destinationResultKeys.add(destinationKey); 
			destinationResultMap.put(destinationKey, destination.text(destinationKey));
		}
		
		for(String sourceKey: sourceKeys) {
			if(!destinationKeys.contains(sourceKey)) {
				destinationResultKeys.add(sourceKey); 
				destinationResultMap.put(sourceKey, source.text(sourceKey));
			}
		}
		
		source.clearTexts(); 
		destination.clearTexts(); 
		
		for(String key: destinationResultKeys)
			destination.addText(key, destinationResultMap.get(key).text());
		
		for(String key: sourceResultKeys)
			source.addText(key, sourceResultMap.get(key).text());

	}
	@Override
	public void synchronisationDataSourcePivoted() {
		if(source==null) return;
		if(destination==null) return; 
		if(source==destination) return;
		
		List<String> sourceKeys = source.keys(); 
		List<String> destinationKeys = destination.keys();
		List<String> sourceResultKeys = new ArrayList<>();
		List<String> destinationResultKeys = new ArrayList<>(); 
		
		HashMap<String, Text> sourceResultMap       = new HashMap<String, Text>();
		HashMap<String, Text> destinationResultMap  = new HashMap<String, Text>(); 
		
		for(String sourceKey: sourceKeys) {
			sourceResultKeys.add(sourceKey); 
			sourceResultMap.put(sourceKey, source.text(sourceKey));
		}
		
		for(String destinationKey: destinationKeys) {
			if(!sourceKeys.contains(destinationKey)) {
				sourceResultKeys.add(destinationKey); 
				sourceResultMap.put(destinationKey, destination.text(destinationKey));
			}
		}

		for(String destinationKey: destinationKeys) {
			destinationResultKeys.add(destinationKey); 
			if(sourceKeys.contains(destinationKey))  sourceResultMap.put(destinationKey, destination.text(destinationKey));
			else destinationResultMap.put(destinationKey, destination.text(destinationKey));
		}
		
		for(String sourceKey: sourceKeys) {
			if(!destinationKeys.contains(sourceKey)) {
				destinationResultKeys.add(sourceKey); 
				destinationResultMap.put(sourceKey, source.text(sourceKey));
			}
		}
		
		source.clearTexts(); 
		destination.clearTexts(); 
		
		for(String key: destinationResultKeys)
			destination.addText(key, destinationResultMap.get(key).text());
		
		for(String key: sourceResultKeys)
			source.addText(key, sourceResultMap.get(key).text());
	}
	@Override
	public void synchronisationDataDestinationPivoted() {
		if(source==null) return;
		if(destination==null) return; 
		if(source==destination) return;
		
		List<String> sourceKeys = source.keys(); 
		List<String> destinationKeys = destination.keys();
		List<String> sourceResultKeys = new ArrayList<>();
		List<String> destinationResultKeys = new ArrayList<>(); 
		
		HashMap<String, Text> sourceResultMap       = new HashMap<String, Text>();
		HashMap<String, Text> destinationResultMap  = new HashMap<String, Text>(); 
		
		for(String sourceKey: sourceKeys) {
			sourceResultKeys.add(sourceKey); 
			if(destinationKeys.contains(sourceKey)) sourceResultMap.put(sourceKey, destination.text(sourceKey));
			else sourceResultMap.put(sourceKey, source.text(sourceKey));
		}
		
		for(String destinationKey: destinationKeys) {
			if(!sourceKeys.contains(destinationKey)) {
				sourceResultKeys.add(destinationKey); 
				sourceResultMap.put(destinationKey, destination.text(destinationKey));
			}
		}

		for(String destinationKey: destinationKeys) {
			destinationResultKeys.add(destinationKey); 
			destinationResultMap.put(destinationKey, destination.text(destinationKey));
		}
		
		for(String sourceKey: sourceKeys) {
			if(!destinationKeys.contains(sourceKey)) {
				destinationResultKeys.add(sourceKey); 
				destinationResultMap.put(sourceKey, source.text(sourceKey));
			}
		}
		
		source.clearTexts(); 
		destination.clearTexts(); 
		
		for(String key: destinationResultKeys)
			destination.addText(key, destinationResultMap.get(key).text());
		
		for(String key: sourceResultKeys)
			source.addText(key, sourceResultMap.get(key).text());
	}
}
