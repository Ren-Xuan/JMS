package renxuan.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Listener implements MessageListener{

	@Override
	public void onMessage(Message arg0) {
		try {
			System.out.println("�յ���Ϣ:"+((TextMessage)arg0).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

}
