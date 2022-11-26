package jp.co.smsdatatech.gittest.controller.rest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.co.smsdatatech.gittest.entity.AnalysisViewEntity;
import jp.co.smsdatatech.gittest.entity.EventEntity;
import jp.co.smsdatatech.gittest.form.AnalysisForm;
import jp.co.smsdatatech.gittest.form.EventForm;
import jp.co.smsdatatech.gittest.service.AnalysisViewMapperService;
import jp.co.smsdatatech.gittest.service.EventMapperService;



/**
 *分析画面コントローラー
 */
@RestController
public class AnalysisRest {

	@Autowired
	MessageSource messageSource;

	@Autowired
	EventMapperService eventMapperService;

	@Autowired
	AnalysisViewMapperService analysisViewMapperService;

	/**
	 * 分析画面初期表示
	 * イベント一覧を取得し、返却します。
	 * @param AnalysisForm form 分析画面フォーム
	 * @return AnalysisForm 分析画面フォーム
	 */
	@RequestMapping(value = "/analysis/init", method = RequestMethod.POST)
	public AnalysisForm init(AnalysisForm form){

		form.setEventList(substitutionEventFormFromEventEntity(eventMapperService.select()));
		return form;
	}

	/**
	 * 1時間ごとの認証数抽出機能
	 * eventIdごとに認証データを取得し、1時間ごとのOK/NG数をFormにセットし返却します。
	 * @param AnalysisForm form 分析画面フォーム
	 * @return AnalysisForm 分析画面フォーム
	 */
	@RequestMapping(value = "/analysis/init/authenticationHour", method = RequestMethod.POST)
	public AnalysisForm authenticationHour(AnalysisForm form){

		//analysis_viewからeventIdと一致するデータを取得
		int eventId = Integer.parseInt(form.getEventId());
		ArrayList<AnalysisViewEntity> analysisList = analysisViewMapperService.selectEvent(eventId);

		//エラーチェック
		if(analysisList.size() < 1){
			form.setErrorMsg(messageSource.getMessage("HIS.I.MESSAGE.00001", null, Locale.JAPAN));
			return form;
		}

		//analysisList.checkDateのnullを省く
		int finishIndex = analysisList.size() - 1;
		while(Objects.isNull(analysisList.get(finishIndex).getCheckDate())) {

			//エラーチェック
			if(finishIndex < 1) {
				if(Objects.isNull(analysisList.get(finishIndex).getCheckDate())) {
					form.setErrorMsg(messageSource.getMessage("HIS.I.MESSAGE.00001", null, Locale.JAPAN));
					return form;
				}
			}

			finishIndex--;
		}

		//analysisList.checkDateに格納されている最初と最後の要素
		int firstCheckDate = Integer.parseInt(dateFormatHour(analysisList.get(0).getCheckDate()));
		int lastCheckDate = Integer.parseInt(dateFormatHour(analysisList.get(finishIndex).getCheckDate()));

		ArrayList<String> okList = new ArrayList<String>();
		ArrayList<String> ngList = new ArrayList<String>();
		ArrayList<String> labelList = new ArrayList<String>();

		for(int i = firstCheckDate; i <= lastCheckDate; i++) {
			int okCount = 0;
			int ngCount = 0;

			for(AnalysisViewEntity analysis : analysisList) {
				if(!Objects.isNull(analysis.getCheckDate())) {

					//1時間当たりの認証結果をカウント
					if(i == Integer.parseInt(dateFormatHour(analysis.getCheckDate()))) {
						switch(analysis.getCheckResult()) {
						case 1:
							okCount++;
							break;

						case 2:
							ngCount++;
							break;
						}
					}
				}
			}

			//データの格納
			okList.add(String.valueOf(okCount));
			ngList.add(String.valueOf(ngCount));
			labelList.add(i + "時");
		}

		form.setOkList(okList);
		form.setNgList(ngList);
		form.setLabelList(labelList);

		return form;
	}

