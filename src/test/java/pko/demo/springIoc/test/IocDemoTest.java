package pko.demo.springIoc.test;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import pko.demo.springIoc.IIocDemoService;

public class IocDemoTest {

	// @Test
	public void doOriginalIOC() {
		System.out.println("====doOriginalIOC====");

		/***** 初始化IOC容器 start *****/
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		/***** 初始化IOC容器 end *****/

		/***** 解析加载、注册beanDefinition start *****/
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(
				beanFactory);
		ClassPathResource classPathResource = new ClassPathResource(
				"spring/ioc/applicationContext.xml");
		xmlBeanDefinitionReader.loadBeanDefinitions(classPathResource);
		/***** 解析加载beanDefinition end *****/

		/***** 注入bean start *****/
		IIocDemoService iocDemoService = (IIocDemoService) beanFactory
				.getBean("iocDemoService");
		/***** 注入bean end *****/

		iocDemoService.doSomethings();
	}

	@Test
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

		iocDemoService.doSomethings();
	}
}
