package renxuan.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 
 * ��Ϣ������
 * @author ���
 *
 */
public class JMSProducer {
	
	private static final String USERNAME=ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEURL=ActiveMQConnection.DEFAULT_BROKER_URL;
	private static final int SENDNUM=10;
	
	public static void main(String []args) throws JMSException {
		
		ConnectionFactory connectionFactory;//���ӹ���
		Connection connection;//����
		Session session;//�Ự�߳�
		Destination destination;//��ϢĿ�ĵ�
		MessageProducer messageProducer;//��Ϣ������
		
		//ʵ�������ӹ���
		connectionFactory=new ActiveMQConnectionFactory(JMSProducer.USERNAME,
				JMSProducer.PASSWORD,JMSProducer.BROKEURL);
		connection=connectionFactory.createConnection();
		connection.start();//��������
		
		session=connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		/**
		 * ��һ��Ϊ�Ƿ�������
		 */
		destination=session.createQueue("firstQueue");//������Ϣ����
		messageProducer=session.createProducer(destination);//������Ϣ������
		sendMessage(session, messageProducer);
		session.commit();
		if(connection!=null) {
			connection.close();
			System.out.println("�ر����ӣ�");
		}
	}
	//������Ϣ
	public static void sendMessage(Session session,MessageProducer messageProducer) throws JMSException {
		for(int i=0;i<JMSProducer.SENDNUM;i++) {
			TextMessage textMessage=session.createTextMessage("ActiveMQ message"+i);
			System.out.println("������Ϣ:"+"ActiveMQ message"+i);
			messageProducer.send(textMessage);
		}
		
	}
}
