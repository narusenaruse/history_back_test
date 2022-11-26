package jp.co.smsdatatech.gittest;

import static org.hamcrest.MatcherAssert.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.TestPropertySource;

import jp.co.smsdatatech.gittest.custommatcher.ListMatcher;
import jp.co.smsdatatech.gittest.entity.HistoryViewEntity;
import jp.co.smsdatatech.gittest.repository.HistoryViewMapper;

@SpringBootTest(classes = HistoryBackTestApplication.class)
@TestPropertySource(locations = "/test.properties")
class HistoryViewMapperTests {

	@Autowired
	private HistoryViewMapper historyViewMapper;
	private ArrayList<HistoryViewEntity> expectedList = new ArrayList<HistoryViewEntity>();

	@BeforeEach
	void beforeEach(TestInfo testInfo) throws Exception {

		//csvデータのパス取得		
		String csvPath = ("/csv/" + testInfo.getDisplayName() + "Csv.csv").replace("()", "");
		ClassPathResource resource = new ClassPathResource(csvPath);
		Path path = Paths.get(resource.getURI());

		//csvデータ読み込み
		List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));

		//ヘッダー取得
		List<String> header = Arrays.asList(lines.get(0).split(",", -1));

		//String→Date用のフォーマット
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		//csv1行ごとにループ
		for (int i = 1; i < lines.size(); i++) {	

			//csv１行分のデータを分割し、splitへ格納
			List<String> split = Arrays.asList(lines.get(i).split(",", -1));

			//expectedListへ格納するクラスのインスタンス作成
			HistoryViewEntity historyViewEntity = new HistoryViewEntity();

			//headerの数だけループ
			for (int j = 0; j < header.size(); j++) {

				//historyViewEntityのプライベートフィールドを取得し、アクセス許可する(フィールド変数はheaderで指定)
				Field field = historyViewEntity.getClass().getDeclaredField(header.get(j));
				field.setAccessible(true);

				//splitで分けたデータを各プライベートフィールドに格納
				if(j < 4) {
					field.set(historyViewEntity, new BigDecimal(split.get(j)));
				}else if(j < 6) {
					field.set(historyViewEntity, split.get(j));
				}else if(j == 6) {
					field.set(historyViewEntity, Integer.parseInt(split.get(j)));
				}else if(j == 7 && !split.get(j).isEmpty()){
					field.set(historyViewEntity, sdformat.parse(split.get(j)));
				}else{
					if (!split.get(j).isEmpty()) {
						field.set(historyViewEntity, split.get(j));
					}
				}
			}

			//csvデータを１行文のデータをexpectedListに格納
			expectedList.add(historyViewEntity);
		}
	}

	/**
	 * returnテスト(selectメソッド)
	 * actualListとexpectedListが一致すれば成功
	 * actualList historyViewMapperから取得したHistoryViewEntity型のリスト
	 * expectedList 期待値を詰め込んだHistoryViewEntity型のリスト
	 * @throws Exception 例外処理
	 */
	@Test
	void historyViewMapperSelect() throws Exception {
		ArrayList<HistoryViewEntity> actualList = historyViewMapper.select();
		assertThat(actualList, new ListMatcher(expectedList));
	}
}
