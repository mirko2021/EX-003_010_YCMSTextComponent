package ycms.component.general;

import java.io.Serializable;

/**
 * Баратање колекцијама зрна.
 * @author VM
 * @version 1.0
 */
public interface TextCollection extends Serializable{
	public void addText(String id, String text);
	public void putText(String id, String text); 
	public void removeText(String id);
	public String getText(String id); 
	public void clearTexts(); 
}
