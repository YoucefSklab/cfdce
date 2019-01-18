package com.cfdce.Agents;

import jade.core.Agent;

public class MyAgent extends Agent{

	

	protected void setup() {
		System.out.println("Salut je suis l'acheteur!");
		System.out.println("My Name is "+this.getAID().getName());System.out.println("Je me pr�pare .....");
	}

	/*
	protected void beforeMove() {
		System.out.println("Avant de migrer vers une nouvelle location .....");
	}

	protected void afterMove() {
		System.out.println("Je viens d'arriver � une nouvelle location .....");
	}

	protected void takeDown() {
		System.out.println("avant de mourir .....");
	}
	*/
	
}