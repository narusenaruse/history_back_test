package jp.co.smsdatatech.gittest.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.smsdatatech.gittest.entity.EventEntity;
import jp.co.smsdatatech.gittest.repository.EventMapper;

/**
 * eventテーブルサービス  
 */
@Service
public class EventMapperService {
	
	@Autowired
	EventMapper eventMapper;
	
	/**
	 * eventテーブルから全てのデータを取得
	 * @return ArrayList<EventEntity> 検索結果
	 */
	public ArrayList<EventEntity> select() {
		return eventMapper.select();
	}
	
	/**
	 * eventテーブルからeventIdと一致するデータのeventNameを取得
	 * @param eventId イベントID
	 * @return eventName 検索結果
	 */
	public String selectName(int eventId) {
		return eventMapper.selectName(eventId);
	}
}
