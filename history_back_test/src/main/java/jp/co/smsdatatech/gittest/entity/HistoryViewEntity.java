package jp.co.smsdatatech.gittest.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *history_viewテーブルエンティティ
 */
public class HistoryViewEntity {
	
	/** 履歴ID:historyId */
	private BigDecimal historyId;
	
	/** イベントID:eventId */
	private BigDecimal eventId;
	
	/** サプライヤーID:supplierId */
	private BigDecimal supplierId;
	
	/** コンシューマーID:consumerId */
	private BigDecimal consumerId;
	
	/** 氏名:consumerName */
	private String consumerName;
	
	/** 氏名カナ:consumerNameKana */
	private String consumerNameKana;
	
	/** 判定結果:checkResult */
	private int checkResult;
	
	/** 認証時間:checkDate */
	private Date checkDate;
	
	/** 顔写真:picture */
	private String picture;
	
	/**
	* getter:historyId
	* @return BigDecimal historyId
	*/
	public BigDecimal getHistoryId() {
		return historyId;
	}
	/**
	* setter:historyId
	* @param historyId
	*/
	public void setHistoryId(BigDecimal historyId) {
		this.historyId = historyId;
	}
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
	* getter:consumerName
	* @return String consumerName
	*/
	public String getConsumerName() {
		return consumerName;
	}
	/**
	* setter:consumerName
	* @param consumerName
	*/
	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}
	/**
	* getter:consumerNameKana
	* @return String consumerNameKana
	*/
	public String getConsumerNameKana() {
		return consumerNameKana;
	}
	/**
	* setter:consumerNameKana
	* @param consumerNameKana
	*/
	public void setConsumerNameKana(String consumerNameKana) {
		this.consumerNameKana = consumerNameKana;
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
	* getter:picture
	* @return String picture
	*/
	public String getPicture() {
		return picture;
	}
	/**
	* setter:picture
	* @param picture
	*/
	public void setPicture(String picture) {
		this.picture = picture;
	}
}