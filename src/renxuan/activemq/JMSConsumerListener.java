package renxuan.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSConsumerListener {
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static final int SENDNUM = 10;

	public static void main(String[] args) throws JMSException {

		ConnectionFactory connectionFactory;// ���ӹ���
		Connection connection;// ����
		Session session;// �Ự�߳�
		Destination destination;// ��ϢĿ�ĵ�
		MessageConsumer messageConsumer;
		// ʵ�������ӹ���
		connectionFactory = new ActiveMQConnectionFactory(JMSConsumerListener.USERNAME, JMSConsumerListener.PASSWORD,
				JMSConsumerListener.BROKEURL);
		connection = connectionFactory.createConnection();
		connection.start();// ��������
		session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);//����Ҫ������
		destination=session.createQueue("firstQueue");//����������Ϣ����
		messageConsumer=session.createConsumer(destination);//������Ϣ������
		messageConsumer.setMessageListener(new Listener());

	}
}
