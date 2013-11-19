package org.kuali.kd2013.dataobject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.kuali.rice.krad.data.jpa.eclipselink.PortableSequenceGenerator;
import org.kuali.rice.krad.data.provider.annotation.UifAutoCreateViewType;
import org.kuali.rice.krad.data.provider.annotation.UifAutoCreateViews;

@Entity
@Table(name="KD13_PRESENTER_T")
@UifAutoCreateViews({UifAutoCreateViewType.INQUIRY,UifAutoCreateViewType.LOOKUP})
public class PresenterInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PRES_ID",length=10)
	@GeneratedValue(generator="KD13_PRESENTER_ID_S")
	@PortableSequenceGenerator(name="KD13_PRESENTER_ID_S")
	protected String presenterId; // PRES_ID / 10 / KD13_PRESENTER_ID_S
	
	@Column(length=40,nullable=false)
	protected String name; // 40 / NN

	@Column(length=40)
	protected String institution; // 40

	@Column(length=40)
	protected String title; // 40
	
	public String getPresenterId() {
		return presenterId;
	}
	public void setPresenterId(String presenterId) {
		this.presenterId = presenterId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
