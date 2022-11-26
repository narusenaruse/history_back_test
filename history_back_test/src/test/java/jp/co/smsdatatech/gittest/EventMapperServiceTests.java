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

import jp.co.smsdatatech.gittest.entity.EventEntity;
import jp.co.smsdatatech.gittest.service.EventMapperService;

@AutoConfigureMockMvc
@SpringBootTest(classes = HistoryBackTestApplication.class)
@TestPropertySource(locations="/test.properties")
class EventMapperServiceTests {
	
	@Autowired
	private EventMapperService eventMapperService;

	/**
	 * returnテスト(selectメソッド)
	 * actualJsonStrとexpectedJsonStrが一致すれば成功
	 * actualJsonStr returnされたJSONデータ
	 * expectedJsonStr 期待されるJSONデータ
	 * @throws Exception 例外処理
	 */
	@Test
	public void checkContentsSelect() throws Exception {
		String actualJsonStr = transformationJson(eventMapperService.select());
		String expectedJsonStr = transformationJson(generateExpected());
		assertThat(actualJsonStr).isEqualTo(expectedJsonStr);
	}
	
	/**
	 * returnテスト(selectNameメソッド)
	 * actualとexpectedが一致すれば成功
	 * actual returnされたデータ
	 * expected 期待されるデータ
	 * @throws Exception 例外処理
	 */
	@Test
	public void checkContentsSelectName() throws Exception {
		int eventId = 1;
		String actual = eventMapperService.selectName(eventId);
		String expected = "テストイベント1";
		assertThat(actual).isEqualTo(expected);
	}
	
	/**
	 * テストデータを格納し、返却するメソッド
	 * 期待されるテストデータを格納し、返却します。
	 * @return analysisViewEntityList 期待された値を格納したテストデータ
	 * @throws Exception 例外処理
	 */
	private ArrayList<EventEntity> generateExpected() throws Exception {
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		ArrayList<EventEntity> eventEntityList = new ArrayList<EventEntity>();
		EventEntity eventEntity1 = new EventEntity();
		eventEntity1.setEventId(new BigDecimal(1));
		eventEntity1.setSupplierId(new BigDecimal(1));
		eventEntity1.setEventName("テストイベント1");
		eventEntity1.setEventDate(sdformat.parse("2021/08/31 13:30:00"));
		eventEntity1.setEventDateStart(sdformat.parse("2021/08/23 13:00:00"));
		eventEntityList.add(eventEntity1);
		
		EventEntity eventEntity2 = new EventEntity();
		eventEntity2.setEventId(new BigDecimal(2));
		eventEntity2.setSupplierId(new BigDecimal(1));
		eventEntity2.setEventName("テストイベント2");
		eventEntity2.setEventDate(sdformat.parse("2021/08/31 14:30:00"));
		eventEntity2.setEventDateStart(sdformat.parse("2021/08/23 14:00:00"));
		eventEntityList.add(eventEntity2);
		
		return eventEntityList;
	}
	
	/**
	 * JSONデータに変換し、返却するメソッド
	 * eventEntityListをJSONデータに変換し、返却します。
	 * @param eventEntityList 分析ビューエンティティリスト
	 * @return JsonStr eventEntityListをJSON型にしたデータ
	 * @throws Exception 例外処理
	 */
	private String transformationJson(ArrayList<EventEntity> eventEntityList) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String JsonStr = objectMapper.writeValueAsString(eventEntityList);
		return JsonStr;
	}

}
