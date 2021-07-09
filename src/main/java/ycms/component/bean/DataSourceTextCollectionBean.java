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
import ycms.component.engine.JavaTextCollectionEngine;
import ycms.component.engine.MongoTextCollectionEngine;
import ycms.component.general.TextCollection;
import ycms.component.model.Text;
import ycms.component.util.DataSourceSchema;
import ycms.component.util.DataSourceValue;

/**
 * Зрно које се односи на промјењивост извора података. 
 * @author VM
 * @version 1.0
 */
public class DataSourceTextCollectionBean implements ycms.component.general.Text, TextCollection, AjaxExecutable{
	private static final long serialVersionUID = 2614997588658686144L;
	private DataSourceSchema dataSource = new DataSourceSchema(); 
	private MongoTextCollectionEngine mongoEngine;
	private JavaTextCollectionEngine listEngine; 
	private TextComponentBean textBean = new TextComponentBean(); 
	private String selectedId = ""; 
	private Text selectedText = null; 
	private String beanName = "";

	public DataSourceSchema getDataSource() {
		return dataSource;
	}

	public String getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(String selectedId) {
		if(selectedId==null) selectedId = ""; 
		this.selectedId = selectedId;
	}

	public Text getSelectedText() {
		return selectedText;
	}

	public void setSelectedText(Text selectedText) {
		this.selectedText = selectedText;
	}

	public String getBeanName() {
		return beanName;
	}

	public void register(HttpSession session, String beanName) {
		this.beanName = beanName; AjaxRegisterListener.getAjaxRegister(session).put(beanName, this);
		if(this.mongoEngine==null) this.mongoEngine = new MongoTextCollectionEngine();
		if(this.listEngine==null) this.listEngine = new JavaTextCollectionEngine();
	}
	
	public TextComponentBean getTextBean() {
		return textBean;
	}

	public JavaTextCollectionEngine getListEngine() {
		return listEngine;
	}
		
	public MongoTextCollectionEngine getMongoEngine() {
		return mongoEngine;
	}

	public void setMongoEngine(MongoTextCollectionEngine mongoEngine) {
		this.mongoEngine = mongoEngine;
	}

	@Override
	public void importFrom(AjaxRequestContext request) {
		
	}

	@Override
	public void exportTo(AjaxRequestContext request) {
	}

	@Override
	public void addText(String id, String text) {
		if(id==null) return;
		if(id.trim().length()==0) return; 
		if(text==null) return; 
		switch(dataSource.getDataSourceValue()) {
			case JAVA_BUFFER: 
				listEngine.addText(id, text);
				break; 
			case MONGO_DATABASE: 
				mongoEngine.addText(id, text);
				break; 
		}
	}

	@Override
	public void putText(String id, String text) {
		if(id==null) return; 
		if(id.trim().length()==0) return; 
		if(text==null) return;
		switch(dataSource.getDataSourceValue()) {
			case JAVA_BUFFER: 
				listEngine.putText(id, text);
				break; 
			case MONGO_DATABASE: 
				mongoEngine.putText(id, text);
				break;
		}
	}

	@Override
	public void removeText(String id) {
		if(id==null) return; 
		if(id.trim().length()==0) return; 
		switch(dataSource.getDataSourceValue()) {
			case JAVA_BUFFER: 
				listEngine.removeText(id);
				break; 
			case MONGO_DATABASE: 
				mongoEngine.removeText(id);
				break;
		}
	}

	@Override
	public String getText(String id) {
		try {
			if(id==null) return "";
			if(id.trim().length()==0) return ""; 
			switch(dataSource.getDataSourceValue()) { 
				case JAVA_BUFFER: 
					return listEngine.getText(id);
				case MONGO_DATABASE:
					return mongoEngine.getText(id); 
			}
			return null; 
		}catch(Exception ex) {
			return null; 
		}
	}

