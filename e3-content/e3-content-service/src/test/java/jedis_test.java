import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.e3mall.common.jedis.JedisClient;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class jedis_test {
	
	@Test
	public void jedis_clustertest() {
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.25.130",7001));
		nodes.add(new HostAndPort("192.168.25.130",7002));
		nodes.add(new HostAndPort("192.168.25.130",7003));
		nodes.add(new HostAndPort("192.168.25.130",7004));
		nodes.add(new HostAndPort("192.168.25.130",7005));
		nodes.add(new HostAndPort("192.168.25.130",7006));
		JedisCluster jedisCluster = new JedisCluster(nodes);
		jedisCluster.set("testa", "jedis_test1");
		String string = jedisCluster.get("testa");
		System.out.println(string);
		jedisCluster.close();
	}
	
	@Test
	public void demo2() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		JedisClient jedis = applicationContext.getBean(JedisClient.class);
		jedis.set("kiki", "120");
		String string = jedis.get("kiki");
		System.out.println(string);
	}
}
