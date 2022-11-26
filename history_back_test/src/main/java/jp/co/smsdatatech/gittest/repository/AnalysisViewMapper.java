package jp.co.smsdatatech.gittest.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.smsdatatech.gittest.entity.AnalysisViewEntity;


/**
 * 分析画面マッパー
 */
@Mapper
public interface AnalysisViewMapper {
	
	/**
	 * analysis_viewからイベントIDが一致するデータを時間の昇順で取得
	 * @param eventId イベントID
	 * @return ArrayList<AnalysisViewEntity> 検索結果
	 */
	@Select("SELECT * FROM analysis_view WHERE eventId = #{eventId} ORDER BY checkDate")
	ArrayList<AnalysisViewEntity> selectEvent (@Param("eventId")int eventId);
}
