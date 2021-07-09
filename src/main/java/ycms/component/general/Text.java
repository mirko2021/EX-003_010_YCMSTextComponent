package ycms.component.general;

import java.io.Serializable;

/**
 * Општи оквир за текстуална зрна. 
 * @author VM
 * @version 1.0
 */
public interface Text extends Serializable{
	public String getText();
	public void resetText();
	public void setText(String text);
}
