package mycodelearn.undergrowth.netty5;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import org.junit.Test;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* @author <a href="zhangwu@wxchina.com">Wu.Zhang</a>
* Date 2016年6月16日
* @version 1.0.0
 */
public class BeeperControl {
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS");
	
	@Test
	public void beepForAnHour() {
		final Runnable beeper = new Runnable() {
			public void run() {
				System.out.println("beep,currentTimeMillis："+format.format(new Date()));
			}
		};
		final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 10, 10, SECONDS);
		scheduler.schedule(new Runnable() {
			public void run() {
				beeperHandle.cancel(true);
			}
		}, 60 * 60, SECONDS);
		while(true);
	}

	
}
