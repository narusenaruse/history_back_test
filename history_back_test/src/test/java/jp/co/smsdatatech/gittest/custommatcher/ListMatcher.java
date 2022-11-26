package jp.co.smsdatatech.gittest.custommatcher;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ListMatcher extends TypeSafeMatcher<List<?>> {

	List<?> actualList;
	List<?> expectedList;

	/**
	 * コンストラクタ
	 * @param expectedList 期待値
	 */
	public ListMatcher(List<?> expectedList) {
		this.expectedList = expectedList;
	}

	/**
	 * Listを検証します。
	 * Listの中身は、メモリ比較でないものとList<独自クラス>であれば検証可能。
	 */
	@Override
	protected boolean matchesSafely(List<?> actualList) {
		this.actualList = actualList;
		boolean succeedFlg = true;
		
		//サイズチェック
		if(actualList.size() != expectedList.size()) {
			succeedFlg = false;
			return succeedFlg;
		}
		//expectedListのサイズだけループ
		for(int i = 0; i < expectedList.size(); i++) {

			//オブジェクトの各値の取得
			List<Object> actualValueList = getObjectValue(actualList.get(i));
			List<Object> expectedValueList = getObjectValue(expectedList.get(i));
			
			//サイズチェック
			if(actualValueList.size() != expectedValueList.size()) {
				succeedFlg = false;
				return succeedFlg;
			}

			//valueListのサイズだけループ
			for(int j = 0; j < expectedValueList.size(); j++) {

				//nullチェック
				if(!Objects.isNull(expectedValueList.get(j))) {

					//期待値と実際値を比較し、間違っている場合にtrue
					if(!actualValueList.get(j).equals(expectedValueList.get(j))) {
						succeedFlg = succeedCheck(actualValueList.get(j), expectedValueList.get(j));
					}
					//succeedCheckでfalseが返却された場合、即座にfalseを返却する
					if(succeedFlg == false) {
						return succeedFlg;
					}

				} else {
					//expectedValueList.get(j)がnullでactualValueList.get(j)に値が入っている場合、即座にfalseを返却する
					if(!Objects.isNull(actualValueList.get(j))) {
						succeedFlg = false;
						return succeedFlg;
					}
				}
			}
		}
		return succeedFlg;
	}

	/**
	 * false返却時のメッセージ
	 */
	@Override
	public void describeTo(Description description) {
		description.appendText("Does not match to actual");
	}

	/**
	 * オブジェクトの中にあるオブジェクトリストの検証をします。
	 * @param actual 実際値
	 * @param expected 期待値
	 * @return succeedFlg true又はfalseを返却
	 */
	@SuppressWarnings("unchecked")
	private boolean succeedCheck(Object actual, Object expected) {
		boolean succeedFlg = true;

		//メモリアクセスの検証になっていない場合、succeedFlgをfalseにして、返却する。
		if(!expected.toString().startsWith("[jp.co.smsdatatech")) {
			succeedFlg = false;

		} else {
			//object→arrayListへキャスト
			List<Object> actualList = (ArrayList<Object>) actual;
			List<Object> expectedList = (ArrayList<Object>) expected;
			
			//サイズチェック
			if(actualList.size() != expectedList.size()) {
				succeedFlg = false;
				return succeedFlg;
			}

			//expectedListのサイズだけループ
			for(int i = 0; i < expectedList.size(); i++) {

				//オブジェクトの各値の取得
				List<Object> actualValueList = getObjectValue(actualList.get(i));
				List<Object> expectedValueList = getObjectValue(expectedList.get(i));
				
				//サイズチェック
				if(actualValueList.size() != expectedValueList.size()) {
					succeedFlg = false;
					return succeedFlg;
				}

				//valueListのサイズだけループ
				for(int j = 0; j < expectedValueList.size(); j++) {

					//nullチェック
					if(!Objects.isNull(expectedValueList.get(j))) {

						//期待値と実際値を比較し、間違っている場合にtrue
						if(!actualValueList.get(j).equals(expectedValueList.get(j))) {
							succeedFlg = succeedCheck(actualValueList.get(j), expectedValueList.get(j));
						}
						//succeedCheckでfalseが返却された場合、即座にfalseを返却する
						if(succeedFlg == false) {
							return succeedFlg;
						}
					
					} else {
						//expectedValueList.get(j)がnullでactualValueList.get(j)に値が入っている場合、即座にfalseを返却する
						if(!Objects.isNull(actualValueList.get(j))) {
							succeedFlg = false;
							return succeedFlg;
						}
					}
				}
			}
		}
		return succeedFlg;
	}

	/**
	 * オブジェクトのフィールドの値をリストに格納し、返却します。
	 * @param object オブジェクト
	 * @return valueList オブジェクトのフィールドの値をリストに格納したもの
	 */
	private List<Object> getObjectValue(Object object){
		List<Object> valueList = new ArrayList<Object>();

		for(Field field : object.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				Object value = field.get(object);
				valueList.add(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return valueList;
	}
}