package mylearncode.undergrowth.designmode.decorate.util;

import static org.junit.Assert.*;

import java.awt.im.InputContext;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;

public class CharacterInputStreamDecorateTest {

	@Test
	public void test() {
		int c;
		try {
			String pathname="data.txt";
			InputStream is=new CharacterInputStreamDecorate(new BufferedInputStream(new FileInputStream(new File(CharacterInputStreamDecorateTest.class.getResource(pathname).getFile()))));
			while ((c=is.read())>=0) {
				System.out.print((char)c);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
