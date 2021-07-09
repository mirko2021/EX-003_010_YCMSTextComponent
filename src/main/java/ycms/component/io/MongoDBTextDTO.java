package ycms.component.io;

import java.io.Serializable;
import ycms.component.model.Text;

/**
 * Објекат који се користи за потребе уписа при чувању текстова 
 * у бази података. 
 * @author VM
 * @version 1.0
 */
public class MongoDBTextDTO implements Serializable{
	private static final long serialVersionUID = -1510029559466909681L;
	private Text text; 
	private String username = "";
	
	public Text getText() {
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		if(username==null) username = ""; 
		this.username = username;
	}
}
