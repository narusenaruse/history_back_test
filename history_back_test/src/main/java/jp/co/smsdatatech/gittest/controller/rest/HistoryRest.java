package jp.co.smsdatatech.gittest.controller.rest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.co.smsdatatech.gittest.entity.EventEntity;
import jp.co.smsdatatech.gittest.entity.HistoryViewEntity;
import jp.co.smsdatatech.gittest.form.EventForm;
import jp.co.smsdatatech.gittest.form.HistoryForm;
import jp.co.smsdatatech.gittest.form.HistoryViewForm;
import jp.co.smsdatatech.gittest.service.EventMapperService;
import jp.co.smsdatatech.gittest.service.HistoryViewMapperService;

/**
 *履歴画面コントローラー
 */
@RestController
public class HistoryRest {

	@Autowired
	HistoryViewMapperService historyViewMapperService;

	@Autowired
	EventMapperService eventMapperService; 

	/**
	 * 参照機能
	 * イベント一覧、履歴一覧を取得し、返却します。
	 * @param HistoryForm form 一覧画面フォーム
	 * @return HistoryForm 一覧画面フォーム
	 */
	@RequestMapping(value = "/history/init", method = RequestMethod.POST)
	public HistoryForm init(HistoryForm form){
		
		form.setHistoryViewList(substitutionHistoryFormFromHistoryViewEntity(historyViewMapperService.select()));
		form.setEventList(substitutionEventFormFromEventEntity(eventMapperService.select()));
		return form;
	}

	/**
	 * 名前検索機能
	 * chooseConsumerFromNameごとに認証データを取得し、取得したデータをformにセットし返却します。
	 * @param HistoryForm form 一覧画面フォーム
	 * @return HistoryForm 一覧画面フォーム
	 */
	@RequestMapping(value = "/history/selectName", method = RequestMethod.POST)
	public HistoryForm selectName(HistoryForm form){

		String chooseConsumerFromName = form.getChooseConsumerFromName();
		form.setHistoryViewList(substitutionHistoryFormFromHistoryViewEntity(historyViewMapperService.selectName(chooseConsumerFromName)));
		form.setEventList(substitutionEventFormFromEventEntity(eventMapperService.select()));
		return form;
	}

	/**
	 * 判定検索機能
	 * chooseConsumerFromCheckResultごとに認証データを取得し、取得したデータをformにセットし返却します。
	 * @param HistoryForm form 一覧画面フォーム
	 * @return HistoryForm 一覧画面フォーム
	 */
	@RequestMapping(value = "/history/selectCheckResult", method = RequestMethod.POST)
	public HistoryForm selectCheckResult(HistoryForm form){

		int chooseConsumerFromCheckResult = Integer.parseInt(form.getChooseConsumerFromCheckResult());
		form.setHistoryViewList(substitutionHistoryFormFromHistoryViewEntity(historyViewMapperService.selectCheckResult(chooseConsumerFromCheckResult)));
		form.setEventList(substitutionEventFormFromEventEntity(eventMapperService.select()));
		return form;
	}

	/**
	 * イベント検索機能
	 * eventIdごとに認証データを取得し、取得したデータをformにセットし返却します。
	 * @param HistoryForm form 一覧画面フォーム
	 * @return HistoryForm 一覧画面フォーム	
	 */
	@RequestMapping(value = "/history/selectEvent", method = RequestMethod.POST)
	public HistoryForm selectEvent(HistoryForm form){

		int eventId = Integer.parseInt(form.getEventId());
		form.setHistoryViewList(substitutionHistoryFormFromHistoryViewEntity(historyViewMapperService.selectEvent(eventId)));
		form.setEventList(substitutionEventFormFromEventEntity(eventMapperService.select()));
		form.setEventName(eventMapperService.selectName(eventId));
		return form;
	}

	/**
	 * historyViewListからhistoryViewFormListへ格納するためのメソッド
	 * historyViewListの中身をhistoryViewFormListで扱える形に変換し、historyViewFormListを返却します。
	 * @param ArrayList<HistoryViewEntity> 履歴リスト
	 * @return ArrayList<HistoryViewForm> 履歴フォームリスト
	 */
	private ArrayList<HistoryViewForm> substitutionHistoryFormFromHistoryViewEntity(ArrayList<HistoryViewEntity> historyViewList){
		ArrayList<HistoryViewForm> historyViewFormList = new ArrayList<HistoryViewForm>();

		for(HistoryViewEntity entity : historyViewList) {
			HistoryViewForm historyForm = new HistoryViewForm();
			historyForm.setHistoryId(String.valueOf(entity.getHistoryId().intValue()));
			historyForm.setEventId(String.valueOf(entity.getEventId().intValue()));
			historyForm.setSupplierId(String.valueOf(entity.getSupplierId().intValue()));
			historyForm.setConsumerId(String.valueOf(entity.getConsumerId().intValue()));
			historyForm.setConsumerName(entity.getConsumerName());
			historyForm.setConsumerNameKana(entity.getConsumerNameKana());
			historyForm.setCheckResult(Integer.toString(entity.getCheckResult()));

			if(!Objects.isNull(entity.getCheckDate())) {
				historyForm.setCheckDate(dateFormat(entity.getCheckDate()));
			}

			if(entity.getPicture() != null && !entity.getPicture().isEmpty()) {
				historyForm.setPicture(entity.getPicture());
			}

			historyViewFormList.add(historyForm);
		}
		return historyViewFormList;
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
