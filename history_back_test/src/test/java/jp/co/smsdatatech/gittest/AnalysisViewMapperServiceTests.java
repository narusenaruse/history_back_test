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

import jp.co.smsdatatech.gittest.entity.AnalysisViewEntity;
import jp.co.smsdatatech.gittest.service.AnalysisViewMapperService;

@AutoConfigureMockMvc
@SpringBootTest(classes = HistoryBackTestApplication.class)
@TestPropertySource(locations="/test.properties")
class AnalysisViewMapperServiceTests {

	@Autowired
	private AnalysisViewMapperService analysisViewMapperService;
	
	/**
	 * returnテスト(selectEventメソッド)
	 * actualJsonStrとexpectedJsonStrが一致すれば成功
	 * actualJsonStr returnされたJSONデータ
	 * expectedJsonStr 期待されるJSONデータ
	 * @throws Exception 例外処理
	 */
	@Test
	public void checkContentsSelectEvent() throws Exception {
		int eventId = 2;
		String actualJsonStr = transformationJson(analysisViewMapperService.selectEvent(eventId));
		String expectedJsonStr = transformationJson(generateExpected());
		assertThat(actualJsonStr).isEqualTo(expectedJsonStr);
	}
	
	/**
	 * テストデータを格納し、返却するメソッド
	 * 期待されるテストデータを格納し、返却します。
	 * @return analysisViewEntityList 期待された値を格納したテストデータ
	 * @throws Exception 例外処理
	 */
	private ArrayList<AnalysisViewEntity> generateExpected(){
		ArrayList<AnalysisViewEntity> analysisViewEntityList = new ArrayList<AnalysisViewEntity>();
		AnalysisViewEntity analysisViewEntity1 = new AnalysisViewEntity();
		analysisViewEntity1.setEventId(new BigDecimal(2));
		analysisViewEntity1.setSupplierId(new BigDecimal(1));
		analysisViewEntity1.setConsumerId(new BigDecimal(1));
		analysisViewEntity1.setCheckResult(0);
		analysisViewEntity1.setFaceMaskType(0);
		analysisViewEntity1.setTemperature(35.5);
		analysisViewEntityList.add(analysisViewEntity1);
		
		AnalysisViewEntity analysisViewEntity2 = new AnalysisViewEntity();
		analysisViewEntity2.setEventId(new BigDecimal(2));
		analysisViewEntity2.setSupplierId(new BigDecimal(1));
		analysisViewEntity2.setConsumerId(new BigDecimal(1));
		analysisViewEntity2.setCheckResult(0);
		analysisViewEntity2.setFaceMaskType(0);
		analysisViewEntity2.setTemperature(35.5);
		analysisViewEntityList.add(analysisViewEntity2);
		
		return analysisViewEntityList;
	}
	
	/**
	 * JSONデータに変換し、返却するメソッド
	 * analysisViewEntityListをJSONデータに変換し、返却します。
	 * @param analysisViewEntityList 分析ビューエンティティリスト
	 * @return JsonStr analysisViewEntityListをJSON型にしたデータ
	 * @throws Exception 例外処理
	 */
	private String transformationJson(ArrayList<AnalysisViewEntity> analysisViewEntityList) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String JsonStr = objectMapper.writeValueAsString(analysisViewEntityList);
		return JsonStr;
	}

}
