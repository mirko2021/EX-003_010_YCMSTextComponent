package ycms.component.system;

import java.io.Serializable;
import java.util.Date;

import ycms.component.model.Header;

/**
 * Општи оквир за информације о објектима, у овом систему и апликацијама. 
 * @author VM
 * @version 1.0
 */
public interface YatospaceObject<T extends Serializable> {
	public Date created(); 
	public Date modified();
	public String id(); 
	public T content();
	public Header<T> header();
}
