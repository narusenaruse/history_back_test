package jp.co.smsdatatech.gittest.entity;

import java.math.BigDecimal;

/**
 *optionテーブルエンティティ
 */
public class OptionEntity {
	
	/** 設定ID:optionId */
	private BigDecimal optionId;
	
	/** サプライヤーID:supplierId */
	private BigDecimal supplierId;
	
	/** 設定グループ:optionGroup */
	private String optionGroup;
	
	/** 設定名:optionName */
	private String optionName;
	
	/** 設定値:optionValue */
	private String optionValue;
	
	/**
	* getter:optionId
	* @return BigDecimal optionId
	*/
	public BigDecimal getOptionId() {
		return optionId;
	}
	/**
	* setter:optionId
	* @param optionId
	*/
	public void setOptionId(BigDecimal optionId) {
		this.optionId = optionId;
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
	* getter:optionGroup
	* @return String optionGroup
	*/
	public String getOptionGroup() {
		return optionGroup;
	}
	/**
	* setter:optionGroup
	* @param optionGroup
	*/
	public void setOptionGroup(String optionGroup) {
		this.optionGroup = optionGroup;
	}
	/**
	* getter:optionName
	* @return String optionName
	*/
	public String getOptionName() {
		return optionName;
	}
	/**
	* setter:optionName
	* @param optionName
	*/
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	/**
	* getter:optionValue
	* @return String optionValue
	*/
	public String getOptionValue() {
		return optionValue;
	}
	/**
	* setter:optionValue
	* @param optionValue
	*/
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}
}