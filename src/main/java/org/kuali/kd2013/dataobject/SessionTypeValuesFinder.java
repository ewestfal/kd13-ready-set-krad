package org.kuali.kd2013.dataobject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

public class SessionTypeValuesFinder extends KeyValuesBase {
	private static final long serialVersionUID = 1L;

	static List<KeyValue> VALUES = new ArrayList<KeyValue>(5);
	static {
		VALUES.add( new ConcreteKeyValue("", "") );
		VALUES.add( new ConcreteKeyValue("TECH", "Technical") );
		VALUES.add( new ConcreteKeyValue("FUNC", "Functional") );
		VALUES.add( new ConcreteKeyValue("PRES", "Presentational") );
		VALUES.add( new ConcreteKeyValue("SOCL", "Social") );
		VALUES.add( new ConcreteKeyValue("FUCH", "Funcnical") );
	}
	
	@Override
	public List<KeyValue> getKeyValues() {
		return Collections.unmodifiableList(VALUES);
	}

}
