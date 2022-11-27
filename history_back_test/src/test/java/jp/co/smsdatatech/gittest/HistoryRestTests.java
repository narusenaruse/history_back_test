package jp.co.smsdatatech.gittest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.smsdatatech.gittest.controller.rest.HistoryRest;
import jp.co.smsdatatech.gittest.custommatcher.FormMatcher;
import jp.co.smsdatatech.gittest.entity.EventEntity;
import jp.co.smsdatatech.gittest.entity.HistoryViewEntity;
import jp.co.smsdatatech.gittest.form.EventForm;
import jp.co.smsdatatech.gittest.form.HistoryForm;
import jp.co.smsdatatech.gittest.form.HistoryViewForm;

@AutoConfigureMockMvc
@SpringBootTest(classes = HistoryBackTestApplication.class)
@TestPropertySource(locations="/test.properties")
class HistoryRestTests {

	@Autowired
	private MockMvc mockMvc;

	private static HistoryRest historyRest;
	
	private ObjectMapper objectMapper = new ObjectMapper();

	@BeforeAll
	static void beforeAll() throws Exception {
		historyRest = new HistoryRest();
	}

	/**
	 * 接続テスト(initメソッド)
	 * HTTPステータスコードが200の時に成功
	 * @throws Exception 例外処理
	 */
	@Test
	public void initTest() throws Exception {
		mockMvc.perform(post("/history/init"))
		.andExpect(status().isOk());
	}

	/**
	 * 接続テスト(selectNameメソッド)
	 * HTTPステータスコードが200の時に成功
	 * @throws Exception 例外処理
	 */
	@Test
	public void selectNameTest() throws Exception {
		mockMvc.perform(post("/history/selectName").param("chooseConsumerFromName", "テスト"))
		.andExpect(status().isOk());
	}

	/**
	 * 接続テスト(selectCheckResultメソッド)
	 * HTTPステータスコードが200の時に成功
	 * @throws Exception 例外処理
	 */
	@Test
	public void selectCheckResultTest() throws Exception {
		mockMvc.perform(post("/history/selectCheckResult").param("chooseConsumerFromCheckResult", "1"))
		.andExpect(status().isOk());
	}

	/**
	 * 接続テスト(selectEventメソッド)
	 * HTTPステータスコードが200の時に成功
	 * @throws Exception 例外処理
	 */
	@Test
	public void selectEventTest() throws Exception {
		mockMvc.perform(post("/history/selectEvent").param("eventId", "1"))
		.andExpect(status().isOk());
	}

	/**
	 * returnテスト(initメソッド)
	 * actualとexpectedが一致すれば成功
	 * actual returnされたJSONデータをオブジェクトに直したデータ
	 * expected 期待されるデータ
	 * @throws Exception 例外処理
	 */
	@Test
	public void checkContentsInit() throws Exception {
		String checkItem = "none";
		String actualJsonStr = mockMvc.perform(post("/history/init"))
				.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8);
		
