package com.cfdce.generator;

import java.util.concurrent.ThreadLocalRandom;

public class randomValues {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for(int i=0; i<1000; i++){
			System.out.println(ThreadLocalRandom.current().nextInt(30, 100));
		}
	}

}
