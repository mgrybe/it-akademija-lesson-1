package lt.itakademija.hello.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public final class Console {

	private final PrintWriter writer;

	private final BufferedReader reader;

	private static final Console instance = new Console();

	private Console() {
		this.writer = new PrintWriter(System.out, true);
		this.reader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void print(String message, Object... arguments) {
		writer.print(String.format(message, arguments));
		writer.flush();
	}
	
	public void println(String message, Object... arguments) {
		writer.println(String.format(message, arguments));
	}

	public String prompt(String message, Object... arguments) {
		print(message, arguments);
		
		return readLine();
	}

	public String readLine() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static Console getInstance() {
		return instance;
	}

}
