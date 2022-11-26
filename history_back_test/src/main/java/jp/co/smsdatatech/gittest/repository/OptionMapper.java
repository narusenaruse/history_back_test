package jp.co.smsdatatech.gittest.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.smsdatatech.gittest.entity.OptionEntity;

/**
 *optionテーブルマッパー
 */
@Mapper
public interface OptionMapper {
	
	/**
	 * optionにデータ追加
	 * @param entity optionに格納するデータ
	 */
	@Insert("INSERT INTO option "
			+ "(optionId, "
			+ "supplierId, "
			+ "optionGroup, "
			+ "optionName, "
			+ "optionValue) "
			+ "VALUES "
			+ "(#{entity.optionId}, "
			+ "#{entity.supplierId}, "
			+ "#{entity.optionGroup}, "
			+ "#{entity.optionName}, "
			+ "#{entity.optionValue})")
	void insert(@Param("entity") OptionEntity entity);
	
	/**
	 * optionのoptionIdがentity.optionIdと一致するデータを更新
	 * @param entity optionを更新するデータ
	 */
	@Update("UPDATE option SET "
			+ "optionName = #{entity.optionName}, "
			+ "optionValue = #{entity.optionValue} "
			+ "WHERE optionId = #{entity.optionId}")
	void update(@Param("entity") OptionEntity entity);
	
	/**
	 * オートインクリメント機能
	 * @return int 新規発行した設定ID 
	 */
	@Select("SELECT nextval('selectOptionId')")
	int newOptionId();
	
	/**
	 * optionのsupplierIdとoptionNameがentity.supplierIdとentity.optionNameと一致するデータを取得
	 * @param entity optionから取得するためのデータ
	 * @return OptionEntity 検索結果
	 */
	@Select("SELECT optionId, supplierId, optionGroup, optionName, optionValue FROM option "
			+ "WHERE supplierId = #{entity.supplierId} AND optionName = #{entity.optionName}")
	OptionEntity selectSupplierAndOptionName(@Param("entity")OptionEntity entity);
	
	/**
	 * optionからサプライヤーIDが一致するデータを取得
	 * @param supplierId サプライヤーID
	 * @return 検索結果
	 */
	@Select("SELECT * FROM option WHERE optiongroup = 'slack' and supplierid = #{supplierId} ORDER BY optionid")
	ArrayList<OptionEntity> select(@Param("supplierId")int supplierId);
}
