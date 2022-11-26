package jp.co.smsdatatech.gittest;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jp.co.smsdatatech.gittest.form.HistoryViewForm;

class HistoryViewFormTests {

	private static HistoryViewForm historyViewForm;
	
	/** 履歴ID:historyId */
	private String historyId = "1";

	/** イベントID:eventId */
	private String eventId = "1";

	/** サプライヤーID:supplierId */
	private String supplierId = "1";

	/** コンシューマーID:consumerId */
	private String consumerId = "1";

	/** 氏名:consumerName */
	private String consumerName = "name";

	/** 氏名カナ:consumerNameKana */
	private String consumerNameKana = "ネーム";

	/** 判定結果:checkResult */
	private String checkResult = "1";

	/** 認証時間:checkDate */
	private String checkDate = "YYYY/MM/dd";

	/** 顔写真:picture */
	private String picture = "test.jpg";

	@BeforeAll
	static void beforeAll() {
		historyViewForm = new HistoryViewForm();
	}

	/**
	 * GetHistoryIdテスト
	 * @throws Exception 例外処理
	 */
	@Test
	void testGetHistoryId() throws Exception{

		Field historyIdField = historyViewForm.getClass().getDeclaredField("historyId");
		historyIdField.setAccessible(true);
		historyIdField.set(historyViewForm, historyId);

		assertEquals(historyId, historyViewForm.getHistoryId());
	}

	/**
	 * SetHistoryIdテスト
	 * @throws Exception 例外処理
	 */
	@Test
	void testSetHistoryId() throws Exception{

		Field historyIdField = historyViewForm.getClass().getDeclaredField("historyId");
		historyIdField.setAccessible(true);
		historyViewForm.setHistoryId(historyId);

		assertEquals(historyId, (String)historyIdField.get(historyViewForm));
	}

	/**
	 * GetEventIdテスト
	 * @throws Exception 例外処理
	 */
	@Test
	void testGetEventId() throws Exception{

		Field eventIdField = historyViewForm.getClass().getDeclaredField("eventId");
		eventIdField.setAccessible(true);
		eventIdField.set(historyViewForm, eventId);

		assertEquals(eventId, historyViewForm.getEventId());
	}

	/**
	 * SetEventIdテスト
	 * @throws Exception 例外処理
	 */
	@Test
	void testSetEventId() throws Exception{

		Field eventIdField = historyViewForm.getClass().getDeclaredField("eventId");
		eventIdField.setAccessible(true);
		historyViewForm.setEventId(eventId);

		assertEquals(eventId, (String)eventIdField.get(historyViewForm));
	}

	/**
	 * GetSupplierId()テスト
	 * @throws Exception 例外処理
	 */
	@Test
	void testGetSupplierId() throws Exception{

		Field supplierIdField = historyViewForm.getClass().getDeclaredField("supplierId");
		supplierIdField.setAccessible(true);
		supplierIdField.set(historyViewForm, supplierId);

		assertEquals(supplierId, historyViewForm.getSupplierId());
	}

	/**
	 * SetSupplierId()テスト
	 * @throws Exception 例外処理
	 */
	@Test
	void testSetSupplierId() throws Exception{

		Field supplierIdField = historyViewForm.getClass().getDeclaredField("supplierId");
		supplierIdField.setAccessible(true);
		historyViewForm.setSupplierId(supplierId);

		assertEquals(supplierId,  (String)supplierIdField.get(historyViewForm));
	}

	/**
	 * GetConsumerIdテスト
	 * @throws Exception 例外処理
	 */
	@Test
	void testGetConsumerId() throws Exception{

		Field consumerIdField = historyViewForm.getClass().getDeclaredField("consumerId");
		consumerIdField.setAccessible(true);
		consumerIdField.set(historyViewForm, consumerId);

		assertEquals(consumerId, historyViewForm.getConsumerId());
	}

	/**
	 * SetConsumerIdテスト
	 * @throws Exception 例外処理
	 */
	@Test
	void testSetConsumerId() throws Exception{

		Field consumerIdField = historyViewForm.getClass().getDeclaredField("consumerId");
		consumerIdField.setAccessible(true);
		historyViewForm.setConsumerId(consumerId);

		assertEquals(consumerId,  (String)consumerIdField.get(historyViewForm));
	}

