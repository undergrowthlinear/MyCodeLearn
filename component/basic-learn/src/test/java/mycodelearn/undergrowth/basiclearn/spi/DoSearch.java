package mycodelearn.undergrowth.basiclearn.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class DoSearch {
	public static void main(String[] args) {
		ServiceLoader<Search> sl = ServiceLoader.load(Search.class);
		Iterator<Search> s = sl.iterator();
		for (Search search : sl) {
			search.search();
		}
	}
}
