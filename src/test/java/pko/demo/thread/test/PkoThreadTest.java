package pko.demo.thread.test;

import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;

import org.junit.Test;

public class PkoThreadTest {

	class Accumulator extends TestRunnable {
		// int sum = 0;
		ThreadLocal threadLocal = new ThreadLocal();

		@Override
		public void runTest() throws Throwable {
			// TODO Auto-generated method stub
			// int sum = 0;
			for (int i = 1; i <= 10; i++) {
				if (threadLocal.get() == null) {
					threadLocal.set(0);
				}
				threadLocal.set((int) threadLocal.get() + i);
				// sum = sum + i;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
				System.out.println(Thread.currentThread().getName()
						+ "====sum====" + threadLocal.get());
			}
		}
	}

	@Test
	public void doTest() {
		// TODO Auto-generated method stub
		// Runnable accumelatora = new Accumulator();
		// Runnable accumelatorb=new Accumulator();

		TestRunnable threada = new Accumulator();
		TestRunnable threadb = new Accumulator();
		TestRunnable[] tcs = { threada, threada };

		MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(tcs);
		try {
			mttr.runTestRunnables(2 * 60 * 1000);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		// threada.start();
		// threadb.start();
	}
}