	/**
	 * GetConsumerNameテスト
	 * @throws Exception 例外処理
	 */
	@Test
	void testGetConsumerName() throws Exception{

		Field consumerNameField = historyViewForm.getClass().getDeclaredField("consumerName");
		consumerNameField.setAccessible(true);
		consumerNameField.set(historyViewForm, consumerName);

		assertEquals(consumerName, historyViewForm.getConsumerName());
	}

	/**
	 * SetConsumerNameテスト
	 * @throws Exception 例外処理
	 */
	@Test
	void testSetConsumerName() throws Exception{

		Field consumerNameField = historyViewForm.getClass().getDeclaredField("consumerName");
		consumerNameField.setAccessible(true);
		historyViewForm.setConsumerName(consumerName);

		assertEquals(consumerName, (String)consumerNameField.get(historyViewForm));
	}

	/**
	 * GetConsumerNameKanaテスト
	 * @throws Exception 例外処理
	 */
	@Test
	void testGetConsumerNameKana() throws Exception{

		Field consumerNameKanaField = historyViewForm.getClass().getDeclaredField("consumerNameKana");
		consumerNameKanaField.setAccessible(true);
		consumerNameKanaField.set(historyViewForm, consumerNameKana);

		assertEquals(consumerNameKana, historyViewForm.getConsumerNameKana());
	}

	/**
	 * SetConsumerNameKanaテスト
	 * @throws Exception 例外処理
	 */
	@Test
	void testSetConsumerNameKana() throws Exception{

		Field consumerNameKanaField = historyViewForm.getClass().getDeclaredField("consumerNameKana");
		consumerNameKanaField.setAccessible(true);
		historyViewForm.setConsumerNameKana(consumerNameKana);

		assertEquals(consumerNameKana, (String)consumerNameKanaField.get(historyViewForm));
	}

	/**
	 * GetCheckResultテスト
	 * @throws Exception 例外処理
	 */
	@Test
	void testGetCheckResult() throws Exception{

		Field checkResultField = historyViewForm.getClass().getDeclaredField("checkResult");
		checkResultField.setAccessible(true);
		checkResultField.set(historyViewForm, checkResult);

		assertEquals(checkResult, historyViewForm.getCheckResult());
	}

	/**
	 * SetCheckResultテスト
	 * @throws Exception 例外処理
	 */
	@Test
	void testSetCheckResult() throws Exception{

		Field checkResultField = historyViewForm.getClass().getDeclaredField("checkResult");
		checkResultField.setAccessible(true);
		historyViewForm.setCheckResult(checkResult);

		assertEquals(checkResult, (String)checkResultField.get(historyViewForm));
	}

	/**
	 * GetCheckDateテスト
	 * @throws Exception 例外処理
	 */
	@Test
	void testGetCheckDate() throws Exception{

		Field checkDateField = historyViewForm.getClass().getDeclaredField("checkDate");
		checkDateField.setAccessible(true);
		checkDateField.set(historyViewForm, checkDate);

		assertEquals(checkDate, historyViewForm.getCheckDate());
	}

	/**
	 * SetCheckDateテスト
	 * @throws Exception 例外処理
	 */
	@Test
	void testSetCheckDate() throws Exception{

		Field checkDateField = historyViewForm.getClass().getDeclaredField("checkDate");
		checkDateField.setAccessible(true);
		historyViewForm.setCheckDate(checkDate);

		assertEquals(checkDate, (String)checkDateField.get(historyViewForm));
	}

	/**
	 * GetPictureテスト
	 * @throws Exception 例外処理
	 */
	@Test
	void testGetPicture() throws Exception{

		Field pictureField = historyViewForm.getClass().getDeclaredField("picture");
		pictureField.setAccessible(true);
		pictureField.set(historyViewForm, picture);

		assertEquals(picture, historyViewForm.getPicture());
	}

	/**
	 * SetPictureテスト
	 * @throws Exception 例外処理
	 */
	@Test
	void testSetPicture() throws Exception{

		Field pictureField = historyViewForm.getClass().getDeclaredField("picture");
		pictureField.setAccessible(true);
		historyViewForm.setPicture(picture);

		assertEquals(picture, (String)pictureField.get(historyViewForm));
	}

}
