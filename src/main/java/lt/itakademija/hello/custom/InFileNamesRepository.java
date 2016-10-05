package lt.itakademija.hello.custom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import lt.itakademija.hello.NamesRepository;

public class InFileNamesRepository implements NamesRepository {
	
	private final File file;
	
	public InFileNamesRepository(final File file) {
		this.file = file;
	}
	
	@Override
	public List<String> getNames() {
		List<String> names = new LinkedList<>();
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				names.add(line);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return names;
	}

}
