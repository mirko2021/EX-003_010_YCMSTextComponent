package ycms.component.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import yatospace.web.ajax.anotation.AjaxAccessable;
import yatospace.web.ajax.element.AjaxRequestContext;
import yatospace.web.ajax.listener.AjaxRegisterListener;
import yatospace.web.ajax.model.AjaxExecutable;
import ycms.component.engine.MongoTextCollectionEngine;
import ycms.component.general.TextCollection;
import ycms.component.model.Text;

/**
 * Монго база података.
 * @author VM
 * @version 1.0
 */
public class MongoComponentBean implements ycms.component.general.Text, TextCollection, AjaxExecutable{
	private static final long serialVersionUID = 804837817598434050L;
	private TextComponentBean textBean = new TextComponentBean(); 
	private MongoTextCollectionEngine listEngine = new MongoTextCollectionEngine(); 
	private String selectedId = ""; 
	private Text selectedText = null; 
	private String beanName = "";
	
	public String getBeanName() {
		return beanName;
	}

	public void register(HttpSession session, String beanName) {
		this.beanName = beanName; AjaxRegisterListener.getAjaxRegister(session).put(beanName, this);
	}
	
	public TextComponentBean getTextBean() {
		return textBean;
	}

	public MongoTextCollectionEngine getListEngine() {
		return listEngine;
	}

	@Override
	public void addText(String id, String text) {
		if(id==null) return;
		if(id.trim().length()==0) return; 
		if(text==null) return; 
		listEngine.addText(id, text);
	}

	@Override
	public void putText(String id, String text) {
		if(id==null) return; 
		if(id.trim().length()==0) return; 
		if(text==null) return; 
		listEngine.putText(id, text);
	}
	
	@Override
	public void removeText(String id) {
		if(id==null) return; 
		if(id.trim().length()==0) return; 
		listEngine.removeText(id);
	}

	@Override
	public String getText(String id) {
		try {
			if(id==null) return "";
			if(id.trim().length()==0) return ""; 
			return listEngine.getText(id);
		}catch(Exception ex) {
			return null; 
		}
	}

	@Override
	public void clearTexts() {
		listEngine.clearTexts();
	}

	@Override
	public String getText() {
		return textBean.getText();
	}

	@Override
	public void resetText() {
		textBean.resetText();
	}

	@Override
	public void setText(String text) {
		textBean.setText(text);
	}

	public String getSelectedId() {
		return selectedId;
	}

	public Text getSelectedText() {
		return selectedText;
	}
	
	public void setSelected(String selectedId) {
		if(selectedId==null) return; 
		if(selectedId.trim().length()==0) return; 
		var text = listEngine.text(selectedId); 
		if(text==null) return; 
		this.selectedId = selectedId; 
		this.selectedText = text; 
	}
	
	public void resetSelected() {
		selectedId = "";
		selectedText = null;
	}
	
	public void putText() {	
		if(!isSelected()) return;
		this.putText(selectedId, selectedText.text());
	}
	
	public void removeText() {
		if(!isSelected()) return; 
		this.removeText(selectedId);
	}
	
	public boolean isSelected() {
		return selectedId!=null && selectedId.trim().length()!=0 && selectedText!=null;
	}
	
	public synchronized void select() {
		this.selectedId = listEngine.generateId();
		this.selectedText = new Text(selectedId);
		this.selectedText.setText(textBean.getText());
	}
	
	@AjaxAccessable("select")
	public synchronized void select(AjaxRequestContext context) {
		try {
			String textId = context.getRequest().get("parameters").getAsJsonObject().get("id").getAsString(); 
			Text text = listEngine.text(textId);
			if(text==null) textId = ""; 
			selectedId = textId;
			selectedText = text; 
			context.getResponse().addProperty("success", "true");
			context.getResponse().addProperty("message", "");
			context.getResponse().addProperty("id", selectedId);
			if(text==null) context.getResponse().addProperty("text", "");
			else context.getResponse().addProperty("text", selectedText.text());
		}catch(Exception ex) {
			context.getResponse().addProperty("success", "false");
			context.getResponse().addProperty("message", "");
		}
	}
	
	public synchronized HashMap<String, Text> getMap(){
		HashMap<String, Text> map = new HashMap<String, Text>(); 
		List<String> keys = new ArrayList<>(listEngine.keys()); 
		for(String key: keys)
			map.put(key, listEngine.text(key)); 
		return map;
	}
	
	public synchronized List<String> getList(){
		List<String> keys = listEngine.keys(); 
		Collections.sort(keys);
		return keys;
	}
	
	public Text myText(String id) {
		return listEngine.text(id);
	}

	@Override public void importFrom(AjaxRequestContext request) {}
	@Override public void exportTo(AjaxRequestContext request)   {}
}
