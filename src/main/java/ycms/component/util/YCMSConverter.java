package ycms.component.util;

import org.bson.Document;

import com.google.gson.Gson;

import ycms.component.io.MongoDBTextDTO;

/**
 * Конвертвоање разних објеката. 
 * @author VM
 * @version 1.0
 */
public class YCMSConverter {
	private YCMSConverter() {}
	
	public static Document convert(MongoDBTextDTO object) {
		try {
			Gson gson = new Gson();  
			String json = gson.toJson(object); 
			Document document = Document.parse(json); 
			return document; 
		}catch(Exception ex) {
			return null;
		}
	}
	
	public static MongoDBTextDTO toMongoDBTextDAO(Document document) {
		try {
			String json = document.toJson();
			MongoDBTextDTO dto = new Gson().fromJson(json, MongoDBTextDTO.class); 
			return dto; 
		}catch(Exception ex) {
			return null; 
		}
	}
}
