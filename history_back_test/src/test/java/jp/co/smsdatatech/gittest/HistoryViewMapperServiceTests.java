package jp.co.smsdatatech.gittest;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.smsdatatech.gittest.entity.HistoryViewEntity;
import jp.co.smsdatatech.gittest.service.HistoryViewMapperService;

@AutoConfigureMockMvc
@SpringBootTest(classes = HistoryBackTestApplication.class)
@TestPropertySource(locations="/test.properties")
class HistoryViewMapperServiceTests {
	
	@Autowired
	private HistoryViewMapperService historyViewMapperService;

	/**
	 * returnテスト(selectメソッド)
	 * actualJsonStrとexpectedJsonStrが一致すれば成功
	 * actualJsonStr returnされたJSONデータ
	 * expectedJsonStr 期待されるJSONデータ
	 * @throws Exception 例外処理
	 */
	@Test
	public void checkContentsSelect() throws Exception {
		String checkItem = "none";
		String actualJsonStr = transformationJson(historyViewMapperService.select());
		String expectedJsonStr = transformationJson(generateExpected(checkItem));
		assertThat(actualJsonStr).isEqualTo(expectedJsonStr);
	}
	
	/**
	 * returnテスト(selectNameメソッド)
	 * actualJsonStrとexpectedJsonStrが一致すれば成功
	 * actualJsonStr returnされたJSONデータ
	 * expectedJsonStr 期待されるJSONデータ
	 * @throws Exception 例外処理
	 */
	@Test
	public void checkContentsSelectName() throws Exception {
		String checkItem = "name";
		String chooseConsumerFromName = "テスト1";
		String actualJsonStr = transformationJson(historyViewMapperService.selectName(chooseConsumerFromName));
		String expectedJsonStr = transformationJson(generateExpected(checkItem));
		assertThat(actualJsonStr).isEqualTo(expectedJsonStr);
	}
	
	/**
	 * returnテスト(selectCheckResultメソッド)
	 * actualJsonStrとexpectedJsonStrが一致すれば成功
	 * actualJsonStr returnされたJSONデータ
	 * expectedJsonStr 期待されるJSONデータ
	 * @throws Exception 例外処理
	 */
	@Test
	public void checkContentsSelectCheckResult() throws Exception {
		String checkItem = "checkResult";
		int chooseConsumerFromCheckResult = 1;
		String actualJsonStr = transformationJson(historyViewMapperService.selectCheckResult(chooseConsumerFromCheckResult));
		String expectedJsonStr = transformationJson(generateExpected(checkItem));
		assertThat(actualJsonStr).isEqualTo(expectedJsonStr);
	}
	
	/**
	 * returnテスト(selectEventメソッド)
	 * actualJsonStrとexpectedJsonStrが一致すれば成功
	 * actualJsonStr returnされたJSONデータ
	 * expectedJsonStr 期待されるJSONデータ
	 * @throws Exception 例外処理
	 */
	@Test
	public void checkContentsSelectEvent() throws Exception {
		String checkItem = "event";
		int eventId = 1;
		String actualJsonStr = transformationJson(historyViewMapperService.selectEvent(eventId));
		String expectedJsonStr = transformationJson(generateExpected(checkItem));
		assertThat(actualJsonStr).isEqualTo(expectedJsonStr);
	}

	/**
	 * テストデータを格納し、返却するメソッド
	 * 期待されるテストデータを格納し、返却します。
	 * @param checkItem 検索時に扱うデータのkey値
	 * @return historyViewEntityList 期待された値を格納したテストデータ
	 * @throws Exception 例外処理
	 */
	private ArrayList<HistoryViewEntity> generateExpected(String checkItem) throws Exception {
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		ArrayList<HistoryViewEntity> historyViewEntityList = new ArrayList<HistoryViewEntity>();
		HistoryViewEntity historyEntity1 = new HistoryViewEntity();
		historyEntity1.setHistoryId(new BigDecimal(1));
		historyEntity1.setEventId(new BigDecimal(1));
		historyEntity1.setSupplierId(new BigDecimal(1));
		historyEntity1.setConsumerId(new BigDecimal(1));
		historyEntity1.setConsumerName("テスト1");
		historyEntity1.setConsumerNameKana("テストイチ");
		historyEntity1.setCheckResult(1);
		historyEntity1.setCheckDate(sdformat.parse("2021/10/07 16:18:48"));
		historyEntity1.setPicture("img/test1.jpg");
		historyViewEntityList.add(historyEntity1);
		
		if(checkItem != "checkResult") {
			HistoryViewEntity historyEntity2 = new HistoryViewEntity();
			historyEntity2.setHistoryId(new BigDecimal(2));
			historyEntity2.setEventId(new BigDecimal(1));
			historyEntity2.setSupplierId(new BigDecimal(1));
			historyEntity2.setConsumerId(new BigDecimal(1));
			historyEntity2.setConsumerName("テスト1");
			historyEntity2.setConsumerNameKana("テストイチ");
			historyEntity2.setCheckResult(0);
			historyEntity2.setCheckDate(sdformat.parse("2021/10/07 16:18:48"));
			historyEntity2.setPicture("img/test1.jpg");
			historyViewEntityList.add(historyEntity2);
		}
		
		if(checkItem == "none" || checkItem == "name") {
			HistoryViewEntity historyEntity3 = new HistoryViewEntity();
			historyEntity3.setHistoryId(new BigDecimal(3));
			historyEntity3.setEventId(new BigDecimal(2));
			historyEntity3.setSupplierId(new BigDecimal(1));
			historyEntity3.setConsumerId(new BigDecimal(1));
			historyEntity3.setConsumerName("テスト1");
			historyEntity3.setConsumerNameKana("テストイチ");
			historyEntity3.setCheckResult(0);
			historyViewEntityList.add(historyEntity3);
			
			HistoryViewEntity historyEntity4 = new HistoryViewEntity();
			historyEntity4.setHistoryId(new BigDecimal(4));
			historyEntity4.setEventId(new BigDecimal(2));
			historyEntity4.setSupplierId(new BigDecimal(1));
			historyEntity4.setConsumerId(new BigDecimal(1));
			historyEntity4.setConsumerName("テスト1");
			historyEntity4.setConsumerNameKana("テストイチ");
			historyEntity4.setCheckResult(0);
			historyViewEntityList.add(historyEntity4);
		}

		return historyViewEntityList;
	}
	
	/**
	 * JSONデータに変換し、返却するメソッド
	 * historyViewEntityListをJSONデータに変換し、返却します。
	 * @param historyViewEntityList 履歴ビューエンティティリスト
	 * @return JsonStr historyViewEntityListをJSON型にしたデータ
	 * @throws Exception 例外処理
	 */
	private String transformationJson(ArrayList<HistoryViewEntity> historyViewEntityList) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String JsonStr = objectMapper.writeValueAsString(historyViewEntityList);
		return JsonStr;
	}
}
