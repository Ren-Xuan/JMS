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

		ConnectionFactory connectionFactory;// 连接工厂
		Connection connection;// 连接
		Session session;// 会话线程
		Destination destination;// 消息目的地
		MessageConsumer messageConsumer;
		// 实例化连接工厂
		connectionFactory = new ActiveMQConnectionFactory(JMSConsumerListener.USERNAME, JMSConsumerListener.PASSWORD,
				JMSConsumerListener.BROKEURL);
		connection = connectionFactory.createConnection();
		connection.start();// 启动连接
		session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);//不需要加事务
		destination=session.createQueue("firstQueue");//创建连接消息队列
		messageConsumer=session.createConsumer(destination);//创建消息消费者
		messageConsumer.setMessageListener(new Listener());

	}
}
