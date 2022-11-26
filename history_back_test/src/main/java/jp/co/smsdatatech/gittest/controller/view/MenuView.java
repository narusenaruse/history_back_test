//path: http://localhost:8080/menu

package jp.co.smsdatatech.gittest.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *メニュー画面ビュー
 */
@Controller
public class MenuView {
	
	/**
	 * メニュー画面へ遷移
	 * @return String
	 */
	@RequestMapping(value = "/menu")
	public String menu(){
		return "menu.html";
	}

	/**
	 * 履歴画面へ遷移
	 * @return String
	 */
	@RequestMapping(value = "/history")
	public String listView(){
		return "list.html";
	}
	
	/**
	 * 設定画面へ遷移
	 * @return String
	 */
	@RequestMapping(value= "/option")
	public String option() {
		return "testOption.html";
	}
	
	/**
	 * 分析画面へ遷移
	 * @return String
	 */
	@RequestMapping(value= "/analysis")
	public String analysis() {
		return "dashboard.html";
	}

}
