package pko.demo.springIoc;

public class OneIocDemoService implements IIocDemoService {

	@Override
	public String getDemoMessage() {
		return "";
	}

	@Override
	public String doSomethings() {
		return "=============One====IocDemoService doSomethings============";
	}
}
