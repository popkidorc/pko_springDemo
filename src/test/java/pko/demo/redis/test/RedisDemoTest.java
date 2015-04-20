package pko.demo.redis.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisDemoTest {

	@Test
	public void doTest() {
		Jedis redis = new Jedis("localhost", 6379);// 连接redis

		// hset key field value将哈希表key中的域field的值设为value。
		redis.hset("yyweb", "music", "m.yy.com");
		redis.hset("yyweb", "mall", "mai.yy.com");
		redis.hset("yyweb", "duowan", "www.duowan.com");
		// 返回哈希表key中，一个或多个给定域的值。
		String mall = redis.hget("yyweb", "mall");
		System.out.println(mall);
		List list = redis.hmget("yyweb", "mall", "duowan");
		for (int i = 0; i < list.size(); i++) {
			System.out.println("yyweb===" + list.get(i));
		}

		// 同时将多个field - value(域-值)对设置到哈希表key中。
		Map map = new HashMap();
		map.put("uid", "001");
		map.put("username", "user");
		redis.hmset("hash", map);
		// 得到map下面的username的值
		System.out.println(redis.hget("hash", "username"));

		// HGETALL key返回哈希表key中，所有的域和值。
		Map<String, String> maps = redis.hgetAll("hash");
		for (Map.Entry entry : maps.entrySet()) {
			System.out.print(entry.getKey() + ":" + entry.getValue() + "\t");
		}
	}
}
