package ycms.component.util;

import java.io.Serializable;

/**
 * Општа шема за избор извора података за неку компоненту
 * за уношење и складиштење текстова. 
 * @author VM
 * @version 1.0
 */
public class DataSourceSchema implements Serializable{
	private static final long serialVersionUID = 4327880714618727664L;
	
	private DataSourceValue dataSourceValue = DataSourceValue.JAVA_BUFFER;

	public DataSourceValue getDataSourceValue() {
		return dataSourceValue;
	}
		
	public void setDataSourceValue(DataSourceValue dataSourceValue) {
		if(dataSourceValue==null) dataSourceValue = DataSourceValue.JAVA_BUFFER; 
		this.dataSourceValue = dataSourceValue;
	}
}
