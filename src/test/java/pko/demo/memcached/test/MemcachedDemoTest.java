package pko.demo.memcached.test;

import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.MemcachedClient;

import org.junit.Test;

import pko.demo.springIoc.OneIocDemoService;

public class MemcachedDemoTest {

	@Test
	public void doTest() {
		// Get a memcached client connected to several servers
		// over the binary protocol
		try {
			MemcachedClient c = new MemcachedClient(
					new BinaryConnectionFactory(),
					AddrUtil.getAddresses("localhost:11211"));
			c.set("someKey", 5, "ttt");
			// Try to get a value, for up to 5 seconds, and cancel if it
			// doesn't return
			Object myObj = null;
			Future<Object> f = c.asyncGet("someKey");
			try {
				myObj = f.get(5, TimeUnit.SECONDS);
				System.out
						.println(myObj + "=====" + c.gets("someKey").getCas());
				// throws expecting InterruptedException, ExecutionException
				// or TimeoutException
			} catch (Exception e) {
				// Since we don't need this, go ahead and cancel the operation.
				// This is not strictly necessary, but it'll save some work on
				// the server. It is okay to cancel it if running.
				f.cancel(true);
				// Do other timeout related stuff
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
