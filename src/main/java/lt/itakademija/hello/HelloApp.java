package lt.itakademija.hello;

public class HelloApp {

	public static void main(String[] args) {
		View view = new SwingView();
		NamesRepository namesRepository = new InMemoryNamesRepository();
		
		HelloEngine engine = new HelloEngine(view, namesRepository);
		engine.start();
	}

}