	/**
	 * OK/NG判定データ抽出機能
	 * eventIdごとに認証データを取得し、OK/NG数を百分率でFormにセットし返却します。
	 * @param AnalysisForm form 分析画面フォーム
	 * @return AnalysisForm 分析画面フォーム
	 */
	@RequestMapping(value = "/analysis/init/authenticationResult", method = RequestMethod.POST)
	public AnalysisForm authenticationResult(AnalysisForm form){

		//analysis_viewからeventIdと一致するデータを取得
		int eventId = Integer.parseInt(form.getEventId());
		ArrayList<AnalysisViewEntity> analysisList = analysisViewMapperService.selectEvent(eventId);

		//エラーチェック
		if(analysisList.size() < 1){
			form.setErrorMsg(messageSource.getMessage("HIS.I.MESSAGE.00001", null, Locale.JAPAN));
			return form;
		}

		double okCount = 0;
		double ngCount = 0;

		//OKとNGの人数を取得
		for(AnalysisViewEntity analysis : analysisList) {
			switch(analysis.getCheckResult()) {
			case 1:
				okCount++;
				break;

			case 2:
				ngCount++;
				break;
			}
		}

		//OK/NGの比率計算
		double totalCount = okCount + ngCount;
		int okPercentage = (int) ((okCount / totalCount) * 100);
		int ngPercentage = 100 - okPercentage;

		form.setOkPercentage(String.valueOf(okPercentage));
		form.setNgPercentage(String.valueOf(ngPercentage));

		return form;
	}

	/**
	 * 受付判定結果抽出機能
	 * eventIdごとに認証データを取得し、判定済人数と未判定人数を百分率でFormにセットし返却します。
	 * @param AnalysisForm form 分析画面フォーム
	 * @return AnalysisForm 分析画面フォーム
	 */
	@RequestMapping(value = "/analysis/init/receptionRate", method = RequestMethod.POST)
	public AnalysisForm receptionRate(AnalysisForm form){

		//analysis_viewからeventIdと一致するデータを取得
		int eventId = Integer.parseInt(form.getEventId());
		ArrayList<AnalysisViewEntity> analysisList = analysisViewMapperService.selectEvent(eventId);

		//エラーチェック
		if(analysisList.size() < 1){
			form.setErrorMsg(messageSource.getMessage("HIS.I.MESSAGE.00001", null, Locale.JAPAN));
			return form;
		}

		double totalCount = 0;
		double okCount = 0;

		//受付人数を取得
		for(AnalysisViewEntity analysis : analysisList) {
			if(analysis.getCheckResult() == 1) {
				okCount++;
			}
			totalCount++;
		}

		//受付の比率計算
		int yetPercentage = (int) ((okCount / totalCount) * 100);
		int notYetPercentage = 100 - yetPercentage;

		form.setYetPercentage(String.valueOf(yetPercentage));
		form.setNotYetPercentage(String.valueOf(notYetPercentage));

		return form;
	}

	

	/**
	 * マスク着用率取得
	 * eventIdごとに認証データを取得し、マスク着用者およびマスク未着用者の比率をFormにセットし返却します。
	 * @param AnalysisForm form 分析画面フォーム
	 * @return AnalysisForm 分析画面フォーム
	 */
	@RequestMapping(value = "/analysis/init/maskRate", method = RequestMethod.POST)
	public AnalysisForm getWearingMaskRate(AnalysisForm form) {

		/* analysis_viewからeventIdと一致するデータを取得 */
		int eventId = Integer.parseInt(form.getEventId());
		ArrayList<AnalysisViewEntity> analysisList = analysisViewMapperService.selectEvent(eventId);

		//エラーチェック
		if(analysisList.size() < 1){
			form.setErrorMsg(messageSource.getMessage("HIS.I.MESSAGE.00001", null, Locale.JAPAN));
			return form;
		}

		double okCount = 0;
		double ngCount = 0;

		/* マスク着用者とマスク未着用者の人数を取得 */
		for(AnalysisViewEntity analysis : analysisList) {
			switch(analysis.getFaceMaskType()) {
			case 1:
				okCount++;
				break;

			case 0:
				ngCount++;
				break;
			}
		}

		/* マスク着用者とマスク未着用者の比率計算 */
		double totalCount = okCount + ngCount;
		int facemaskOkPercentage = (int) ((okCount / totalCount) * 100);
		int facemaskNgPercentage = 100 - facemaskOkPercentage;

		form.setFacemaskOkPercentage(String.valueOf(facemaskOkPercentage));
		form.setFacemaskNgPercentage(String.valueOf(facemaskNgPercentage));

		return form;
	}

