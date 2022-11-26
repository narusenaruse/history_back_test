package jp.co.smsdatatech.gittest.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * eventテーブルエンティティ
 */
public class EventEntity {
	
	/** イベントID:eventId */
	private BigDecimal eventId;
	
	/** サプライヤーID:supplierId */
	private BigDecimal supplierId;
	
	/** イベント名:eventName */
	private String eventName;
	
	/** 開催日時:eventDate */
	private Date eventDate;
	
	/** 入場開始時間:eventDateStart */
	private Date eventDateStart;
	
	/**
	* getter:eventId
	* @return BigDecimal eventId
	*/
	public BigDecimal getEventId() {
		return eventId;
	}
	/**
	* setter:eventId
	* @param eventId
	*/
	public void setEventId(BigDecimal eventId) {
		this.eventId = eventId;
	}
	/**
	* getter:supplierId
	* @return BigDecimal supplierId
	*/
	public BigDecimal getSupplierId() {
		return supplierId;
	}
	/**
	* setter:supplierId
	* @param supplierId
	*/
	public void setSupplierId(BigDecimal supplierId) {
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
	* @return Date eventDate
	*/
	public Date getEventDate() {
		return eventDate;
	}
	/**
	* setter:eventDate
	* @param eventDate
	*/
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	/**
	* getter:eventDateStart
	* @return Date eventDateStart
	*/
	public Date getEventDateStart() {
		return eventDateStart;
	}
	/**
	* setter:eventDateStart
	* @param eventDateStart
	*/
	public void setEventDateStart(Date eventDateStart) {
		this.eventDateStart = eventDateStart;
	}
}
