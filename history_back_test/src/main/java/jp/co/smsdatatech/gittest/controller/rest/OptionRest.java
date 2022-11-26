package jp.co.smsdatatech.gittest.controller.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.co.smsdatatech.gittest.common.logs.AppLogger;
import jp.co.smsdatatech.gittest.entity.OptionEntity;
import jp.co.smsdatatech.gittest.form.OptionForm;
import jp.co.smsdatatech.gittest.service.OptionMapperService;

/**
 *設定画面コントローラー
 */
@RestController
public class OptionRest {
	
	@Autowired
	OptionMapperService optionMapperService;
	
	AppLogger appLogger = new AppLogger();
	
	/**
	 * slack設定初期表示
	 * supplierIdごとに認証データを取得し、スラックUrl、スラックユーザー名、スラックメッセージをFormにセットし返却します。
	 * @param OptionForm form 設定画面フォーム
	 * @return OptionForm 設定画面フォーム
	 */
	@RequestMapping(value = "/option/slack/init", method = RequestMethod.POST)
	public OptionForm init(OptionForm form) {
		
		int supplierId = 1;
		ArrayList<OptionEntity> entityList = optionMapperService.select(supplierId);
		
		if(!Objects.isNull(entityList)) {
			for (OptionEntity entity : entityList) {
				switch(entity.getOptionName()) {
				case "slackUrl": 
					form.setSlackUrl(entity.getOptionValue());
					break;
				
				case "slackUserName":
					form.setSlackUserName(entity.getOptionValue());
					break;
					
				case "slackMsg":
					form.setSlackMsg(entity.getOptionValue());
					break;
				}
			}
		}
		
		return form;
	}
	
	/**
	 * slack設定連携機能
	 * supplierIdと設定画面フォームから取得したデータをoptionテーブルに保存後、メッセージをformにセットし返却します。
	 * supplierIdごとに認証データを取得し、取得したデータがあれば更新、なければ追加します。
	 * 設定画面フォームに入力エラーがある場合は、エラーメッセージをformにセットし返却します。
	 * @param OptionForm form 設定画面フォーム
	 * @return OptionForm 設定画面フォーム
	 */
	@RequestMapping(value = "/option/slack/cooperate", method = RequestMethod.POST)
	public OptionForm cooperate(@Validated OptionForm form, BindingResult br){
		appLogger.info("start option process");
		
		//入力チェック
		if(br.hasErrors()) {
			List<ObjectError> errorList =  br.getAllErrors();
			Map<String, Object> errorMap = new HashMap<String, Object>();
			for (ObjectError error : errorList) {
				appLogger.error(((FieldError) error).getField());
				errorMap.put(((FieldError) error).getField(), error.getDefaultMessage());
			}
			
			form.setErrorMap(errorMap);
			return form;
		}
		
		//初期値設定
		ArrayList<OptionEntity> entityList = new ArrayList<OptionEntity>();
		BigDecimal supplierId = new BigDecimal(1);
		String optionGroup = "slack";

		//slackUrlを取得していればtrue
		if(form.getSlackUrl() != null && !form.getSlackUrl().isEmpty()) {
			String optionName = "slackUrl";
			entityList.add(substitutionEntityFromForm(supplierId, optionGroup, optionName, form.getSlackUrl()));
		}
		
		//slackUserNameを取得していればtrue
		if(form.getSlackUserName() != null && !form.getSlackUserName().isEmpty()) {
			String optionName = "slackUserName";
			entityList.add(substitutionEntityFromForm(supplierId, optionGroup, optionName, form.getSlackUserName()));
		}

		//slackMsgを取得していればtrue
		if(form.getSlackMsg() != null && !form.getSlackMsg().isEmpty()) {
			String optionName = "slackMsg";
			entityList.add(substitutionEntityFromForm(supplierId, optionGroup, optionName, form.getSlackMsg()));
		}

		//設定処理呼び出し
		optionMapperService.option(entityList);
		appLogger.info("finish option process");
		
		//設定完了メッセージ
		form.setMsg("登録が完了しました");

		return form;
	}
	
	/**
	 * OptionFormからOptionEntityへ格納するためのメソッド
	 * OptionFormの中身をoptionEntityで扱える形に変換し、OptionFormを返却します。
	 * @param supplierId	サプライヤーID
	 * @param optionGroup	設定グループ
	 * @param optionName	設定名
	 * @param optionValue	設定値
	 * @return OptionEntity optionテーブルエンティティ
	 */
	private OptionEntity substitutionEntityFromForm(BigDecimal supplierId, String optionGroup, String optionName,String optionValue) {
		OptionEntity optionEntity = new OptionEntity();
		
		optionEntity.setSupplierId(supplierId);
		optionEntity.setOptionGroup(optionGroup);
		optionEntity.setOptionName(optionName);
		optionEntity.setOptionValue(optionValue);
		return optionEntity;
	}
}
