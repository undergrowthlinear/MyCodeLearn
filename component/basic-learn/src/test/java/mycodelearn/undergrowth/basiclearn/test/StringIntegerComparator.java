package mycodelearn.undergrowth.basiclearn.test;

import java.util.Comparator;

@SuppressWarnings("hiding")
public class StringIntegerComparator<String> implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		// TODO Auto-generated method stub
		return Integer.valueOf((java.lang.String) o1)-Integer.valueOf((java.lang.String) o2);
	}

}
