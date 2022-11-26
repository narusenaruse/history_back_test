package jp.co.smsdatatech.gittest.form;

import java.util.Map;

import javax.validation.constraints.NotBlank;

/**
 *option.htmlフォーム 
 */
public class OptionForm {
	
	/** メッセージ:msg */
	private String msg;
	
	/** スラックURL:slackUrl */
	@NotBlank(message="URLを入力してください。")
	private String slackUrl;
	
	/** スラックユーザー名:slackUserName */
	@NotBlank(message="ユーザー名を入力してください。")
	private String slackUserName;
	
	/** スラックメッセージ:slackMsg */
	private String slackMsg;
	
	/** エラー用マップ:errorMap */
	private Map<String, Object> errorMap;
	
	/**
	* getter:msg
	* @return String msg
	*/
	public String getMsg() {
		return msg;
	}
	/**
	* setter:msg
	* @param msg
	*/
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	* getter:slackUrl
	* @return String slackUrl
	*/
	public String getSlackUrl() {
		return slackUrl;
	}
	/**
	* setter:slackUrl
	* @param slackUrl
	*/
	public void setSlackUrl(String slackUrl) {
		this.slackUrl = slackUrl;
	}
	/**
	* getter:slackUserName
	* @return String slackUserName
	*/
	public String getSlackUserName() {
		return slackUserName;
	}
	/**
	* setter:slackUserName
	* @param slackUserName
	*/
	public void setSlackUserName(String slackUserName) {
		this.slackUserName = slackUserName;
	}
	/**
	* getter:slackMsg
	* @return String slackMsg
	*/
	public String getSlackMsg() {
		return slackMsg;
	}
	/**
	* setter:slackMsg
	* @param slackMsg
	*/
	public void setSlackMsg(String slackMsg) {
		this.slackMsg = slackMsg;
	}
	/**
	* getter:errorMap
	* @return Map<String,Object> errorMap
	*/
	public Map<String, Object> getErrorMap() {
		return errorMap;
	}
	/**
	* setter:errorMap
	* @param errorMap
	*/
	public void setErrorMap(Map<String, Object> errorMap) {
		this.errorMap = errorMap;
	}
	
}
