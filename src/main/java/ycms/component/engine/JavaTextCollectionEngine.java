package ycms.component.engine;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ycms.component.general.TextCollection;
import ycms.component.model.Text;

/**
 * Средство за складиштење и баратање идентификованим текстовима. 
 * Нема могућност трајног памћења нити бекаповања. (JTCE)
 * @author VM
 * @version 1.0
 */
public class JavaTextCollectionEngine implements TextCollection, GeneralTextCollectionEngine{
	private static final long serialVersionUID = -7776900705868901915L;
	private HashMap<String, Text> textMap = new HashMap<>(); 
	
	public synchronized String generateId() {
		String id = ""; 
		int n = 0; 
		do {
			Date date = new Date(); 
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss");
			sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			try {
				id = sdf.format(date)+" "+String.format("%02d", ++n); 
			}catch(Exception ex){
				throw new RuntimeException(ex);
			}
		}while(n<10 && textMap.keySet().contains(id));
		return id; 
	}
	
	@Override
	public synchronized void addText(String id, String text) {
		if(id==null) return; 
		if(text==null) return; 
		if(id.trim().length()==0) return; 
		if(textMap.containsKey(id)) return; 
		Text txt = new Text(generateId()); 
		txt.setText(text);
		textMap.put(id, txt);
	}

	@Override
	public synchronized void putText(String id, String text) {
		if(id==null) return; 
		if(text==null) return; 
		if(id.trim().length()==0) return;  
		Text txt = text(id);
		if(txt==null) {txt = new Text(id);}
		txt.setText(text);
		if(txt!=null) txt.adoptText(text);
		textMap.put(id, txt);
	}

	@Override
	public synchronized void removeText(String id) {
		if(id==null) return; 
		textMap.remove(id); 
	}

	@Override
	public synchronized void clearTexts() {
		textMap.clear();
	}

	@Override
	public synchronized String getText(String id) {
		try {
			if(id==null) return null;
			return textMap.get(id).text(); 
		}catch(Exception ex) {
			ex.printStackTrace(System.out);
			return ""; 
		}
	}
	
	public Text text(String id) {
		if(id==null) return null;
		if(textMap.get(id)==null) return null;
		return textMap.get(id).clone();
	}
	
	public List<String> keys(){
		ArrayList<String> textIds = new ArrayList<>();
		synchronized(this) { textIds = new ArrayList<>(textMap.keySet());}
		Collections.sort(textIds);
		return textIds; 
	}
}
