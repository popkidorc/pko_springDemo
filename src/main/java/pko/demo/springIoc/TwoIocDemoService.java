package pko.demo.springIoc;

import java.util.List;

public class TwoIocDemoService{

	private String constructorArg;

	private String demoMessage;

	private List<String> demoMessages;

	public TwoIocDemoService(String constructorArg) {
		this.constructorArg = constructorArg;
		this.demoMessage = constructorArg;
	}

	public String getDemoMessage() {
		return demoMessage;
	}

	public void setDemoMessage(String demoMessage) {
		this.demoMessage = demoMessage;
	}

	public List<String> getDemoMessages() {
		return demoMessages;
	}

	public void setDemoMessages(List<String> demoMessages) {
		this.demoMessages = demoMessages;
	}

//	@Override
	public String doSomethings() {
		System.out.println("invoke==doSomethings");
		return "=============Two====IocDemoService doSomethings============"
				+ constructorArg + "====" + demoMessage + "====" + demoMessages;
	}
}
