package ycms.server.process.model;

import java.util.Collection;

/**
 * Општи облик за моделе процеса.
 * @author VM
 * @version 1.0
 */
public interface ProccessModel {
	public Collection<ProccessVariant> variants(); 
	public ProccessDescriptor descriptor(); 
}
