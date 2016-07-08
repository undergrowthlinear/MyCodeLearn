package mycodelearn.undergrowth.basiclearn.kryo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.junit.Test;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import mycodelearn.undergrowth.basiclearn.javassist.JavassistClass;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年7月6日
 * @version 1.0.0
 */
public class KryoTest {

	@Test
	public void testKyro() throws FileNotFoundException {
		Kryo kryo = new Kryo();
		// ...
		Output output = new Output(new FileOutputStream("file.bin"));
		JavassistClass someObject = new JavassistClass();
		kryo.writeObject(output, someObject);
		output.close();
		// ...
		Input input = new Input(new FileInputStream("file.bin"));
		someObject = kryo.readObject(input, JavassistClass.class);
		someObject.execute();
		input.close();
	}

}
