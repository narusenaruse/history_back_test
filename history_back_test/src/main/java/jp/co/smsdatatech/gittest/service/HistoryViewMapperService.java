package jp.co.smsdatatech.gittest.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.smsdatatech.gittest.entity.HistoryViewEntity;
import jp.co.smsdatatech.gittest.repository.HistoryViewMapper;

/**
 *履歴画面サービス
 */
@Service
public class HistoryViewMapperService {
	
	@Autowired
	HistoryViewMapper historyViewMapper;
	
	/**
	 * history_viewから全てのデータを取得
	 * @return ArrayList<HistoryViewEntity> 検索結果
	 */
	public ArrayList<HistoryViewEntity> select(){
		return historyViewMapper.select();
	}
	
	/**
	  * history_viewからコンシューマー名が一致するデータを取得
	  * @param chooseConsumerFromName コンシューマー名
	  * @return ArrayList<HistoryViewEntity> 検索結果
	  */
	public ArrayList<HistoryViewEntity> selectName(String chooseConsumerFromName){
		return historyViewMapper.selectName(chooseConsumerFromName);
	}
	
	/**
	  * history_viewからコンシューマー認証結果が一致するデータを取得
	  * @param chooseConsumerFromCheckResult コンシューマー認証結果
	  * @return ArrayList<HistoryViewEntity> 検索結果
	  */
	public ArrayList<HistoryViewEntity> selectCheckResult(int chooseConsumerFromCheckResult){
		return historyViewMapper.selectCheckResult(chooseConsumerFromCheckResult);
	}
	
	/**
	  * history_viewからイベントIDが一致するデータを取得
	  * @param eventId イベントID
	  * @return ArrayList<HistoryViewEntity> 検索結果
	  */
	public ArrayList<HistoryViewEntity> selectEvent(int eventId){
		return historyViewMapper.selectEvent(eventId);
	}
}
