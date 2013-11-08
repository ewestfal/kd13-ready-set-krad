package org.kuali.kd2013.dataobject;

import java.io.Serializable;

//@Entity
//@Table(name="KD13_PRESENTER_T")
public class PresenterInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	protected String presenterId;
	protected String name;
	protected String institution;
	protected String title;

}
