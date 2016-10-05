package lt.itakademija.hello.custom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

import lt.itakademija.hello.NamesRepository;

public class NetworkNamesRepository implements NamesRepository {

	private final String url;

	public NetworkNamesRepository(final String url) {
		this.url = url;
	}

	@Override
	public List<String> getNames() {
		List<String> names = new LinkedList<>();

		try {
			URLConnection urlConnection = new URL(url).openConnection();
			urlConnection.connect();

			try (InputStream is = urlConnection.getInputStream()) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

				String line = null;
				while ((line = reader.readLine()) != null) {
					names.add(line);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return names;
	}

	public static void main(String[] args) {
		for (String name : new NetworkNamesRepository("http://localhost:8081").getNames()) {
			System.out.println(name);
		}
	}

}
