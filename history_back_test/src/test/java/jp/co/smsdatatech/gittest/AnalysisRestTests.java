package jp.co.smsdatatech.gittest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.smsdatatech.gittest.controller.rest.AnalysisRest;
import jp.co.smsdatatech.gittest.entity.EventEntity;
import jp.co.smsdatatech.gittest.form.AnalysisForm;
import jp.co.smsdatatech.gittest.form.EventForm;

@AutoConfigureMockMvc
@SpringBootTest(classes = HistoryBackTestApplication.class)
@TestPropertySource(locations="/test.properties")
class AnalysisRestTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	MessageSource messageSource;

	private static AnalysisRest analysisRest;
	
	
	
	@BeforeAll
	static void beforeAll() {
		analysisRest = new AnalysisRest();
	}
	
	/**
	 * 接続テスト(initメソッド)
	 * HTTPステータスコードが200の時に成功
	 * @throws Exception 例外処理
	 */
	@Test
	public void initTest() throws Exception {
		mockMvc.perform(post("/analysis/init"))
		.andExpect(status().isOk());
	}
	
	/**
	 * 接続テスト(authenticationHourメソッド)
	 * HTTPステータスコードが200の時に成功
	 * @throws Exception 例外処理
	 */
	@Test
	public void authenticationHourTest() throws Exception {
		mockMvc.perform(post("/analysis/init/authenticationHour").param("eventId", "1"))
		.andExpect(status().isOk());
	}
	
	/**
	 * 接続テスト(authenticationResultメソッド)
	 * HTTPステータスコードが200の時に成功
	 * @throws Exception 例外処理
	 */
	@Test
	public void authenticationResultTest() throws Exception {
		mockMvc.perform(post("/analysis/init/authenticationResult").param("eventId", "1"))
		.andExpect(status().isOk());
	}
	
	/**
	 * 接続テスト(receptionRateメソッド)
	 * HTTPステータスコードが200の時に成功
	 * @throws Exception 例外処理
	 */
	@Test
	public void receptionRateTest() throws Exception {
		mockMvc.perform(post("/analysis/init/receptionRate").param("eventId", "1"))
		.andExpect(status().isOk());
	}
	
	/**
	 * 接続テスト(maskRateメソッド)
	 * HTTPステータスコードが200の時に成功
	 * @throws Exception 例外処理
	 */
	@Test
	public void maskRateTest() throws Exception {
		mockMvc.perform(post("/analysis/init/maskRate").param("eventId", "1"))
		.andExpect(status().isOk());
	}
	
	/**
	 * 接続テスト(getTemperatureRateメソッド)
	 * HTTPステータスコードが200の時に成功
	 * @throws Exception 例外処理
	 */
	@Test
	public void getTemperatureRateTest() throws Exception {
		mockMvc.perform(post("/analysis/init/temperatureRate").param("eventId", "1"))
		.andExpect(status().isOk());
	}
	
	/**
	 * エラーチェックテスト(authenticationHourメソッド)
	 * returnされる値が無いことによるエラーチェックの処理をテストします。
	 * HTTPステータスコードが200の時かつエラーメッセージを受け取れていれば成功
	 * @throws Exception 例外処理
	 */
	@Test
	public void authenticationHourErrorCheck() throws Exception {
		String eventId = "999";
		String actualJsonStr = mockMvc.perform(post("/analysis/init/authenticationHour").param("eventId", eventId))
		.andExpect(status().isOk())
		.andReturn()
		.getResponse()
		.getContentAsString(StandardCharsets.UTF_8);
		
		String expectedJsonStr = errorCheckExpectedJson(eventId);
		assertEquals(expectedJsonStr, actualJsonStr);
	}
	
	/**
	 * エラーチェックテスト(authenticationHourメソッド)
	 * returnされる値が無いことによるエラーチェックの処理をテストします。
	 * HTTPステータスコードが200の時かつエラーメッセージを受け取れていれば成功
	 * @throws Exception 例外処理
	 */
	@Test
	public void authenticationHourErrorCheck2() throws Exception {
		String eventId = "2";
		String actualJsonStr = mockMvc.perform(post("/analysis/init/authenticationHour").param("eventId", eventId))
		.andExpect(status().isOk())
		.andReturn()
		.getResponse()
		.getContentAsString(StandardCharsets.UTF_8);
		
		String expectedJsonStr = errorCheckExpectedJson(eventId);
		assertEquals(expectedJsonStr, actualJsonStr);
	}
	
	/**
	 * エラーチェックテスト(authenticationResultメソッド)
	 * returnされる値が無いことによるエラーチェックの処理をテストします。
	 * HTTPステータスコードが200の時かつエラーメッセージを受け取れていれば成功
	 * @throws Exception 例外処理
	 */
	@Test
	public void authenticationResultErrorCheck() throws Exception {
		String eventId = "999";
		String actualJsonStr = mockMvc.perform(post("/analysis/init/authenticationResult").param("eventId", eventId))
		.andExpect(status().isOk())
		.andReturn()
		.getResponse()
		.getContentAsString(StandardCharsets.UTF_8);
		
		String expectedJsonStr = errorCheckExpectedJson(eventId);
		assertEquals(expectedJsonStr, actualJsonStr);
	}
	
	/**
	 * エラーチェックテスト(receptionRateメソッド)
	 * returnされる値が無いことによるエラーチェックの処理をテストします。
	 * HTTPステータスコードが200の時かつエラーメッセージを受け取れていれば成功
	 * @throws Exception 例外処理
	 */
	@Test
	public void receptionRateErrorCheck() throws Exception {
		String eventId = "999";
		String actualJsonStr = mockMvc.perform(post("/analysis/init/receptionRate").param("eventId", eventId))
		.andExpect(status().isOk())
		.andReturn()
		.getResponse()
		.getContentAsString(StandardCharsets.UTF_8);
		
		String expectedJsonStr = errorCheckExpectedJson(eventId);
		assertEquals(expectedJsonStr, actualJsonStr);
	}
	
	/**
	 * エラーチェックテスト(maskRateメソッド)
	 * returnされる値が無いことによるエラーチェックの処理をテストします。
	 * HTTPステータスコードが200の時かつエラーメッセージを受け取れていれば成功
	 * @throws Exception 例外処理
	 */
	@Test
	public void maskRateErrorCheck() throws Exception {
		String eventId = "999";
		String actualJsonStr = mockMvc.perform(post("/analysis/init/maskRate").param("eventId", eventId))
		.andExpect(status().isOk())
		.andReturn()
		.getResponse()
		.getContentAsString(StandardCharsets.UTF_8);
		
		String expectedJsonStr = errorCheckExpectedJson(eventId);
		assertEquals(expectedJsonStr, actualJsonStr);
	}
	
	/**
	 * エラーチェックテスト(getTemperatureRateメソッド)
	 * returnされる値が無いことによるエラーチェックの処理をテストします。
	 * HTTPステータスコードが200の時かつエラーメッセージを受け取れていれば成功
	 * @throws Exception 例外処理
	 */
	@Test
	public void getTemperatureRateErrorCheck() throws Exception {
		String eventId = "999";
		String actualJsonStr = mockMvc.perform(post("/analysis/init/temperatureRate").param("eventId", eventId))
		.andExpect(status().isOk())
		.andReturn()
		.getResponse()
		.getContentAsString(StandardCharsets.UTF_8);
		
		String expectedJsonStr = errorCheckExpectedJson(eventId);
		assertEquals(expectedJsonStr, actualJsonStr);
	}
	
	/**
	 * returnテスト(authenticationHourメソッド)
	 * actualJsonStrとexpectedJsonStrが一致すれば成功
	 * actualJsonStr returnされたJSONデータ
	 * expectedJsonStr 期待されるJSONデータ
	 * @throws Exception 例外処理
	 */
	@Test
	public void checkContentsAuthenticationHour() throws Exception {
		String actualJsonStr = mockMvc.perform(post("/analysis/init/authenticationHour").param("eventId", "1"))
		.andReturn()
		.getResponse()
		.getContentAsString(StandardCharsets.UTF_8);
		
		String expectedJsonStr = expectedJson("authenticationHour");
		assertEquals(expectedJsonStr, actualJsonStr);
	}
	
	/**
	 * returnテスト(authenticationResultメソッド)
	 * actualJsonStrとexpectedJsonStrが一致すれば成功
	 * actualJsonStr returnされたJSONデータ
	 * expectedJsonStr 期待されるJSONデータ
	 * @throws Exception 例外処理
	 */
	@Test
	public void checkContentsAuthenticationResult() throws Exception {
		String actualJsonStr = mockMvc.perform(post("/analysis/init/authenticationResult").param("eventId", "1"))
		.andReturn()
		.getResponse()
		.getContentAsString(StandardCharsets.UTF_8);
		
		String expectedJsonStr = expectedJson("authenticationResult");
		assertEquals(expectedJsonStr, actualJsonStr);
	}
	
	/**
	 * returnテスト(receptionRateメソッド)
	 * actualJsonStrとexpectedJsonStrが一致すれば成功
	 * actualJsonStr returnされたJSONデータ
	 * expectedJsonStr 期待されるJSONデータ
	 * @throws Exception 例外処理
	 */
	@Test
	public void checkContentsReceptionRate() throws Exception {
		String actualJsonStr = mockMvc.perform(post("/analysis/init/receptionRate").param("eventId", "1"))
		.andReturn()
		.getResponse()
		.getContentAsString(StandardCharsets.UTF_8);
		
		String expectedJsonStr = expectedJson("receptionRate");
		assertEquals(expectedJsonStr, actualJsonStr);
	}
	
	/**
	 * returnテスト(maskRateメソッド)
	 * actualJsonStrとexpectedJsonStrが一致すれば成功
	 * actualJsonStr returnされたJSONデータ
	 * expectedJsonStr 期待されるJSONデータ
	 * @throws Exception 例外処理
	 */
	@Test
	public void checkContentsMaskRate() throws Exception {
		String actualJsonStr = mockMvc.perform(post("/analysis/init/maskRate").param("eventId", "1"))
		.andReturn()
		.getResponse()
		.getContentAsString(StandardCharsets.UTF_8);
		
		String expectedJsonStr = expectedJson("maskRate");
		assertEquals(expectedJsonStr, actualJsonStr);
	}
	
	/**
	 * returnテスト(getTemperatureRateメソッド)
	 * actualJsonStrとexpectedJsonStrが一致すれば成功
	 * actualJsonStr returnされたJSONデータ
	 * expectedJsonStr 期待されるJSONデータ
	 * @throws Exception 例外処理
	 */
	@Test
	public void checkContentsGetTemperatureRate() throws Exception {
		String actualJsonStr = mockMvc.perform(post("/analysis/init/temperatureRate").param("eventId", "1"))
		.andReturn()
		.getResponse()
		.getContentAsString(StandardCharsets.UTF_8);
		
		String expectedJsonStr = expectedJson("getTemperatureRate");
		assertEquals(expectedJsonStr, actualJsonStr);
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
		
		Method substitutionEventFormFromEventEntity = analysisRest.getClass().getDeclaredMethod("substitutionEventFormFromEventEntity", ArrayList.class);
		substitutionEventFormFromEventEntity.setAccessible(true);
		ArrayList<EventForm> actualList = (ArrayList<EventForm>) substitutionEventFormFromEventEntity.invoke(analysisRest, eventEntityList);
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
	 * returnテスト(dateFormatメソッド)
	 * actualとexpectedが一致すれば成功
	 * actual returnされたString型の日付データ
	 * expected 期待されるString型の日付データ
	 * @throws Exception 例外処理
	 */
	@Test
	public void dateFormatTest() throws Exception {
		String beforeDateFormat = "2021/01/01 12:00:00";

		Method dateFormat = analysisRest.getClass().getDeclaredMethod("dateFormat", Date.class);
		dateFormat.setAccessible(true);

		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dateTime = sdformat.parse(beforeDateFormat);

		String actual = (String) dateFormat.invoke(analysisRest, dateTime);
		String expected = "2021/01/01 12:00:00";
		assertEquals(expected, actual);
	}
	
	/**
	 * returnテスト(dateFormatHourメソッド)
	 * actualとexpectedが一致すれば成功
	 * actual returnされたString型の日付データ
	 * expected 期待されるString型の日付データ
	 * @throws Exception 例外処理
	 */
	@Test
	public void dateFormatHourTest() throws Exception {
		String beforeDateFormat = "2021/01/01 12:00:00";

		Method dateFormat = analysisRest.getClass().getDeclaredMethod("dateFormatHour", Date.class);
		dateFormat.setAccessible(true);

		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dateTime = sdformat.parse(beforeDateFormat);

		String actual = (String) dateFormat.invoke(analysisRest, dateTime);
		String expected = "12";
		assertEquals(expected, actual);
	}
	
	/**
	 * エラーチェックにおける期待値を格納し、返却するメソッド
	 * 期待値を格納し、JSON形式に変換後、返却します。
	 * @param eventId イベントID
	 * @return expectedJsonStr 期待された値を格納したJSONテストデータ
	 * @throws Exception 例外処理
	 */
	private String errorCheckExpectedJson(String eventId) throws Exception {
		AnalysisForm analysisForm = new AnalysisForm();
		analysisForm.setErrorMsg(messageSource.getMessage("HIS.I.MESSAGE.00001", null, Locale.JAPAN));
		analysisForm.setEventId(eventId);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String expectedJsonStr = objectMapper.writeValueAsString(analysisForm);
		return expectedJsonStr;
	}
	
	/**
	 * テストデータを格納し、返却するメソッド
	 * 期待されるテストデータを格納し、JSON形式に変換後、返却します。
	 * @param methodName メソッドの分別をするために扱う値
	 * @return expectedJsonStr 期待された値を格納したJSONテストデータ
	 * @throws Exception 例外処理
	 */
	private String expectedJson(String methodName) throws Exception {
		AnalysisForm analysisForm = new AnalysisForm();
		analysisForm.setEventId("1");
		
		switch(methodName) {
		case "authenticationHour":
			ArrayList<String> okList = new ArrayList<String>();
			ArrayList<String> ngList = new ArrayList<String>();
			ArrayList<String> labelList = new ArrayList<String>();
			okList.add("1");
			ngList.add("2");
			labelList.add("16時");
			analysisForm.setOkList(okList);
			analysisForm.setNgList(ngList);
			analysisForm.setLabelList(labelList);
			break;
			
		case "authenticationResult":
			analysisForm.setOkPercentage("33");
			analysisForm.setNgPercentage("67");
			break;
			
		case "receptionRate":
			analysisForm.setYetPercentage("25");
			analysisForm.setNotYetPercentage("75");
			break;
			
		case "maskRate":
			analysisForm.setFacemaskOkPercentage("50");
			analysisForm.setFacemaskNgPercentage("50");
			break;
			
		case "getTemperatureRate":
			analysisForm.setTemperaturePercentage1("25");
			analysisForm.setTemperaturePercentage2("25");
			analysisForm.setTemperaturePercentage3("25");
			analysisForm.setTemperaturePercentage4("25");
			break;
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		String expectedJsonStr = objectMapper.writeValueAsString(analysisForm);
		return expectedJsonStr;
	}
}
