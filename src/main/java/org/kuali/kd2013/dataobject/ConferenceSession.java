package org.kuali.kd2013.dataobject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.kuali.rice.krad.data.jpa.eclipselink.PortableSequenceGenerator;
import org.kuali.rice.krad.data.provider.annotation.KeyValuesFinderClass;
import org.kuali.rice.krad.data.provider.annotation.Label;
import org.kuali.rice.krad.data.provider.annotation.NonPersistentProperty;
import org.kuali.rice.krad.data.provider.annotation.UifAutoCreateViewType;
import org.kuali.rice.krad.data.provider.annotation.UifAutoCreateViews;
import org.kuali.rice.krad.data.provider.annotation.UifDisplayHint;
import org.kuali.rice.krad.data.provider.annotation.UifDisplayHintType;
import org.kuali.rice.krad.data.provider.annotation.UifDisplayHints;

@Entity
@Table(name="KD13_CONF_SESS_T")
@UifAutoCreateViews({UifAutoCreateViewType.INQUIRY,UifAutoCreateViewType.LOOKUP})
public class ConferenceSession implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SESS_ID",length=10)
	@GeneratedValue(generator="KD13_CONF_SESS_ID_S")
	@PortableSequenceGenerator(name="KD13_CONF_SESS_ID_S")
	protected String sessionId;

	@Column(name="TITLE",length=60,nullable=false)
	protected String sessionTitle;

	@Column(name="SESS_DATE")
	@Temporal(TemporalType.DATE)
	protected Date date;

	@Column(name="START_TIME")
	@Temporal(TemporalType.TIME)
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
	protected Date startTime;

	@Column(name="END_TIME")
	@Temporal(TemporalType.TIME)
	@UifDisplayHints({
		@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_RESULT)
		, @UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)
		, @UifDisplayHint(value=UifDisplayHintType.NO_INQUIRY)
	})
	protected Date endTime;

	// Positioned here since properties are processed in order found
	@NonPersistentProperty
	@Label("Time")
	//@UifDisplayHints({@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)})
	public String getDateRangeString() {
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
		return ((startTime != null)?sdf.format(startTime):"")
				+ " - "
				+ ((endTime != null)?sdf.format(endTime):"");
	}

	@Column(length=20)
	protected String room;

	@Column(name="SESS_TYPE_CODE",length=4)
	@KeyValuesFinderClass(SessionTypeValuesFinder.class)
	protected String sessionTypeCode;

	@Column(name="DESCRIPTION",length=2000)
	protected String description;

	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="SESS_ID",referencedColumnName="SESS_ID")
	protected List<SessionPresenter> presenters = new ArrayList<SessionPresenter>();

	@NonPersistentProperty
	//@UifDisplayHints({@UifDisplayHint(value=UifDisplayHintType.NO_LOOKUP_CRITERIA)})
	public String getPresenterNames() {
		StringBuilder sb = new StringBuilder();
		if ( presenters != null ) {
			for ( SessionPresenter sp : presenters ) {
				sb.append( sp.getPresenter().getName() );
				sb.append( "\n" );
			}
		}
		return sb.toString();
	}

	protected static final String TIME_FORMAT = "hh:mm aa";

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
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

	public List<SessionPresenter> getPresenters() {
		return presenters;
	}

	public void setPresenters(List<SessionPresenter> presenters) {
		this.presenters = presenters;
	}

}
