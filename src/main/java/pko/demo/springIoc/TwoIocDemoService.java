package pko.demo.springIoc;

public class TwoIocDemoService implements IIocDemoService {

	@Override
	public void doSomethings() {
		System.out
				.println("=============Two====IocDemoService doSomethings============");
	}
}
