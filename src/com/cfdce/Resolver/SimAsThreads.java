package com.cfdce.Resolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SimAsThreads  {

	public static  List<Thread> threads = new ArrayList<Thread>();
	public SimAsThreads() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nbrThreads = 10;
		
		for(int i=0; i<nbrThreads; i++) {
			System.out.println("run the thread : "+i);
			int[] indiceTab = new int[ThreadLocalRandom.current().nextInt(6, 11)];
			Arrays.fill(indiceTab,1);
		    newThread("Thread_"+i, 10, 10, indiceTab);
		}
		
		
		/*
		int[] tabIndice = { 5, 1, 5, 1, 5};
		newThread("th1", 5, 10, tabIndice);
		
		tabIndice =  new int[]  { 1, 5, 1, 5, 1, 5};
		newThread("th2", 6, 10, tabIndice);
		
		//tabIndice =  new int[]  { 5, 1, 5, 1, 5, 1};
		//newThread("th3", 6, 10, tabIndice);
	
		
		tabIndice =  new int[]  { 1, 5, 1, 5, 1, 5, 1};
		newThread("th4", 7, 10, tabIndice);
		
		//tabIndice =  new int[]  { 5, 1, 5, 1, 5, 1, 5};
		//newThread("th6", 7, 10, tabIndice);
		
		tabIndice =  new int[]  { 1, 5, 1, 5, 1, 5, 1, 5};
		newThread("th7", 8, 10, tabIndice);
		*/
		//tabIndice =  new int[]  { 5, 1, 5, 1, 5, 1, 5, 1};
		//newThread("th8", 8, 10, tabIndice);
		/*
		tabIndice =  new int[]  { 1, 5, 1, 5, 1, 5, 1, 5, 1};
		newThread("th7", 9, 10, tabIndice);
		
		tabIndice =  new int[]  { 5, 1, 5, 1, 5, 1, 5, 1, 5};
		newThread("th8", 9, 10, tabIndice);
		
		tabIndice =  new int[]  { 1, 5, 1, 5, 1, 5, 1, 5, 1, 5};
		newThread("th7", 10, 10, tabIndice);
		
		tabIndice =  new int[]  { 5, 1, 5, 1, 5, 1, 5, 1, 5, 1};
		newThread("th8", 10, 10, tabIndice);
		*/
		
	}
	
	
	public static void newThread(String name, int agents, int plans, int[] tabIndice) {
		
		Runnable runnable = new  ResolverThread(name, agents, plans, tabIndice);
		Thread thread = new Thread(runnable);
		thread.setName(String.valueOf(name));
		thread.start();
			
		threads.add(thread);
	
	}

	

}
