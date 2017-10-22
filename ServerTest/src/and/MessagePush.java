package and;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

class MessagePush {
	private String serverKey = "AAAA_nSHWN8:APA91bEl5MSNYLmyJ7h8BGPV-8MM9pXP0l-5gXvu5dwtN3SdxzuQc2msKE2viAfC3fTxRxRTWDxMpD57orCif0OMaJuZMPd3h66cTT708EZtvxEV5DCW4NaEqjP_jz3hYJD09bBT7vHJ";
	private static String TAG = "MESSAGEPUSH";
	private final String API_URL = "https://fcm.googleapis.com/fcm/send";
	private String FIREBASE_SERVER_KEY;
	private HashMap<String, String> attrMap = new HashMap<String, String>();
	private Map<String, Object> dataMap;
	
	public MessagePush() {
		this.FIREBASE_SERVER_KEY = serverKey;
	}
	public void push() {
		HttpURLConnection con = null;
		try {
			String json = toJson();
			System.out.println("JSON : " + json);
			
			String url = API_URL;
			URL obj = new URL(url);
			con = (HttpURLConnection)obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Authorization", "Key="+FIREBASE_SERVER_KEY);
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);
			
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(json);
			wr.flush();
			wr.close();
			System.out.println(con.getResponseCode());
			
		}catch(Exception ex) {
			
		}
	}
	public void setTo(String to) {
		attrMap.put("to",to);
	}
	public void setData(Map<String, Object> _dataMap) {
		dataMap = _dataMap;
	}
	private String toJson() {
		JSONObject obj = new JSONObject();
		for(String key : attrMap.keySet()) {
			String value = attrMap.get(key);
			obj.put(key, value);
		}
		obj.put("data", dataMap);
		return obj.toString();
	}
	
}