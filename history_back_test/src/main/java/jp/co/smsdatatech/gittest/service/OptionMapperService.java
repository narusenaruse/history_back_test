package jp.co.smsdatatech.gittest.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.smsdatatech.gittest.entity.OptionEntity;
import jp.co.smsdatatech.gittest.repository.OptionMapper;

/**
 *設定画面サービス
 */
@Service
public class OptionMapperService {

	@Autowired OptionMapper optionMapper;

	/**
	 * optionの登録及び更新機能
	 * optionから一致するサプライヤーのデータを抽出
	 * サプライヤーが設定名を持っているなら更新し、
	 * サプライヤーが設定名を持っていなければ新規登録する
	 * @param entityList
	 */
	public void option(ArrayList<OptionEntity> entityList) {

		for(OptionEntity entity : entityList) {

			OptionEntity chooseEntityFromName = optionMapper.selectSupplierAndOptionName(entity);

			if(!Objects.isNull(chooseEntityFromName)) {
				entity.setOptionId(chooseEntityFromName.getOptionId());
				optionMapper.update(entity);

			} else {
				BigDecimal optionId = new BigDecimal(newOptionId());
				entity.setOptionId(optionId);
				optionMapper.insert(entity);
			}
		}
	}

	/**
	 * オートインクリメント機能
	 * @return int 新規発行した設定ID 
	 */
	public int newOptionId() {
		return optionMapper.newOptionId();
	}

	/**
	 * optionからサプライヤーIDが一致するデータを取得
	 * @param supplierid サプライヤーID
	 * @return 検索結果
	 */
	public ArrayList<OptionEntity> select(int supplierId){
		return optionMapper.select(supplierId);
	}
}
