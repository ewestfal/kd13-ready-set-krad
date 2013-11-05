package org.kuali.kd2013.dataobject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.kuali.rice.krad.data.provider.annotation.Label;
import org.kuali.rice.krad.data.provider.annotation.NonPersistentProperty;
import org.kuali.rice.krad.data.provider.annotation.UifAutoCreateViewType;
import org.kuali.rice.krad.data.provider.annotation.UifAutoCreateViews;

@Entity
@Table(name="KD13_CONF_SESS_T")
@UifAutoCreateViews({UifAutoCreateViewType.INQUIRY,UifAutoCreateViewType.LOOKUP})
public class ConferenceSession {

	@Id
	@Column(name="SESS_ID",precision=10)
	protected long sessionId;

	@Column(name="TITLE",length=60,nullable=false)
	protected String sessionTitle;

	@Column(name="SESS_DATE")
	@Temporal(TemporalType.DATE)
	protected Date date;

	@Column(name="START_TIME")
	@Temporal(TemporalType.TIME)
	protected Date startTime;
	@Column(name="END_TIME")
	@Temporal(TemporalType.TIME)
	protected Date endTime;

	@Column(length=20)
	protected String room;

	@Column(name="SESS_TYPE_CODE",length=4)
	protected String sessionTypeCode;

	@Column(name="DESCRIPTION",length=2000)
	protected String description;

	protected List<PresenterInfo> presenters;

	protected static final String TIME_FORMAT = "hh:mm aa";

	@NonPersistentProperty
	@Label("Time")
	public String getDateRangeString() {
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
		return ((startTime != null)?sdf.format(startTime):"")
				+ " - "
				+ ((endTime != null)?sdf.format(endTime):"");
	}


	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionTitle() {
		return sessionTitle;
	}

	public void setSessionTitle(String sessionTitle) {
		this.sessionTitle = sessionTitle;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getSessionTypeCode() {
		return sessionTypeCode;
	}

	public void setSessionTypeCode(String sessionTypeCode) {
		this.sessionTypeCode = sessionTypeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<PresenterInfo> getPresenters() {
		return presenters;
	}

	public void setPresenters(List<PresenterInfo> presenters) {
		this.presenters = presenters;
	}

}
