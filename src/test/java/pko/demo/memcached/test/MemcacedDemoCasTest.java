package pko.demo.memcached.test;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;

public class MemcacedDemoCasTest {
	private static MemcachedClient client = null;

	static {
		try {
			client = new MemcachedClient(new InetSocketAddress("localhost",
					11211));
		} catch (IOException o) {
			o.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		// Firstly, the key should exist.
		// key is "number", value is Integer 1, 7845 is expire time
		client.set("number", 7845, 1);

		MemcacedDemoCasTest testObj = new MemcacedDemoCasTest();
		// start the multithread environment
		for (int i = 0; i < 10; i++) {
			testObj.new ThreadTest("Thread-" + (i + 1)).start();
		}
	}

	/**
	 * Each thread runs many times
	 */
	private class ThreadTest extends Thread {

		private MemcachedClient client = null;

		ThreadTest(String name) throws IOException {
			super(name);
			client = new MemcachedClient(new InetSocketAddress("localhost",
					11211));
		}

		public void run() {
			int i = 0;
			int success = 0;
			while (i < 10) {
				i++;
				CASValue<Object> uniqueValue = client.gets("number");
				CASResponse response = client.cas("number",
						uniqueValue.getCas(),
						(Integer) uniqueValue.getValue() + 1);

				if (response.toString().equals("OK")) {
					success++;
				}
				System.out.println(Thread.currentThread().getName() + " " + i
						+ " time " + " update oldValue : "
						+ uniqueValue.getValue() + " === "
						+ uniqueValue.getCas() + " , result : " + response);
			}

			if (success < 10) {
				System.out.println(Thread.currentThread().getName()
						+ " unsuccessful times : " + (10 - success));
			}
		}
	}

}
