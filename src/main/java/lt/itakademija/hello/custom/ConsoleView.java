package lt.itakademija.hello.custom;

import lt.itakademija.hello.View;
import lt.itakademija.hello.util.Console;

public class ConsoleView implements View {

	private final Console console = Console.getInstance();
	
	@Override
	public void showName(final String name) {
		console.print("Name: '%s'", name);
		console.readLine();
	}

}
