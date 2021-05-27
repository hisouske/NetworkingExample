package com.cj4dplex.test.udpserver;



import com.cj4dplex.test.ucontroller.Controller;

public class UdpServerMain {

	public static void main(String[] args) {

		UdpServer udpServer = new UdpServer(6000);
		Controller controller = new Controller(udpServer);
		
		
		
//		final Object object = new Object();
//		final List<Object> objects = new ArrayList<Object>();
//		for(int i = 0 ; i < 10 ; i++) {
//			objects.add(new Object());
//			if(4 == i) {
//				objects.add(object);
//			}
//		}
//		
//		System.out.println(objects.size());
//		
//		int num = 0;
//		for(Object compObj : objects) {
//			System.out.println(String.format("Compare: %02d - %b", num++, compObj.equals(object)));
//		}
	}

}
