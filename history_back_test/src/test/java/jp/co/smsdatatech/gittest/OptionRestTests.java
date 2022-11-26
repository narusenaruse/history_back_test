package jp.co.smsdatatech.gittest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.smsdatatech.gittest.controller.rest.OptionRest;
import jp.co.smsdatatech.gittest.entity.OptionEntity;
import jp.co.smsdatatech.gittest.form.OptionForm;

@AutoConfigureMockMvc
@SpringBootTest(classes = HistoryBackTestApplication.class)
@TestPropertySource(locations="/test.properties")
class OptionRestTests {

	@Autowired
	private MockMvc mockMvc;

	private static OptionRest optionRest;

	@BeforeAll
	static void beforeAll() {
		optionRest = new OptionRest();
	}

	/**
	 * 接続テスト(initメソッド)
	 * HTTPステータスコードが200の時に成功
	 * @throws Exception 例外処理
	 */
	@Test
	public void initTest() throws Exception {
		mockMvc.perform(post("/option/slack/init").param("supplierId", "1"))
		.andExpect(status().isOk());
	}

	/**
	 * 接続テスト(cooperateメソッド)
	 * HTTPステータスコードが200の時に成功
	 * @throws Exception 例外処理
	 */
	@Test
	public void cooperateTest() throws Exception {
		mockMvc.perform(post("/option/slack/cooperate")
				.param("slackUrl", "test.com")
				.param("slackUserName", "テスト1")
				.param("slackMsg", "テストメッセージ"))
		.andExpect(status().isOk());
	}

