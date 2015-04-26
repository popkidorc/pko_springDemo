package pko.demo.thread.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;

public class PkoThreadSynchronizedTest {

	@Test
	public void doTest() {
		TestRunnable testRunnable = new ThreadBody(new ArrayList<Integer>());
		TestRunnable[] testRunnables = { testRunnable, testRunnable };

		MultiThreadedTestRunner multiThreadedTestRunner = new MultiThreadedTestRunner(
				testRunnables);
		try {
			multiThreadedTestRunner.runTestRunnables();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Thread threada = new Thread(threadBody, "ThreadA");
		// Thread threadb = new Thread(threadBody, "ThreadB");
		//
		// threada.start();
		// threadb.start();
	}

	class ThreadBody extends TestRunnable {
		List<Integer> intList = null;

		public ThreadBody(List<Integer> list) {
			intList = list;
		}

		@Override
		public void runTest() {
			// TODO Auto-generated method stub
			synchronized (this) {
				intList.add(new Integer(123));
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
				System.out.println(Thread.currentThread().getName() + "/t共有元素："
						+ intList.size());
			}
		}
	}
}
