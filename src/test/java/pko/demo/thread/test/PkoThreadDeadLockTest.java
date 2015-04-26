package pko.demo.thread.test;

import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;

import org.junit.Test;

public class PkoThreadDeadLockTest {
	static final ClassA classA = new ClassA();
	static final ClassB classB = new ClassB();

	@Test
	public void doTest() {
		// TODO Auto-generated method stub
		TestRunnable threada = new TestRunnable() {
			public void runTest() {
				classA.method1();
			}
		};

		TestRunnable threadb = new TestRunnable() {
			public void runTest() {
				classB.method2();
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
		// threada.start();
		// threadb.start();
	}
}

class ClassA {
	public static synchronized void method1() {
		ClassB.method2();
	}
}

class ClassB {
	public static synchronized void method2() {
		ClassA.method1();
	}
}