	@Override
	public void clearTexts() {
		switch(dataSource.getDataSourceValue()) {
			case JAVA_BUFFER: 
				listEngine.clearTexts();
				break; 
			case MONGO_DATABASE: 
				mongoEngine.clearTexts();
				break;
		}
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
	
	
	public void setSelected(String selectedId) {
		if(selectedId==null) return; 
		if(selectedId.trim().length()==0) return; 
		var text = listEngine.text(selectedId); 
		text = null;
		switch(dataSource.getDataSourceValue()) {
			case JAVA_BUFFER: 
				text = listEngine.text(selectedId);
				break; 
			case MONGO_DATABASE: 
				text = mongoEngine.text(selectedId);
				break;
		}
		if(text==null) return; 
		this.selectedId = selectedId; 
		this.selectedText = text; 
	}
	
	public void resetSelected() {
		selectedId = "";
		selectedText = null;
	}
	
	
	@AjaxAccessable("select")
	public synchronized void select(AjaxRequestContext context) {
		try {
			String textId = context.getRequest().get("parameters").getAsJsonObject().get("id").getAsString(); 
			Text text = listEngine.text(textId);
			text = null;
			switch(dataSource.getDataSourceValue()) {
				case JAVA_BUFFER: 
					text = listEngine.text(textId);
					break; 
				case MONGO_DATABASE: 
					text = mongoEngine.text(textId);
					break;
			}
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
	
	public void dataSourceJavaBuffer() {
		resetSelected();
		dataSource.setDataSourceValue(DataSourceValue.JAVA_BUFFER); 
	}
	
	public void dataSourceMongoDatabase() {
		resetSelected();
		dataSource.setDataSourceValue(DataSourceValue.MONGO_DATABASE);
	}
	
	public boolean isDataSourceJavaBuffer() {
		return dataSource.getDataSourceValue() == DataSourceValue.JAVA_BUFFER; 
	}
	
	public boolean isDataSourceMongoDatabase() {
		return dataSource.getDataSourceValue() == DataSourceValue.MONGO_DATABASE;
	}
	
	public synchronized HashMap<String, Text> getMap(){
		HashMap<String, Text> map = new HashMap<String, Text>(); 
		List<String> keys = new ArrayList<>(); 
		switch(dataSource.getDataSourceValue()) {
			case JAVA_BUFFER: 
				keys = new ArrayList<>(listEngine.keys()); 
				for(String key: keys)
					map.put(key, listEngine.text(key)); 
				break; 
			case MONGO_DATABASE: 
				keys = new ArrayList<>(mongoEngine.keys()); 
				for(String key: keys)
					map.put(key, mongoEngine.text(key)); 
				break;
		}
		return map;
	}
	
	public synchronized List<String> getList(){
		List<String> keys = new ArrayList<>(); 
		switch(dataSource.getDataSourceValue()) {
			case JAVA_BUFFER: 	
				keys = listEngine.keys(); 
				break;
			case MONGO_DATABASE: 
				keys = mongoEngine.keys();
				break;
		}
		Collections.sort(keys);
		return keys;
	}
	
	public Text myText(String id) {
		switch(dataSource.getDataSourceValue()) {
			case JAVA_BUFFER: 	
				return listEngine.text(id);
			case MONGO_DATABASE: 
				return mongoEngine.text(id);
		}
		return null;
	}
	
	public boolean isSelected() {
		return selectedText!=null && selectedId!=null && selectedId.trim().length()!=0; 
	}
	
	public void select() {
		switch(dataSource.getDataSourceValue()) {
			case JAVA_BUFFER: 	
				this.selectedId = listEngine.generateId(); 
				this.selectedText = new Text(selectedId);
				this.selectedText.setText(textBean.getText());
			case MONGO_DATABASE: 
				this.selectedId = mongoEngine.generateId(); 
				this.selectedText = new Text(selectedId);
				this.selectedText.setText(textBean.getText());
		}
	}
	
	public void putText() {	
		if(!isSelected()) return;
		this.putText(selectedId, selectedText.text());
	}
	
	public void removeText() {
		if(!isSelected()) return; 
		this.removeText(selectedId);
	}
}
