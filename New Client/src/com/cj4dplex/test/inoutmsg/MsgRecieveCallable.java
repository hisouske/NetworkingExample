package com.cj4dplex.test.inoutmsg;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.*;

public class MsgRecieveCallable implements Callable<String> {

	private BufferedReader bufferedReader = null;
	private boolean flag = true;
	@Override
	public String call() throws Exception {
		String msg = null;
		try {
			while ((msg = bufferedReader.readLine()) != null&&flag) {
				System.out.println(msg);
				return msg;
			}
		} catch (IOException e) {
			
			System.out.println("Server 문제 발생 ");
			flag = false;
			bufferedReader = null;
			bufferedReader.close();
		}
		return msg;
	}

	public MsgRecieveCallable(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}

}