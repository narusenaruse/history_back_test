package jp.co.smsdatatech.gittest.form;

import java.util.ArrayList;

/**
 *history.htmlフォーム 
 */
public class HistoryForm {
	
	/** 履歴ビューリスト:historyViewList */
	private ArrayList<HistoryViewForm> historyViewList;
	
	/** イベントリスト:eventList */
	private ArrayList<EventForm> eventList;
	
	/** コンシューマー氏名抽出:chooseConsumerFromName */
	private String chooseConsumerFromName;
	
	/** コンシューマー判定結果抽出:chooseConsumerFromCheckResult */
	private String chooseConsumerFromCheckResult;
	
	/** イベントID:eventId */
	private String eventId;
	
	/** イベント名:eventName */
	private String eventName;

	/**
	* getter:historyViewList
	* @return ArrayList<HistoryViewForm> historyViewList
	*/
	public ArrayList<HistoryViewForm> getHistoryViewList() {
		return historyViewList;
	}

	/**
	* setter:historyViewList
	* @param historyViewList
	*/
	public void setHistoryViewList(ArrayList<HistoryViewForm> historyViewList) {
		this.historyViewList = historyViewList;
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
	* getter:chooseConsumerFromName
	* @return String chooseConsumerFromName
	*/
	public String getChooseConsumerFromName() {
		return chooseConsumerFromName;
	}

	/**
	* setter:chooseConsumerFromName
	* @param chooseConsumerFromName
	*/
	public void setChooseConsumerFromName(String chooseConsumerFromName) {
		this.chooseConsumerFromName = chooseConsumerFromName;
	}

	/**
	* getter:chooseConsumerFromCheckResult
	* @return String chooseConsumerFromCheckResult
	*/
	public String getChooseConsumerFromCheckResult() {
		return chooseConsumerFromCheckResult;
	}

	/**
	* setter:chooseConsumerFromCheckResult
	* @param chooseConsumerFromCheckResult
	*/
	public void setChooseConsumerFromCheckResult(String chooseConsumerFromCheckResult) {
		this.chooseConsumerFromCheckResult = chooseConsumerFromCheckResult;
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
}
