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
 * 消息生产者
 * @author 灵均
 *
 */
public class JMSProducer {
	
	private static final String USERNAME=ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEURL=ActiveMQConnection.DEFAULT_BROKER_URL;
	private static final int SENDNUM=10;
	
	public static void main(String []args) throws JMSException {
		
		ConnectionFactory connectionFactory;//连接工厂
		Connection connection;//连接
		Session session;//会话线程
		Destination destination;//消息目的地
		MessageProducer messageProducer;//消息生产者
		
		//实例化连接工厂
		connectionFactory=new ActiveMQConnectionFactory(JMSProducer.USERNAME,
				JMSProducer.PASSWORD,JMSProducer.BROKEURL);
		connection=connectionFactory.createConnection();
		connection.start();//启动连接
		
		session=connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		/**
		 * 第一个为是否有事务
		 */
		destination=session.createQueue("firstQueue");//创建消息队列
		messageProducer=session.createProducer(destination);//创建消息生产者
		sendMessage(session, messageProducer);
		session.commit();
		if(connection!=null) {
			connection.close();
			System.out.println("关闭连接！");
		}
	}
	//发送消息
	public static void sendMessage(Session session,MessageProducer messageProducer) throws JMSException {
		for(int i=0;i<JMSProducer.SENDNUM;i++) {
			TextMessage textMessage=session.createTextMessage("ActiveMQ message"+i);
			System.out.println("发送消息:"+"ActiveMQ message"+i);
			messageProducer.send(textMessage);
		}
		
	}
}
