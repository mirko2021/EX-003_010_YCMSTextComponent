package ycms.component.engine;

import java.util.List;

import ycms.component.general.TextCollection;
import ycms.component.model.Text;

/**
 * Општи кориснички интерфејс.
 * @author VM
 * @version 1.0
 */
public interface GeneralTextCollectionEngine extends TextCollection{
	public default boolean isJavaTextCollectionEngine() {
		return this instanceof JavaTextCollectionEngine; 
	}
	
	public default boolean isMongoTextCollectionEngine() {
		return this instanceof MongoTextCollectionEngine; 
	}
	
	public default JavaTextCollectionEngine asJavaTextCollectionEngine() {
		if(this instanceof JavaTextCollectionEngine) return (JavaTextCollectionEngine) this; 
		return null; 
	}
	
	public default MongoTextCollectionEngine asMongoTextCollectionEngine() {
		if(this instanceof MongoTextCollectionEngine) return (MongoTextCollectionEngine) this;
		return null; 
	}
	
	public List<String> keys(); 
	public Text text(String id); 
}