	/**
	 * 体温比率取得
	 * eventIdごとに認証データを取得し、体温別の比率をFormにセットし返却します。
	 * @param AnalysisForm form 分析画面フォーム
	 * @return AnalysisForm 分析画面フォーム
	 */
	@RequestMapping(value = "/analysis/init/temperatureRate", method = RequestMethod.POST)
	public AnalysisForm getTemperatureRate(AnalysisForm form) {

		/* analysis_viewからeventIdと一致するデータを取得 */
		int eventId = Integer.parseInt(form.getEventId());
		ArrayList<AnalysisViewEntity> analysisList = analysisViewMapperService.selectEvent(eventId);

		//エラーチェック
		if(analysisList.size() < 1){
			form.setErrorMsg(messageSource.getMessage("HIS.I.MESSAGE.00001", null, Locale.JAPAN));
			return form;
		}

		double count1 = 0;
		double count2 = 0;
		double count3 = 0;
		double count4 = 0;

		/* 体温別人数を取得 */
		for(AnalysisViewEntity analysis : analysisList) {

			double temperature = analysis.getTemperature();

			if(temperature <= 35.9) {
				count1++;

			}else if(temperature >= 36.0 && temperature <= 36.5) {
				count2++;

			}else if(temperature >= 36.6 && temperature <= 37.0) {
				count3++;

			}else if(temperature >= 37.1) {
				count4++;

			}
		}

		/* 体温の比率計算 */
		double totalCount = count1 + count2 + count3 + count4;
		int percentage1 = (int) ((count1 / totalCount) * 100);
		int percentage2 = (int) ((count2 / totalCount) * 100);
		int percentage3 = (int) ((count3 / totalCount) * 100);
		int percentage4 = 100 - percentage1 - percentage2 - percentage3;

		form.setTemperaturePercentage1(String.valueOf(percentage1));
		form.setTemperaturePercentage2(String.valueOf(percentage2));
		form.setTemperaturePercentage3(String.valueOf(percentage3));
		form.setTemperaturePercentage4(String.valueOf(percentage4));

		return form;
	}
	
	/**
	 * eventListからeventFormListへ格納するためのメソッド
	 * eventListの中身をeventFormListで扱える形に変換し、eventFormListを返却します。
	 * @param ArrayList<EventEntity> イベントリスト
	 * @return ArrayList<EventForm> イベントフォームリスト
	 */
	private ArrayList<EventForm> substitutionEventFormFromEventEntity(ArrayList<EventEntity> eventList){
		ArrayList<EventForm> eventFormList = new ArrayList<EventForm>();

		for(EventEntity event : eventList) {
			EventForm eventForm = new EventForm();
			eventForm.setEventId(String.valueOf(event.getEventId().intValue()));
			eventForm.setSupplierId(String.valueOf(event.getSupplierId().intValue()));
			eventForm.setEventName(event.getEventName());
			eventForm.setEventDate(dateFormat(event.getEventDate()));
			eventForm.setEventDateStart(dateFormat(event.getEventDateStart()));
			eventFormList.add(eventForm);
		}

		return eventFormList;
	}

	/**
	 * 時間フォーマット用メソッド
	 * Date型の認証時間を｢HH｣でフォーマットし、String型で返却します。
	 * @param Date 認証時間
	 * @return String フォーマット後の認証時間
	 */
	private String dateFormatHour(Date date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH");

		Instant instant = date.toInstant();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

		return formatter.format(localDateTime);
	}

	/**
	 * 日時フォーマット用メソッド
	 * Date型の認証時間を｢YYYY/MM/dd HH:mm:ss｣でフォーマットし、String型で返却します。
	 * @param Date 認証時間
	 * @return String フォーマット後の認証時間
	 */
	private String dateFormat(Date date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY/MM/dd HH:mm:ss");

		Instant instant = date.toInstant();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

		return formatter.format(localDateTime);
	}
}