package jp.co.smsdatatech.gittest.form;

import java.util.ArrayList;

/**
 * analysis.htmlフォーム
 */
public class AnalysisForm {
	/** エラーメッセージ:errorMsg */
	private String errorMsg;
	
	/** イベントID:eventId */
	private String eventId;
	
	/** イベントリスト:eventList */
	private ArrayList<EventForm> eventList;
	
	/** OKリスト:okList */
	private ArrayList<String> okList;
	
	/** NGリスト:ngList */
	private ArrayList<String> ngList;
	
	/** ラベルリスト:labelList */
	private ArrayList<String> labelList;
	
	/** OK比率:okPercentage */
	private String okPercentage;
	
	/** NG比率:ngPercentage */
	private String ngPercentage;
	
	/** 判定済比率:yetPercentage */
	private String yetPercentage;
	
	/** 未判定比率:notYetPercentage */
	private String notYetPercentage;
	
	/** マスク着用者比率：facemaskOkPercentage */
	private String facemaskOkPercentage;
	
	/** マスク未着用者比率：facemaskOkPercentage */
	private String facemaskNgPercentage;
	
	/** 体温比率(～35.9)：temperaturePercentage1 */
	private String temperaturePercentage1;
	
	/** 体温比率(36.0～36.5)：temperaturePercentage2 */
	private String temperaturePercentage2;
	
	/** 体温比率(36.6～37.0)：temperaturePercentage3 */
	private String temperaturePercentage3;
	
	/** 体温比率(37.1～)：temperaturePercentage4 */
	private String temperaturePercentage4;

	/**
	* getter:okList
	* @return ArrayList<String> okList
	*/
	public ArrayList<String> getOkList() {
		return okList;
	}

	/**
	* setter:okList
	* @param okList
	*/
	public void setOkList(ArrayList<String> okList) {
		this.okList = okList;
	}

	/**
	* getter:ngList
	* @return ArrayList<String> ngList
	*/
	public ArrayList<String> getNgList() {
		return ngList;
	}

	/**
	* setter:ngList
	* @param ngList
	*/
	public void setNgList(ArrayList<String> ngList) {
		this.ngList = ngList;
	}

	/**
	* getter:labelList
	* @return ArrayList<String> labelList
	*/
	public ArrayList<String> getLabelList() {
		return labelList;
	}

	/**
	* setter:labelList
	* @param labelList
	*/
	public void setLabelList(ArrayList<String> labelList) {
		this.labelList = labelList;
	}

	/**
	* getter:okPercentage
	* @return String okPercentage
	*/
	public String getOkPercentage() {
		return okPercentage;
	}

	/**
	* setter:okPercentage
	* @param okPercentage
	*/
	public void setOkPercentage(String okPercentage) {
		this.okPercentage = okPercentage;
	}

	/**
	* getter:ngPercentage
	* @return String ngPercentage
	*/
	public String getNgPercentage() {
		return ngPercentage;
	}

	/**
	* setter:ngPercentage
	* @param ngPercentage
	*/
	public void setNgPercentage(String ngPercentage) {
		this.ngPercentage = ngPercentage;
	}

	/**
	* getter:yetPercentage
	* @return String yetPercentage
	*/
	public String getYetPercentage() {
		return yetPercentage;
	}

	/**
	* setter:yetPercentage
	* @param yetPercentage
	*/
	public void setYetPercentage(String yetPercentage) {
		this.yetPercentage = yetPercentage;
	}

	/**
	* getter:notYetPercentage
	* @return String notYetPercentage
	*/
	public String getNotYetPercentage() {
		return notYetPercentage;
	}

	/**
	* setter:notYetPercentage
	* @param notYetPercentage
	*/
	public void setNotYetPercentage(String notYetPercentage) {
		this.notYetPercentage = notYetPercentage;
	}

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
	* getter:eventList
	* @return ArrayList<EventForm> eventList
	*/
	public ArrayList<EventForm> getEventList() {
		return eventList;
	}

	/**
	* setter:eventList
	* @param eventList
	*/
	public void setEventList(ArrayList<EventForm> eventList) {
		this.eventList = eventList;
	}

	/**
	* getter:errorMsg
	* @return String errorMsg
	*/
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	* setter:errorMsg
	* @param errorMsg
	*/
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	* getter:facemaskOkPercentage
	* @return String facemaskOkPercentage
	*/
	public String getFacemaskOkPercentage() {
		return facemaskOkPercentage;
	}

	/**
	* setter:facemaskOkPercentage
	* @param facemaskOkPercentage
	*/
	public void setFacemaskOkPercentage(String facemaskOkPercentage) {
		this.facemaskOkPercentage = facemaskOkPercentage;
	}

	/**
	* getter:facemaskNgPercentage
	* @return String facemaskNgPercentage
	*/
	public String getFacemaskNgPercentage() {
		return facemaskNgPercentage;
	}

	/**
	* setter:facemaskNgPercentage
	* @param facemaskNgPercentage
	*/
	public void setFacemaskNgPercentage(String facemaskNgPercentage) {
		this.facemaskNgPercentage = facemaskNgPercentage;
	}

	/**
	* getter:temperaturePercentage1
	* @return String temperaturePercentage1
	*/
	public String getTemperaturePercentage1() {
		return temperaturePercentage1;
	}

	/**
	* setter:temperaturePercentage1
	* @param temperaturePercentage1
	*/
	public void setTemperaturePercentage1(String temperaturePercentage1) {
		this.temperaturePercentage1 = temperaturePercentage1;
	}

	/**
	* getter:temperaturePercentage2
	* @return String temperaturePercentage2
	*/
	public String getTemperaturePercentage2() {
		return temperaturePercentage2;
	}

	/**
	* setter:temperaturePercentage2
	* @param temperaturePercentage2
	*/
	public void setTemperaturePercentage2(String temperaturePercentage2) {
		this.temperaturePercentage2 = temperaturePercentage2;
	}

	/**
	* getter:temperaturePercentage3
	* @return String temperaturePercentage3
	*/
	public String getTemperaturePercentage3() {
		return temperaturePercentage3;
	}

	/**
	* setter:temperaturePercentage3
	* @param temperaturePercentage3
	*/
	public void setTemperaturePercentage3(String temperaturePercentage3) {
		this.temperaturePercentage3 = temperaturePercentage3;
	}

	/**
	* getter:temperaturePercentage4
	* @return String temperaturePercentage4
	*/
	public String getTemperaturePercentage4() {
		return temperaturePercentage4;
	}

	/**
	* setter:temperaturePercentage4
	* @param temperaturePercentage4
	*/
	public void setTemperaturePercentage4(String temperaturePercentage4) {
		this.temperaturePercentage4 = temperaturePercentage4;
	}
}
