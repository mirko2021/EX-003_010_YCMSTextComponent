package ycms.component.io;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import ycms.component.general.TextCollection;
import ycms.component.model.Text;
import ycms.component.util.YCMSConverter;

/**
 * Примопредаја података преко монго базе података. 
 * @author VM
 * @version 1.0
 */
public class MongoDBTextDAO implements TextCollection{
	private static final long serialVersionUID = 137350613155944695L;
	private MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
	private MongoDatabase mongoDatabase = mongoClient.getDatabase("yi_database");
	private MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("stup_text_notes");
	
	public MongoClient getMongoClient() {
		return mongoClient;
	}
	
	public MongoDatabase getMongoDatabase() {
		return mongoDatabase;
	}
	public MongoCollection<Document> getMongoCollection() {
		return mongoCollection;
	}
	
	@Override
	public void addText(String id, String text) {
		if(text==null) return; 
		if(id==null) return; 
		if(id.trim().length()==0) return; 
		Text txt = new Text(id);
		txt.setText(text);
		MongoDBTextDTO dto = new MongoDBTextDTO(); 
		dto.setText(txt); 
		Document doc = YCMSConverter.convert(dto); 
		for(Document item : mongoCollection.find(Filters.eq("text.header.id", id)))
				if(item!=null) return; 
				else return;
		mongoCollection.insertOne(doc);
	}
	
	@Override
	public void putText(String id, String text) {
		if(text==null) return; 
		if(id==null) return; 
		if(id.trim().length()==0) return;
		Text txt = new Text(id);
		txt.setText(text);
		MongoDBTextDTO dto = new MongoDBTextDTO();
		Document doc = YCMSConverter.convert(dto); 
		mongoCollection.updateOne(Filters.eq("text.header.id", id), doc); 
	}
	
	@Override
	public void removeText(String id) {
		if(id==null) return; 
		if(id.trim().length()==0) return; 
		mongoCollection.deleteOne(Filters.eq("text.header.id", id)); 
	}
	
	@Override
	public String getText(String id) {
		try {
			for(Document document: mongoCollection.find(Filters.eq("text.header.id", id)))
				return YCMSConverter.toMongoDBTextDAO(document).getText().text();
			return null;
		}catch(Exception ex) {
			return null;
		}
	}
	
	@Override
	public void clearTexts() {
		mongoCollection.drop(); 
		mongoDatabase.createCollection("stup_text_notes");
		mongoCollection = mongoDatabase.getCollection("stup_text_notes"); 
	}
	
	public List<String> keys(){
		ArrayList<String> keys = new ArrayList<>();
		for(Document document : mongoCollection.find())
			keys.add(YCMSConverter.toMongoDBTextDAO(document).getText().id()); 
		return keys;
	}
	
	public List<String> keys(String username){
		if(username==null) return new ArrayList<>(); 
		ArrayList<String> keys = new ArrayList<>();
		for(Document document : mongoCollection.find(Filters.eq("username", username)))
			keys.add(YCMSConverter.toMongoDBTextDAO(document).getText().id()); 
		return keys;
	}
	
	public Text text(String id) {
		try {
			for(Document document: mongoCollection.find(Filters.eq("text.header.id", id)))
				return YCMSConverter.toMongoDBTextDAO(document).getText();
			return null;
		}catch(Exception ex) {
			return null;
		}
	}
	
	public Text text(String id, String username) {
		try {
			for(Document document: mongoCollection.find(Filters.and(Filters.eq("text.header.id", id), Filters.eq("username", username))))
				return YCMSConverter.toMongoDBTextDAO(document).getText();
			return null;
		}catch(Exception ex) {
			return null; 
		}
	}
}