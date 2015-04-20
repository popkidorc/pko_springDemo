package pko.demo.springTransaction.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pko.demo.springTransaction.Foo;
import pko.demo.springTransaction.FooService;

public class TransactionDemoTest {

	@Test
	public void doTest() {
		System.out.println("====doTest====");
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"spring/ioc/applicationContext.xml");
		FooService fooService = (FooService) applicationContext
				.getBean("fooService");
		List<Foo> fooes = new ArrayList<Foo>();
		fooes.add(new Foo("6"));
		fooes.add(new Foo("6"));
		fooService.insertFoo(fooes);
	}
}
