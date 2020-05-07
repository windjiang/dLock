package com.wind.j.dlock.test;

import org.springframework.stereotype.Service;

import com.wind.j.dlock.annotation.Dlock4j;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Dlock4j
	public void simple1Test(String name) {
		log.info("simple1Test name is {}", name);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Dlock4j(keys = { "my", "test" }, retryTimeout = 3000)
	public void simple2Test(String name) {
		log.info("simple2Test name is {}", name);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Dlock4j(retryTimeout = 2500)
	public void timeout1Test(String name) {
		log.info("timeout1Test name is {}，timeout 5 secend", name);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
