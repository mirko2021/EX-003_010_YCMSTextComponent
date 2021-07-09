package ycms.component.controller;

/**
 * Уоштење трансфера текста. 
 * @author VM
 * @version 1.0
 */
public abstract class GeneralTextTransferController {
	public abstract void differentSource(); 
	public abstract void differentDestination(); 
	public abstract void differentaSymetric(); 
	public abstract void unionData(); 
	public abstract void intersectData(); 
	public abstract void transferData(); 
	public abstract void synchronisationData(); 
	public abstract void synchronisationDataSourcePivoted(); 
	public abstract void synchronisationDataDestinationPivoted(); 
}
