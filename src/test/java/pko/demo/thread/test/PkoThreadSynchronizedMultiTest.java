package pko.demo.thread.test;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;

import org.junit.Test;

public class PkoThreadSynchronizedMultiTest {

	@Test
	public void doTest() {
		// TODO Auto-generated method stub
		final List<Integer> intList = new ArrayList<Integer>();
		TestRunnable threada = new TestRunnable() {
			@Override
			public void runTest() throws Throwable {
				// TODO Auto-generated method stub
				synchronized (intList) {// 同步
					intList.add(new Integer(0));
					try {
						Thread.sleep(600);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()
							+ "/t最后一个元素：" + intList.get(intList.size() - 1));
					System.out.println(Thread.currentThread().getName()
							+ "====" + intList);

				}
			}
		};
		TestRunnable threadb = new TestRunnable() {
			@Override
			public void runTest() throws Throwable {
				// TODO Auto-generated method stub
				synchronized (intList) {// 同步
					intList.add(new Integer(1));
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()
							+ "/t最后一个元素：" + intList.get(intList.size() - 1));
					System.out.println(Thread.currentThread().getName()
							+ "====" + intList);

				}
			}
		};
		TestRunnable[] testRunnables = { threada, threadb };
		MultiThreadedTestRunner multiThreadedTestRunner = new MultiThreadedTestRunner(
				testRunnables);
		try {
			multiThreadedTestRunner.runTestRunnables();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "====" + intList);
		// threada.start();
		// threadb.start();
	}
}
