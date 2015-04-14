package pko.demo.springIoc.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import pko.demo.springIoc.IIocDemoService;

public class IocDemoServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/***** 获取IOC容器 start *****/
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
		/***** 获取IOC容器 end *****/

		/***** 注入bean start *****/
		IIocDemoService iocDemoService = (IIocDemoService) applicationContext
				.getBean("iocDemoService");
		/***** 注入bean end *****/
		// 设置响应内容类型
		response.setContentType("text/html");

		// 写出相应信息
		PrintWriter out = response.getWriter();
		out.println("<h1>" + iocDemoService.doSomethings() + "</h1>");
	}
}
