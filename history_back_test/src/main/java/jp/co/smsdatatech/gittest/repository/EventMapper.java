package jp.co.smsdatatech.gittest.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.smsdatatech.gittest.entity.EventEntity;

/**
 *eventテーブルマッパー
 */
@Mapper
public interface EventMapper {
	
	/**
	 * eventテーブルから全てのデータを取得
	 * @return ArrayList<EventEntity> 検索結果
	 */
	@Select("SELECT * FROM event ORDER BY eventId")
	ArrayList<EventEntity> select();
	
	/**
	 * eventテーブルからeventIdと一致するデータのeventNameを取得
	 * @param eventId イベントID
	 * @return eventName 検索結果
	 */
	@Select("SELECT eventName FROM event WHERE eventId = #{eventId}")
	String selectName(@Param("eventId") int eventId);
}
