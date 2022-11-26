package jp.co.smsdatatech.gittest.form;

/**
 *EventEntity変換フォーム 
 */
public class EventForm {
	
	/** イベントID:eventId */
	private String eventId;
	
	/** サプライヤーID:supplierId */
	private String supplierId;
	
	/** イベント名:eventName */
	private String eventName;
	
	/** 開催日時:eventDate */
	private String eventDate;
	
	/** 入場開始時間:eventDateStart */
	private String eventDateStart;

	/**
	* getter:eventId
	* @return String eventId
	*/
	public String getEventId() {
		return eventId;
	}

	/**
	* setter:eventId
	* @param eventId
	*/
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	* getter:supplierId
	* @return String supplierId
	*/
	public String getSupplierId() {
		return supplierId;
	}

	/**
	* setter:supplierId
	* @param supplierId
	*/
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	/**
	* getter:eventName
	* @return String eventName
	*/
	public String getEventName() {
		return eventName;
	}

	/**
	* setter:eventName
	* @param eventName
	*/
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	* getter:eventDate
	* @return String eventDate
	*/
	public String getEventDate() {
		return eventDate;
	}

	/**
	* setter:eventDate
	* @param eventDate
	*/
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	/**
	* getter:eventDateStart
	* @return String eventDateStart
	*/
	public String getEventDateStart() {
		return eventDateStart;
	}

	/**
	* setter:eventDateStart
	* @param eventDateStart
	*/
	public void setEventDateStart(String eventDateStart) {
		this.eventDateStart = eventDateStart;
	}
}
