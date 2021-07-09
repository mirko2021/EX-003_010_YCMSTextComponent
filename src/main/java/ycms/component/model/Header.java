package ycms.component.model;

import java.io.Serializable;
import java.util.Date;

import ycms.component.system.YatospaceObject;

/**
 * Реално заглавље за објекте у овом систему. 
 * @author VM
 * @version 1.0
 */
public class Header<T extends Serializable> implements Serializable, YatospaceObject<T>{
	private static final long serialVersionUID = -8793756620128858679L;
	private Date created = new Date();
	private Date modified = new Date(); 
	private String id = ""; 
	private transient T content = null;
	
	public Header(String id) {
		if(id==null)              throw new NullPointerException("Yatospace Header Id Zero.");
		if(id.trim().length()==0) throw new NullPointerException("Yatospace Header Id Blank.");
		this.id = id; 
	}
	
	@Override
	public Date created() {
		return created;
	}

	@Override
	public Date modified() {
		return modified;
	}

	@Override
	public String id() {
		return id;
	}

	@Override
	public T content() {
		return content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		if(created==null) return;
		if(modified.compareTo(created)<0) return; 
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		if(modified==null) return;
		if(modified.compareTo(created)<0) return; 
		this.modified = modified;
	}

	public String getId() {
		return id;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	@Override
	public Header<T> header() {
		return this;
	}
}
