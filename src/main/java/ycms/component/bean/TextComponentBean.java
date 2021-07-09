package ycms.component.bean;

import ycms.component.general.Text;

/**
 * Зрно за баратање текстовима. 
 * @author VM
 * @version 1.0
 */
public class TextComponentBean implements Text{
	private static final long serialVersionUID = -571688372545828323L;
	private String text = "";
	
	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		if(text==null) text = ""; 
		this.text = text;
	}
	
	public void resetText() {
		this.text = ""; 
	}
}
