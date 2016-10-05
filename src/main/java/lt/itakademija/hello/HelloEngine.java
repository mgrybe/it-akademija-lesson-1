package lt.itakademija.hello;

import java.util.List;

public class HelloEngine {
	
	private final View view;
	
	private final NamesRepository namesRepository;
	
	public HelloEngine(final View view, final NamesRepository namesRepository) {
		this.view = view;
		this.namesRepository = namesRepository;
	}

	public void start() {
		if (view instanceof Initializable) {
			((Initializable) view).init();
		}
		
		List<String> names = namesRepository.getNames();
		for (String name: names) {
			view.showName(name);
		}
		
		if (view instanceof Destroyable) {
			((Destroyable) view).destroy();
		}
	}

}
