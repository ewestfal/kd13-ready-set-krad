package org.kuali.kd2013.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="KD13_CONF_SESS_T")
public class PresenterInfo {

	@Id
	@Column(name="PRES_ID",precision=10)
	protected long presenterId;
	
	@Column(length=40)
	protected String name;
	
	@Column(length=40)
	protected String institution;
	
	@Column(length=40)
	protected String title;
	
	@Column(name="PRIMARY_IND")
	boolean primary;
	
	public long getPresenterId() {
		return presenterId;
	}
	public void setPresenterId(long presenterId) {
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
	public boolean isPrimary() {
		return primary;
	}
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
}
