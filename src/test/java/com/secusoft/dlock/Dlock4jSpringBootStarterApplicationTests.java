package com.secusoft.dlock;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.secusoft.dlock.test.UserService;
import com.wind.j.dlock.util.DlockUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Dlock4jSpringBootStarterApplicationTests.class)
@SpringBootApplication
@ComponentScan(basePackages = {"com.secusoft.dlock.test.*"})
public class Dlock4jSpringBootStarterApplicationTests {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(Dlock4jSpringBootStarterApplicationTests.class, args);
	}
//	@Test
	void contextLoads() {
	}

//	@Test
	public void simple1Test() throws InterruptedException {
		int count = 3;
		CountDownLatch cdl = new CountDownLatch(count);
		for (int i = 0; i < count; i++) {
			final int j = i;
			new Thread(new Runnable() {

				@Override
				public void run() {
					userService.simple1Test(j + "");
					cdl.countDown();
				}
			}).start();
		}
		cdl.await();
	}

//	@Test
	public void simple2Test() throws InterruptedException {
		int count = 3;
		CountDownLatch cdl = new CountDownLatch(count);
		for (int i = 0; i < count; i++) {
			final int j = i;
			new Thread(new Runnable() {

				@Override
				public void run() {
					userService.simple2Test(j + "");
					cdl.countDown();
				}
			}).start();
		}
		cdl.await();
	}

	@Test
	public void timeout1Test() throws InterruptedException {
		int count = 3;
		CountDownLatch cdl = new CountDownLatch(count);
		for (int i = 0; i < count; i++) {
			final int j = i;
			new Thread(new Runnable() {

				@Override
				public void run() {
					userService.timeout1Test(j + "");

					cdl.countDown();
				}
			}).start();
		}
		cdl.await();
	}

//	@Test
	public void timeout2Test() throws InterruptedException {
		int count = 2;
		CountDownLatch cdl = new CountDownLatch(count);
		for (int i = 0; i < count; i++) {
			final int j = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					boolean getLocked = false;
					String key = "gooooo";
//					log.info(j + " ready");
					try {
						if (getLocked = DlockUtil.lock(key, 30000L, 30000L)) {
//							log.info(j + " start");
							Thread.sleep(20000);
						} else {
//							log.info(j + " start failed");
						}

					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						if (getLocked) {
							DlockUtil.unLock(key);
						}
					}
					cdl.countDown();

				}
			}).start();
		}
		cdl.await();
	}

}
