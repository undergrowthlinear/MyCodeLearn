import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 
 * @author u1
 *
 */
public class TestString {
	
	@Test
	public void testString(){
		String str="1234/";
		System.out.println(str.endsWith("/"));
	}
	
	@Test
	public void testPattern(){
		String model="-aass,-under※1※-";
		//String paString="^[\\+-!].*※[01]※[\\+-!]$";
		String paString="^[/+-!].*※[01]※[-!]$";
		Pattern pattern = Pattern.compile(paString);
		System.out.println(pattern.matcher(model).matches());
	}

}
