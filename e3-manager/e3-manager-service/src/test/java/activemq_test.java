import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.junit.Test;

public class activemq_test {

	@Test
	public void demo1() throws Exception {
		ConnectionFactory connectionFactory = new org.apache.activemq.ActiveMQConnectionFactory("tcp://192.168.25.131:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("test1");
		MessageProducer producer = session.createProducer(queue);
		TextMessage message = new ActiveMQTextMessage();
		message.setText("hello activemq");
		producer.send(message);
		producer.close();
		session.close();
		connection.close();
	}
	
	@Test
	public void demo2() throws Exception {
		ConnectionFactory connectionFactory = new org.apache.activemq.ActiveMQConnectionFactory("tcp://192.168.25.131:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue queue = session.createQueue("test1");
		MessageConsumer consumer = session.createConsumer(queue);
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				TextMessage textMessage = (TextMessage) message;
				try {
					String text = textMessage.getText();
					System.out.println(text);
				} catch (JMSException e) {
					e.printStackTrace();
				}
				
			}
		});
		System.in.read();
		consumer.close();
		session.close();
		connection.close();
	}
}