		HistoryForm actual = objectMapper.readValue(actualJsonStr, HistoryForm.class);
		HistoryForm expected = expected(checkItem);
		assertThat(actual, new FormMatcher(expected));
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
		String actualJsonStr = mockMvc.perform(post("/history/selectName").param("chooseConsumerFromName", "テスト1"))
				.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		String expectedJsonStr = expectedJson(checkItem);
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
		String actualJsonStr = mockMvc.perform(post("/history/selectCheckResult").param("chooseConsumerFromCheckResult", "1"))
				.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		String expectedJsonStr = expectedJson(checkItem);
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
		String actualJsonStr = mockMvc.perform(post("/history/selectEvent").param("eventId", "1"))
				.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		String expectedJsonStr = expectedJson(checkItem);
		assertThat(actualJsonStr).isEqualTo(expectedJsonStr);
	}

	/**
	 * returnテスト(dateFormatメソッド)
	 * actualとexpectedが一致すれば成功
	 * actual returnされたString型の日付データ
	 * expected 期待されるString型の日付データ
	 * @throws Exception 例外処理
	 */
	@Test
	public void dateFormatTest() throws Exception {
		String beforeDateFormat = "2021/01/01 12:00:00";

		Method dateFormat = historyRest.getClass().getDeclaredMethod("dateFormat", Date.class);
		dateFormat.setAccessible(true);

		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dateTime = sdformat.parse(beforeDateFormat);

		String actual = (String) dateFormat.invoke(historyRest, dateTime);
		String expected = "2021/01/01 12:00:00";
		assertEquals(expected, actual);
	}

	/**
	 * returnテスト(substitutionEventFormFromEventEntityメソッド)
	 * actualの中身とexpectedの中身が一致すれば成功
	 * actual returnされたArrayListの中のEventForm型のデータ
	 * expected 期待されるEventForm型のデータ
	 * @throws Exception 例外処理
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void substitutionEventFormFromEventEntityTest() throws Exception {
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		ArrayList<EventEntity> eventEntityList = new ArrayList<EventEntity>();
		EventEntity eventEntity = new EventEntity();
		
		eventEntity.setEventId(new BigDecimal(1));
		eventEntity.setSupplierId(new BigDecimal(1));
		eventEntity.setEventName("テストイベント1");
		eventEntity.setEventDate(sdformat.parse("2021/08/31 13:30:00"));
		eventEntity.setEventDateStart(sdformat.parse("2021/08/23 13:00:00"));
		eventEntityList.add(eventEntity);
		
		Method substitutionEventFormFromEventEntity = historyRest.getClass().getDeclaredMethod("substitutionEventFormFromEventEntity", ArrayList.class);
		substitutionEventFormFromEventEntity.setAccessible(true);
		ArrayList<EventForm> actualList = (ArrayList<EventForm>) substitutionEventFormFromEventEntity.invoke(historyRest, eventEntityList);
		EventForm actual = actualList.get(0);
		
		EventForm expected = new EventForm();
		expected.setEventId("1");
		expected.setSupplierId("1");
		expected.setEventName("テストイベント1");
		expected.setEventDate("2021/08/31 13:30:00");
		expected.setEventDateStart("2021/08/23 13:00:00");
		
		assertEquals(expected.getEventId(), actual.getEventId());
		assertEquals(expected.getSupplierId(), actual.getSupplierId());
		assertEquals(expected.getEventName(), actual.getEventName());
		assertEquals(expected.getEventDate(), actual.getEventDate());
		assertEquals(expected.getEventDateStart(), actual.getEventDateStart());
	}

	/**
	 * returnテスト(substitutionHistoryFormFromHistoryViewEntityメソッド)
	 * actualの中身とexpectedの中身が一致すれば成功
	 * actual returnされたArrayListの中のHistoryViewForm型データ
	 * expected 期待されるHistoryViewForm型のデータ
	 * @throws Exception 例外処理
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void substitutionHistoryFormFromHistoryViewEntityTest() throws Exception {
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		ArrayList<HistoryViewEntity> historyViewEntityList = new ArrayList<HistoryViewEntity>();
		HistoryViewEntity historyViewEntity = new HistoryViewEntity();
		
		historyViewEntity.setHistoryId(new BigDecimal(1));
		historyViewEntity.setEventId(new BigDecimal(1));
		historyViewEntity.setSupplierId(new BigDecimal(1));
		historyViewEntity.setConsumerId(new BigDecimal(1));
		historyViewEntity.setConsumerName("テスト1");
		historyViewEntity.setConsumerNameKana("テストイチ");
		historyViewEntity.setCheckResult(1);
		historyViewEntity.setCheckDate(sdformat.parse("2021/10/07 16:18:48"));
		historyViewEntity.setPicture("img/test1.jpg");
		historyViewEntityList.add(historyViewEntity);
		
		Method substitutionHistoryFormFromHistoryViewEntity = historyRest.getClass().getDeclaredMethod("substitutionHistoryFormFromHistoryViewEntity", ArrayList.class);
		substitutionHistoryFormFromHistoryViewEntity.setAccessible(true);
		ArrayList<HistoryViewForm> actualList = (ArrayList<HistoryViewForm>) substitutionHistoryFormFromHistoryViewEntity.invoke(historyRest, historyViewEntityList);
		HistoryViewForm actual = actualList.get(0);
		
		HistoryViewForm expected = new HistoryViewForm();
		expected.setHistoryId("1");
		expected.setEventId("1");
		expected.setSupplierId("1");
		expected.setConsumerId("1");
		expected.setConsumerName("テスト1");
		expected.setConsumerNameKana("テストイチ");
		expected.setCheckResult("1");
		expected.setCheckDate("2021/10/07 16:18:48");
		expected.setPicture("img/test1.jpg");
		
		assertEquals(expected.getHistoryId(), actual.getHistoryId());
		assertEquals(expected.getEventId(), actual.getEventId());
		assertEquals(expected.getSupplierId(), actual.getSupplierId());
		assertEquals(expected.getConsumerId(), actual.getConsumerId());
		assertEquals(expected.getConsumerName(), actual.getConsumerName());
		assertEquals(expected.getConsumerNameKana(), actual.getConsumerNameKana());
		assertEquals(expected.getCheckResult(), actual.getCheckResult());
		assertEquals(expected.getCheckDate(), actual.getCheckDate());
		assertEquals(expected.getPicture(), actual.getPicture());
	}
	
	/**
	 * テストデータを格納し、返却するメソッド
	 * 期待されるテストデータを格納し、JSON形式に変換後、返却します。
	 * @param checkItem 検索時に扱うデータのkey値
	 * @return expectedJsonStr 期待された値を格納したJSONテストデータ
	 * @throws Exception 例外処理
	 */
	private String expectedJson(String checkItem) throws Exception {
		ArrayList<HistoryViewForm> historyViewFormList = new ArrayList<HistoryViewForm>();
		HistoryViewForm historyForm1 = new HistoryViewForm();
		historyForm1.setHistoryId("1");
		historyForm1.setEventId("1");
		historyForm1.setSupplierId("1");
		historyForm1.setConsumerId("1");
		historyForm1.setConsumerName("テスト1");
		historyForm1.setConsumerNameKana("テストイチ");
		historyForm1.setCheckResult("1");
		historyForm1.setCheckDate("2021/10/07 16:18:48");
		historyForm1.setPicture("img/test1.jpg");
		historyViewFormList.add(historyForm1);
		
		if(checkItem != "checkResult") {
			HistoryViewForm historyForm2 = new HistoryViewForm();
			historyForm2.setHistoryId("2");
			historyForm2.setEventId("1");
			historyForm2.setSupplierId("1");
			historyForm2.setConsumerId("1");
			historyForm2.setConsumerName("テスト1");
			historyForm2.setConsumerNameKana("テストイチ");
			historyForm2.setCheckResult("0");
			historyForm2.setCheckDate("2021/10/07 16:18:48");
			historyForm2.setPicture("img/test1.jpg");
			historyViewFormList.add(historyForm2);
		}
		
		if(checkItem == "none" || checkItem == "name") {
			HistoryViewForm historyForm3 = new HistoryViewForm();
			historyForm3.setHistoryId("3");
			historyForm3.setEventId("2");
			historyForm3.setSupplierId("1");
			historyForm3.setConsumerId("1");
			historyForm3.setConsumerName("テスト1");
			historyForm3.setConsumerNameKana("テストイチ");
			historyForm3.setCheckResult("0");
			historyViewFormList.add(historyForm3);
			
			HistoryViewForm historyForm4 = new HistoryViewForm();
			historyForm4.setHistoryId("4");
			historyForm4.setEventId("2");
			historyForm4.setSupplierId("1");
			historyForm4.setConsumerId("1");
			historyForm4.setConsumerName("テスト1");
			historyForm4.setConsumerNameKana("テストイチ");
			historyForm4.setCheckResult("0");
			historyViewFormList.add(historyForm4);
		}

		ArrayList<EventForm> eventFormList = new ArrayList<EventForm>();
		EventForm eventForm1 = new EventForm();
		eventForm1.setEventId("1");
		eventForm1.setSupplierId("1");
		eventForm1.setEventName("テストイベント1");
		eventForm1.setEventDate("2021/08/31 13:30:00");
		eventForm1.setEventDateStart("2021/08/23 13:00:00");
		eventFormList.add(eventForm1);
		
		EventForm eventForm2 = new EventForm();
		eventForm2.setEventId("2");
		eventForm2.setSupplierId("1");
		eventForm2.setEventName("テストイベント2");
		eventForm2.setEventDate("2021/08/31 14:30:00");
		eventForm2.setEventDateStart("2021/08/23 14:00:00");
		eventFormList.add(eventForm2);

		HistoryForm expected = new HistoryForm();
		expected.setEventList(eventFormList);
		expected.setHistoryViewList(historyViewFormList);

		if(!checkItem.isEmpty()) {
			switch(checkItem) {
			case "name":
				expected.setChooseConsumerFromName(historyForm1.getConsumerName());
				break;

			case "checkResult":
				expected.setChooseConsumerFromCheckResult(historyForm1.getCheckResult());
				break;

			case "event":
				expected.setEventId(historyForm1.getEventId());
				expected.setEventName(eventForm1.getEventName());
				break;
			}
		}

		ObjectMapper objectMapper = new ObjectMapper();
		String expectedJsonStr = objectMapper.writeValueAsString(expected);
		return expectedJsonStr;
	}
	
	private HistoryForm expected(String checkItem) throws Exception {
		ArrayList<HistoryViewForm> historyViewFormList = new ArrayList<HistoryViewForm>();
		HistoryViewForm historyForm1 = new HistoryViewForm();
		historyForm1.setHistoryId("1");
		historyForm1.setEventId("1");
		historyForm1.setSupplierId("1");
		historyForm1.setConsumerId("1");
		historyForm1.setConsumerName("テスト1");
		historyForm1.setConsumerNameKana("テストイチ");
		historyForm1.setCheckResult("1");
		historyForm1.setCheckDate("2021/10/07 16:18:48");
		historyForm1.setPicture("img/test1.jpg");
		historyViewFormList.add(historyForm1);

		if(checkItem != "checkResult") {
			HistoryViewForm historyForm2 = new HistoryViewForm();
			historyForm2.setHistoryId("2");
			historyForm2.setEventId("1");
			historyForm2.setSupplierId("1");
			historyForm2.setConsumerId("1");
			historyForm2.setConsumerName("テスト1");
			historyForm2.setConsumerNameKana("テストイチ");
			historyForm2.setCheckResult("0");
			historyForm2.setCheckDate("2021/10/07 16:18:48");
			historyForm2.setPicture("img/test1.jpg");
			historyViewFormList.add(historyForm2);
		}

		if(checkItem == "none" || checkItem == "name") {
			HistoryViewForm historyForm3 = new HistoryViewForm();
			historyForm3.setHistoryId("3");
			historyForm3.setEventId("2");
			historyForm3.setSupplierId("1");
			historyForm3.setConsumerId("1");
			historyForm3.setConsumerName("テスト1");
			historyForm3.setConsumerNameKana("テストイチ");
			historyForm3.setCheckResult("0");
			historyViewFormList.add(historyForm3);

			HistoryViewForm historyForm4 = new HistoryViewForm();
			historyForm4.setHistoryId("4");
			historyForm4.setEventId("2");
			historyForm4.setSupplierId("1");
			historyForm4.setConsumerId("1");
			historyForm4.setConsumerName("テスト1");
			historyForm4.setConsumerNameKana("テストイチ");
			historyForm4.setCheckResult("0");
			historyViewFormList.add(historyForm4);
		}

		ArrayList<EventForm> eventFormList = new ArrayList<EventForm>();
		EventForm eventForm1 = new EventForm();
		eventForm1.setEventId("1");
		eventForm1.setSupplierId("1");
		eventForm1.setEventName("テストイベント1");
		eventForm1.setEventDate("2021/08/31 13:30:00");
		eventForm1.setEventDateStart("2021/08/23 13:00:00");
		eventFormList.add(eventForm1);

		EventForm eventForm2 = new EventForm();
		eventForm2.setEventId("2");
		eventForm2.setSupplierId("1");
		eventForm2.setEventName("テストイベント2");
		eventForm2.setEventDate("2021/08/31 14:30:00");
		eventForm2.setEventDateStart("2021/08/23 14:00:00");
		eventFormList.add(eventForm2);

		HistoryForm expected = new HistoryForm();
		expected.setEventList(eventFormList);
		expected.setHistoryViewList(historyViewFormList);

		if(!checkItem.isEmpty()) {
			switch(checkItem) {
			case "name":
				expected.setChooseConsumerFromName(historyForm1.getConsumerName());
				break;

			case "checkResult":
				expected.setChooseConsumerFromCheckResult(historyForm1.getCheckResult());
				break;

			case "event":
				expected.setEventId(historyForm1.getEventId());
				expected.setEventName(eventForm1.getEventName());
				break;
			}
		}
		return expected;
	}
}
