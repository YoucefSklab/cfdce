package com.cfdce.Resolver;

import java.util.ArrayList;
import java.util.List;

public class SimAsThreads  {

	public static  List<Thread> threads = new ArrayList<Thread>();
	public SimAsThreads() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nbrThreads = 100;
		/*
		for(int i=0; i<nbrThreads; i++) {
			System.out.println("run the thread : "+i);
			ResolverThread R1 = new ResolverThread("Thread number "+i) ;
			R1.nbrAgents = 3;
			R1.totalPlans = 10;
		    R1.start();
		}
		*/
		int[] tabIndice = { 4, 5,7};
		newThread("th1", 3, 10, tabIndice);
		newThread("th2", 3, 10, tabIndice);
		newThread("th3", 3, 10, tabIndice);
		newThread("th4", 3, 10, tabIndice);
		tabIndice = new int[] {4, 5, 7, 6};
		newThread("th6", 4, 20, tabIndice);
		newThread("th7", 4, 20, tabIndice);
		newThread("th8", 4, 20, tabIndice);
		
	}
	
	
	public static void newThread(String name, int agents, int plans, int[] tabIndice) {
		
		Runnable runnable = new  ResolverThread(name, agents, plans, tabIndice);
		Thread thread = new Thread(runnable);
		thread.setName(String.valueOf(name));
		thread.start();
			
		threads.add(thread);
	
	}

	

}
