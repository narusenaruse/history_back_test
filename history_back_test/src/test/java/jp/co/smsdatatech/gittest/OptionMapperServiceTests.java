package jp.co.smsdatatech.gittest;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.smsdatatech.gittest.entity.OptionEntity;
import jp.co.smsdatatech.gittest.service.OptionMapperService;

@AutoConfigureMockMvc
@SpringBootTest(classes = HistoryBackTestApplication.class)
@TestPropertySource(locations="/test.properties")
class OptionMapperServiceTests {

	@Autowired OptionMapperService optionMapperService;

	/**
	 * returnテスト(selectメソッド)
	 * actualJsonStrとexpectedJsonStrが一致すれば成功
	 * actualJsonStr returnされたJSONデータ
	 * expectedJsonStr 期待されるJSONデータ
	 * @throws Exception 例外処理
	 */
	@Test
	public void checkContentsSelect() throws Exception {
		int supplierId = 1;
		String actualJsonStr = transformationJson(optionMapperService.select(supplierId));
		String expectedJsonStr = transformationJson(generateExpected(supplierId));
		assertThat(actualJsonStr).isEqualTo(expectedJsonStr);
	}

	/**
	 * オートインクリメントテスト(newOptionIdメソッド)
	 * actualとexpectedが一致すれば成功
	 * actual シーケンスのnextvalで発行される値
	 * expected 期待される値
	 * @throws Exception 例外処理
	 */
	@Test
	public void NewOptionIdTest() throws Exception {
		int actual = optionMapperService.newOptionId();
		int expected = 3;
		assertThat(actual).isEqualTo(expected);
	}

	/**
	 * 更新処理テスト(optionメソッド)
	 * actualJsonStrとexpectedJsonStrが一致すれば成功
	 * actualJsonStr update後のJSONデータ
	 * expectedJsonStr 期待されるJSONデータ
	 * @throws Exception 例外処理
	 */
	@Test
	public void optionUpdateTest() throws Exception {
		int supplierId = 1;
		optionMapperService.option(generateExpected(supplierId));
		String actualJsonStr = transformationJson(optionMapperService.select(supplierId));
		String expectedJsonStr = transformationJson(generateExpected(supplierId));
		assertThat(actualJsonStr).isEqualTo(expectedJsonStr);
	}

	/**
	 * 挿入処理テスト(optionメソッド)
	 * actualJsonStrとexpectedJsonStrが一致すれば成功
	 * actualJsonStr insertされたJSONデータ
	 * expectedJsonStr 期待されるJSONデータ
	 * @throws Exception 例外処理
	 */
	@Test
	public void optionInsertTest() throws Exception {
		int supplierId = 2;
		optionMapperService.option(generateExpected(supplierId));
		String actualJsonStr = transformationJson(optionMapperService.select(supplierId));
		String expectedJsonStr = transformationJson(generateExpected(supplierId));
		assertThat(actualJsonStr).isEqualTo(expectedJsonStr);
	}

	/**
	 * テストデータを格納し、返却するメソッド
	 * 期待されるテストデータを格納し、返却します。
	 * @param supplierId サプライヤーID
	 * @return optionEntityList 期待された値を格納したテストデータ
	 * @throws Exception 例外処理
	 */
	private ArrayList<OptionEntity> generateExpected(int supplierId){
		ArrayList<OptionEntity> optionEntityList = new ArrayList<OptionEntity>();
		OptionEntity optionEntity1 = new OptionEntity();
		optionEntity1.setOptionId(new BigDecimal((supplierId - 1) * 3 + 1));
		optionEntity1.setSupplierId(new BigDecimal(supplierId));
		optionEntity1.setOptionGroup("slack");
		optionEntity1.setOptionName("slackUrl");
		optionEntity1.setOptionValue("test.com");
		optionEntityList.add(optionEntity1);

		OptionEntity optionEntity2 = new OptionEntity();
		optionEntity2.setOptionId(new BigDecimal((supplierId - 1) * 3 + 2));
		optionEntity2.setSupplierId(new BigDecimal(supplierId));
		optionEntity2.setOptionGroup("slack");
		optionEntity2.setOptionName("slackUserName");
		optionEntity2.setOptionValue("テスト1");
		optionEntityList.add(optionEntity2);

		OptionEntity optionEntity3 = new OptionEntity();
		optionEntity3.setOptionId(new BigDecimal((supplierId - 1) * 3 + 3));
		optionEntity3.setSupplierId(new BigDecimal(supplierId));
		optionEntity3.setOptionGroup("slack");
		optionEntity3.setOptionName("slackMsg");
		optionEntity3.setOptionValue("テストメッセージ");
		optionEntityList.add(optionEntity3);
		return optionEntityList;
	}

	/**
	 * JSONデータに変換し、返却するメソッド
	 * optionEntityListをJSONデータに変換し、返却します。
	 * @param optionEntityList 分析ビューエンティティリスト
	 * @return JsonStr optionEntityListをJSON型にしたデータ
	 * @throws Exception 例外処理
	 */
	private String transformationJson(ArrayList<OptionEntity> optionEntityList) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String JsonStr = objectMapper.writeValueAsString(optionEntityList);
		return JsonStr;
	}
}
