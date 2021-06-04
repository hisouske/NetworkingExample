/**
 * Create Time: 2021-06-01 12:29:56:210
 * Made by: Tristan
 */
package com.cj4dplex.socket.intergrated.common;

public class ThreadSleep {

	public static void sleep(final long waitTime) {
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
