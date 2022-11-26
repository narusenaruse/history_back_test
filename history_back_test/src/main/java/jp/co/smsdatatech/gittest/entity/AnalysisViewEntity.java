package jp.co.smsdatatech.gittest.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *analysis_viewエンティティ
 */
public class AnalysisViewEntity {
	
	/** イベントID:eventId */
	private BigDecimal eventId;
	
	/** サプライヤーID:supplierId */
	private BigDecimal supplierId;
	
	/** コンシューマーID:consumerId */
	private BigDecimal consumerId;
	
	/** 判定結果:checkResult */
	private int checkResult;
	
	/** 認証時間:checkDate */
	private Date checkDate;
	
	/** マスクの有無:faceMaskType */
	private int faceMaskType;
	
	/** 体温:temperature */
	private double temperature;
	
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
	* getter:consumerId
	* @return BigDecimal consumerId
	*/
	public BigDecimal getConsumerId() {
		return consumerId;
	}
	/**
	* setter:consumerId
	* @param consumerId
	*/
	public void setConsumerId(BigDecimal consumerId) {
		this.consumerId = consumerId;
	}
	/**
	* getter:checkResult
	* @return int checkResult
	*/
	public int getCheckResult() {
		return checkResult;
	}
	/**
	* setter:checkResult
	* @param checkResult
	*/
	public void setCheckResult(int checkResult) {
		this.checkResult = checkResult;
	}
	/**
	* getter:checkDate
	* @return Date checkDate
	*/
	public Date getCheckDate() {
		return checkDate;
	}
	/**
	* setter:checkDate
	* @param checkDate
	*/
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	/**
	* getter:faceMaskType
	* @return int faceMaskType
	*/
	public int getFaceMaskType() {
		return faceMaskType;
	}
	/**
	* setter:faceMaskType
	* @param faceMaskType
	*/
	public void setFaceMaskType(int faceMaskType) {
		this.faceMaskType = faceMaskType;
	}
	/**
	* getter:temperature
	* @return double temperature
	*/
	public double getTemperature() {
		return temperature;
	}
	/**
	* setter:temperature
	* @param temperature
	*/
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
}
