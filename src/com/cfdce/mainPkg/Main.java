package com.cfdce.mainPkg;

public class Main {

	public static void main(String[] args) {
		
		int iterations = 3;
	
		
		for (int i = 1; i <= 3; i++) {
			
			/*
			
			try {
			//	sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
			MyThread thread = new MyThread("Agent"+i);
			thread.start();
		}
		
		
	}

}
