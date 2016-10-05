package lt.itakademija.hello;

import java.util.LinkedList;
import java.util.List;

public class InMemoryNamesRepository implements NamesRepository {

	@Override
	public List<String> getNames() {
		List<String> names = new LinkedList<>();
		
		names.add("Marius Grybė");
		names.add("Ramūnas Alksnys");
		names.add("Marius Bernotas");
		names.add("Marek Butrimas");
		names.add("Robertas Charevičius");
		names.add("Jurga Galinienė");
		names.add("Neli Golovinova");
		names.add("Justinas Grybas");
		names.add("Dainius Kapleris");
		names.add("Greta Kostevič");
		names.add("Vytautas Kremeneckis");
		names.add("Ramunė Krutulienė");
		names.add("Jovita Kulbytė");
		names.add("Jurij Kuznecov");
		names.add("Rokas Markeliūnas");
		names.add("Giedrius Mauza");
		names.add("Pavelas Moisevičius");
		names.add("Mantas Nugaras");
		names.add("Tomas Pauža");
		names.add("Karolis Račas");
		names.add("Kristina Rančytė");
		names.add("Mindaugas Rinkevičius");
		names.add("Vilbertas Soraka");
		names.add("Mantas Tumėnas");
		names.add("Justas Važgėla");
		
		return names;
	}

}
