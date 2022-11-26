package jp.co.smsdatatech.gittest.common.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AP用ロガー
 * リリース環境ではinfoのみ出力
 *
 */
public class AppLogger {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(AppLogger.class);

	/**
	 * 通常ログ出力
	 * @param msg
	 */
	public void info(String msg) {

		log.info(msg);
	}

	/**
	 * デバッグログ出力
	 * @param msg
	 */
	public void debug(String msg) {

		log.debug(msg);
	}

	/**
	 * エラーログ出力
	 * @param msg
	 */
	public void error(String msg) {

		log.error(msg);
	}

	/**
	 * エラーログ出力
	 * @param msg
	 * @param t
	 */
	public void error(String msg, Throwable t) {

		log.error(msg, t);
	}
}
