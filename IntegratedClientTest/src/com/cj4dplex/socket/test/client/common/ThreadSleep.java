/**
 * Create Time: 2021-06-01 12:29:56:210
 * Made by: Tristan
 */
package com.cj4dplex.socket.test.client.common;

public class ThreadSleep {

	public static void sleep(final long waitTime) {
		try {
			Thread.sleep(waitTime);
			Thread.interrupted();
			System.exit(0);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
