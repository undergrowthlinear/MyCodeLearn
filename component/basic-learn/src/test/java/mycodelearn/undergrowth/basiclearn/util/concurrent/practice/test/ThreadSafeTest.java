package mycodelearn.undergrowth.basiclearn.util.concurrent.practice.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(value={ElementType.TYPE})
public @interface ThreadSafeTest {

}
