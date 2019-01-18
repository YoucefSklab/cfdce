package com.cfdce.Plan;

import java.util.concurrent.ThreadLocalRandom;

public class randomAgentList {

	public static void main(String[] args) {
		
		for(int i=1; i<=1000; i++){
			System.out.println(" listAgent.add("+ThreadLocalRandom.current().nextInt(0, 1000)+");");
		}

	}

}
