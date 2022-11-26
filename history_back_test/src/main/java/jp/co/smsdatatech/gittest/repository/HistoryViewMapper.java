package jp.co.smsdatatech.gittest.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.smsdatatech.gittest.entity.HistoryViewEntity;

/**
 *履歴画面マッパー
 */
@Mapper
public interface HistoryViewMapper {
	
	/**
	 * history_viewから全てのデータを取得
	 * @return ArrayList<HistoryViewEntity> 検索結果
	 */
	@Select("SELECT * FROM history_view ORDER BY historyId")
	ArrayList<HistoryViewEntity> select();
	
	/**
	  * history_viewからコンシューマー名が一致するデータを取得
	  * @param chooseConsumerFromName コンシューマー名
	  * @return ArrayList<HistoryViewEntity> 検索結果
	  */
	@Select("SELECT * FROM history_view WHERE consumerName = #{chooseConsumerFromName} ORDER BY historyId")
	ArrayList<HistoryViewEntity> selectName(@Param("chooseConsumerFromName")String chooseConsumerFromName);
	
	/**
	  * history_viewからコンシューマー認証結果が一致するデータを取得
	  * @param chooseConsumerFromCheckResult コンシューマー認証結果
	  * @return ArrayList<HistoryViewEntity> 検索結果
	  */
	@Select("SELECT * FROM history_view WHERE checkResult = #{chooseConsumerFromCheckResult} ORDER BY historyId")
	ArrayList<HistoryViewEntity> selectCheckResult(@Param("chooseConsumerFromCheckResult")int chooseConsumerFromCheckResult);
	
	/**
	  * history_viewからイベントIDが一致するデータを取得
	  * @param eventId イベントID
	  * @return ArrayList<HistoryViewEntity> 検索結果
	  */
	@Select("SELECT * FROM history_view WHERE eventId = #{eventId} ORDER BY historyId")
	ArrayList<HistoryViewEntity> selectEvent(@Param("eventId")int eventId);
}
