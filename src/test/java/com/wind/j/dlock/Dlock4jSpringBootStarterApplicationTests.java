package com.wind.j.dlock;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wind.j.dlock.test.UserService;
import com.wind.j.dlock.util.DlockUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Dlock4jSpringBootStarterApplicationTests.class)
@SpringBootApplication
//@ComponentScan(basePackages = { "com.wind.j.dlock.test.*" })
public class Dlock4jSpringBootStarterApplicationTests {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(Dlock4jSpringBootStarterApplicationTests.class, args);
	}

//	@Test
	void contextLoads() {
	}

	@Test
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

	@Test
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

	@Test
	public void utilTest() throws InterruptedException {
		int count = 2;
		CountDownLatch cdl = new CountDownLatch(count);
		for (int i = 0; i < count; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					boolean getLocked = false;
					String key = "gooooo";
					try {
						if (getLocked = DlockUtil.lock(key, 1000L, 1000L)) {
							Thread.sleep(2000);
						} else {
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
