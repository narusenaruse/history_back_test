//package jp.co.smsdatatech.history.service;
//
//public class SlackService {
//
//	String webhookUrl = "https://hooks.slack.com/services/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"; 
//	String message = "test";  
//	requestWeb(setJsonObj(message), webhookUrl);
//
//	String setJsonObj(String text){
//		//JSONオブジェクトを生成する
//		JSONObject json = new JSONObject();
//
//		json.put("text",text);
//
//		//シリアライズ
//		return json.toString();
//	}
//
//	public void requestWeb(String json, String webhookUrl) {
//		appLogger.info("start requestWeb to slack");
//		try{
//			//送信先URLを指定してHttpコネクションを作成する
//			URL sendUrl = new URL(webhookUrl);    
//			HttpsURLConnection con = (HttpsURLConnection)sendUrl.openConnection();
//
//			//リクエストヘッダをセット
//			con.addRequestProperty("Content-Type", 
//					"application/JSON; charset=utf-8");
//			con.addRequestProperty("User-Agent", "DiscordBot");
//			//URLを出力利用に指示
//			con.setDoOutput(true);
//			//要求方法にはPOSTを指示
//			con.setRequestMethod("POST");
//
//			//要求を送信する
//			// POSTデータの長さを設定
//			con.setRequestProperty("Content-Length", String.valueOf(json.length()));
//			//リクエストのbodyにJSON文字列を書き込む
//			OutputStream stream = con.getOutputStream();
//			stream.write(json.getBytes("UTF-8"));
//			stream.flush();
//			stream.close();
//
//			// HTTPレスポンスコード
//			final int status = con.getResponseCode();
//			if (status != HttpsURLConnection.HTTP_OK 
//					&& status != HttpsURLConnection.HTTP_NO_CONTENT) {
//				//異常
//				System.out.println("error:" + status);
//			} 
//
//			//後始末 
//			con.disconnect();
//			appLogger.info("finish requestWeb to slack");
//
//		} catch(MalformedURLException e){
//			//例外
//			appLogger.error(e.toString());
//		} catch(IOException e){
//			//例外
//			appLogger.error(e.toString());
//		}
//	}
//}
