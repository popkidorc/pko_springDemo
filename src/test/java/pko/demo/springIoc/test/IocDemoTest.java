package pko.demo.springIoc.test;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import pko.demo.springIoc.IIocDemoService;

public class IocDemoTest {

	@Test
	public void doOriginalIOC() {
		System.out.println("====doOriginalIOC====");

		/***** 初始化IOC容器 start *****/
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		/***** 初始化IOC容器 end *****/

		/***** 解析加载、注册beanDefinition 状态模式 start *****/
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(
				beanFactory);
		Resource resource = new ClassPathResource(
				"spring/ioc/applicationContext.xml");
		// Resource resource = new FileSystemResource(
		// "src/main/resources/spring/ioc/applicationContext.xml");
		xmlBeanDefinitionReader.loadBeanDefinitions(resource);
		/***** 解析加载beanDefinition end *****/

		/***** 注入bean start *****/
		IIocDemoService iocDemoService = (IIocDemoService) beanFactory
				.getBean("iocDemoService");
		/***** 注入bean end *****/

		System.out.println(iocDemoService.doSomethings());
	}

	// @Test
	public void doSimpleIOC() {
		System.out.println("====doSimpleIOC====");

		/***** 初始化IOC容器、解析加载、注册beanDefinition start *****/
		// ApplicationContext applicationContext = new
		// ClassPathXmlApplicationContext(
		// "spring/ioc/applicationContext.xml");
		// ApplicationContext applicationContext = new
		// FileSystemXmlApplicationContext(
		// "classpath:spring/ioc/applicationContext.xml");
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
				"src/main/resources/spring/ioc/applicationContext.xml");
		/***** 初始化IOC容器、解析加载、注册beanDefinition end *****/

		/***** 注入bean start *****/
		IIocDemoService iocDemoService = (IIocDemoService) applicationContext
				.getBean("iocDemoService");
		/***** 注入bean end *****/

		System.out.println(iocDemoService.doSomethings());
	}
}
