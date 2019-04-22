package com.cfdce.mainPkg;

import java.util.concurrent.ThreadLocalRandom;

public class random {

	public random() {
	
	}
		public static void main(String[] args) {
			
			for(int i = 0; i < 10; i++) {
				System.out.println(ThreadLocalRandom.current().nextInt(1000000, 9999999));
			}
			
		}


}
