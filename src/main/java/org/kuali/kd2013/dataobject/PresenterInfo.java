package org.kuali.kd2013.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="KD13_PRESENTER_T")
public class PresenterInfo {

	@Id
	@Column(name="PRES_ID",length=10)
	protected String presenterId;

	@Column(length=40,nullable=false)
	protected String name;

	@Column(length=40)
	protected String institution;

	@Column(length=40)
	protected String title;

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
