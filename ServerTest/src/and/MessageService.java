package and;

import java.util.HashMap;


public class MessageService {
	MessagePush messagePush;
	String message = "The Door Opend! Please Check your Room";
	public void push(String token) {
		if(messagePush ==null) {
			messagePush = new MessagePush();
			messagePush.setTo(token);
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("SmartStay", message);
		messagePush.setData(map);
		messagePush.push();
	}

}
