package ycms.component.model;

import java.io.Serializable;
import java.util.Date;

import ycms.component.system.YatospaceObject;

/**
 * Модел који се односи на текстове и додатне информације о њима. 
 * @author VM
 * @version 1.0
 */
public class Text implements Serializable, Cloneable, Comparable<Text>, YatospaceObject<Text>{
	private static final long serialVersionUID = -842921023160571066L;
	private Header<Text> header; 
	private String text = ""; 
	
	public Text(String id) {
		header = new Header<>(id); 
		header.setContent(this);
	}
	
	
	@Override
	public Date created() {
		return header.created();
	}

	@Override
	public Date modified() {
		return header.modified();
	}

	@Override
	public String id() {
		return header.id();
	}

	@Override
	public Text content() {
		return header.content();
	}

	@Override
	public Header<Text> header() {
		return header;
	}
	
	@Override
	public int compareTo(Text o) {	
		return id().compareTo(o.id());
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Text) {
			Text text = (Text) o; 
			return id().contentEquals(text.id()); 
		}
		return false; 
	}
	
	@Override 
	public int hashCode() {
		return id().hashCode(); 
	}
	
	@Override
	public Text clone() {
		Text text = new Text(id());
		text.text = this.text;
		return text; 
	}
	
	@Override 
	public String toString() {
		return id();
	}
	
	public String text() {
		return text; 
	}
	
	public void setText(String text) {
		if(text==null) text = "";
		this.text = text;
	}
	
	public void adoptText(String text) {
		if(text==null) text = "";
		this.text = text;
		this.header.setModified(new Date());
	}
}
