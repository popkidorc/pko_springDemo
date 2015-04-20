package pko.demo.springTransaction;

import java.util.List;

public interface FooService {
	Foo getFoo(String fooName);

	Foo getFoo(String fooName, String barName);

	void insertFoo(List<Foo> foo);

	void updateFoo(Foo foo);
}