	/**
	 * 入力チェックテスト:slackUrlが無い場合(cooperateメソッド)
	 * Validatedによるエラーが値が生じた際の入力チェックの処理をテストします。
	 * HTTPステータスコードが200の時かつBindingResultからメッセージを受け取れていれば成功
	 * @throws Exception 例外処理
	 */
	@Test
	public void slackUrlValidateCheck() throws Exception {
		String validateTarget = "url";
		String actualJsonStr = mockMvc.perform(post("/option/slack/cooperate")
				.param("slackUserName", "テスト1")
				.param("slackMsg", "テストメッセージ"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		String expectedJsonStr = validateCheckExpectedJson(validateTarget);

		assertEquals(expectedJsonStr, actualJsonStr);
	}

	/**
	 * 入力チェックテスト:slackUserNameが無い場合(cooperateメソッド)
	 * Validatedによるエラーが値が生じた際の入力チェックの処理をテストします。
	 * HTTPステータスコードが200の時かつBindingResultからメッセージを受け取れていれば成功
	 * @throws Exception 例外処理
	 */
	@Test
	public void slackUserNameValidateCheck() throws Exception {
		String validateTarget = "userName";
		String actualJsonStr = mockMvc.perform(post("/option/slack/cooperate")
				.param("slackUrl", "test.com")
				.param("slackMsg", "テストメッセージ"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		String expectedJsonStr = validateCheckExpectedJson(validateTarget);

		assertEquals(expectedJsonStr, actualJsonStr);
	}

	/**
	 * 入力チェックテスト:slackUrlとslackUserNameが無い場合(cooperateメソッド)
	 * Validatedによるエラーが値が生じた際の入力チェックの処理をテストします。
	 * HTTPステータスコードが200の時かつBindingResultからメッセージを受け取れていれば成功
	 * @throws Exception 例外処理
	 */
	@Test
	public void slackUrlAndSlackUserNameValidateCheck() throws Exception {
		String validateTarget = "url&userName";
		String actualJsonStr = mockMvc.perform(post("/option/slack/cooperate")
				.param("slackMsg", "テストメッセージ"))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		String expectedJsonStr = validateCheckExpectedJson(validateTarget);

		assertEquals(expectedJsonStr, actualJsonStr);
	}


	/**
	 * returnテスト(initメソッド)
	 * actualJsonStrとexpectedJsonStrが一致すれば成功
	 * actualJsonStr returnされたJSONデータ
	 * expectedJsonStr 期待されるJSONデータ
	 * @throws Exception 例外処理
	 */
	@Test
	public void checkContentsInit() throws Exception {
		String actualJsonStr = mockMvc.perform(post("/option/slack/init").param("supplierId", "1"))
				.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8);

		OptionForm optionForm = new OptionForm();
		optionForm.setSlackUrl("test.com");
		optionForm.setSlackUserName("テスト1");
		optionForm.setSlackMsg("テストメッセージ");

		ObjectMapper objectMapper = new ObjectMapper();
		String expectedJsonStr = objectMapper.writeValueAsString(optionForm);

		assertEquals(expectedJsonStr, actualJsonStr);
	}

	/**
	 * 更新処理テスト(cooperateメソッド)
	 * actualJsonStrとexpectedJsonStrが一致すれば成功
	 * actualJsonStr returnされたJSONデータ
	 * expectedJsonStr 期待されるJSONデータ
	 * @throws Exception 例外処理
	 */
	@Test
	public void checkContentsCooperate() throws Exception {
		String actualJsonStr = mockMvc.perform(post("/option/slack/cooperate")
				.param("slackUrl", "test.com")
				.param("slackUserName", "テスト1")
				.param("slackMsg", "テストメッセージ"))
				.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8);
		
		OptionForm optionForm = new OptionForm();
		ObjectMapper objectMapper = new ObjectMapper();
		optionForm.setSlackUrl("test.com");
		optionForm.setSlackUserName("テスト1");
		optionForm.setSlackMsg("テストメッセージ");
		optionForm.setMsg("登録が完了しました");
		String expectedJsonStr = objectMapper.writeValueAsString(optionForm);

		assertEquals(expectedJsonStr, actualJsonStr);
	}

	/**
	 * returnテスト(substitutionEntityFromFormメソッド)
	 * actualの中身とexpectedの中身が一致すれば成功
	 * actual returnされたOptionEntity型のデータ
	 * expected 期待されるOptionEntity型のデータ
	 * @throws Exception 例外処理
	 */
	@Test
	public void substitutionEntityFromFormTest() throws Exception {
		Method substitutionEntityFromForm = optionRest.getClass().getDeclaredMethod("substitutionEntityFromForm", BigDecimal.class, String.class, String.class, String.class);
		substitutionEntityFromForm.setAccessible(true);
		OptionEntity actual = (OptionEntity) substitutionEntityFromForm.invoke(optionRest, new BigDecimal(1), "slack", "slackUrl", "test.jpg");

		OptionEntity expected = new OptionEntity();
		expected.setSupplierId(new BigDecimal(1));
		expected.setOptionGroup("slack");
		expected.setOptionName("slackUrl");
		expected.setOptionValue("test.jpg");

		assertEquals(expected.getSupplierId(), actual.getSupplierId());
		assertEquals(expected.getOptionGroup(), actual.getOptionGroup());
		assertEquals(expected.getOptionName(), actual.getOptionName());
		assertEquals(expected.getOptionValue(), actual.getOptionValue());
	}

	/**
	 * 入力チェックにおける期待値を格納し、返却するメソッド
	 * 期待値を格納し、JSON形式に変換後、返却します。
	 * @param validateTarget 入力チェックする項目
	 * @return expectedJsonStr 期待された値を格納したJSONテストデータ
	 * @throws Exception 例外処理
	 */
	private String validateCheckExpectedJson(String validateTarget) throws Exception {
		String slackUrl = "test.com";
		String slackUserName = "テスト1";
		String slackMsg = "テストメッセージ";
		String urlMsg = "URLを入力してください。";
		String userNameMsg = "ユーザー名を入力してください。";

		OptionForm optionForm = new OptionForm();
		Map<String, Object> errorMap = new HashMap<String, Object>();
		switch(validateTarget) {
		case "url":
			errorMap.put("slackUrl", urlMsg);
			optionForm.setErrorMap(errorMap);
			optionForm.setSlackUserName(slackUserName);
			break;

		case "userName":
			errorMap.put("slackUserName", userNameMsg);
			optionForm.setErrorMap(errorMap);
			optionForm.setSlackUrl(slackUrl);
			break;

		case "url&userName":
			errorMap.put("slackUrl", urlMsg);
			errorMap.put("slackUserName", userNameMsg);
			optionForm.setErrorMap(errorMap);
			break;
		}
		optionForm.setSlackMsg(slackMsg);

		ObjectMapper objectMapper = new ObjectMapper();
		String expectedJsonStr = objectMapper.writeValueAsString(optionForm);

		return expectedJsonStr;
	}
}
