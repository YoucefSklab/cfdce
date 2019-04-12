package com.cfdce.App;

import java.io.IOException;



public class RunJade {
	
	public static String jadePathOld = "/Users/sklab/git/ocfa/bin/jade.jar";
	public static String jadePath = "/Users/sklab/GamaCloudWorkigDirectory/EclipseOxy3A/GamaFrom09-18/cfdce/jade.jar";
	//public static String jadePath = "/Users/djo/git/cfdce/jade.jar";

	public RunJade() {
		
	}
	
	
	public static void main(String args[]) throws InterruptedException{
		try {
		     String[] command = { "java", "-cp", jadePath, "jade.Boot", "-gui" };
		     Process proc = new ProcessBuilder(command).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/**
		PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
		writer.println("The first line");
		writer.println("The second line");
		writer.close();
		*/
		System.out.println("- Main Agent started!");
		java.awt.Toolkit.getDefaultToolkit().beep();
	
	}
	

}

