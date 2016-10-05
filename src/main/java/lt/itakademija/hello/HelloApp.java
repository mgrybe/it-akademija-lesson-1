package lt.itakademija.hello;

public class HelloApp {

	public static void main(String[] args) {
		HelloEngine engine = new HelloEngine(new SwingView(), new InMemoryNamesRepository());
		engine.start();
	}

}
