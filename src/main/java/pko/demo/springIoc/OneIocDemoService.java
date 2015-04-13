package pko.demo.springIoc;

public class OneIocDemoService implements IIocDemoService {

	@Override
	public void doSomethings() {
		System.out
				.println("=============One====IocDemoService doSomethings============");
	}
}
