package ycms.component.general;

/**
 * Оквир за формирање контролисане ЈСОН серијализабилности објеката. 
 * @author VM
 * @version 1.0
 */
public interface JsonSerializable {
	public void fromJson(String json);
	public String stoJson(); 
}
