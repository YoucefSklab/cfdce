package com.cfdce.Resolver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CollectResults {
	
	public static String filePath = "logs/results/";
	public static String summuryFile = "AllResults.txt";

	public CollectResults() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
	
		
		
		
		File folder = new File(filePath);
		File[] listOfFiles = folder.listFiles();
		
		System.out.println("Collecting all results of "+listOfFiles.length +  " files in the folder");
		String title = readTitle(filePath+listOfFiles[0].getName());
		
		resetFile(filePath+summuryFile);
		writeToFile(filePath+summuryFile, title); 
	
		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
			 readFile(filePath+listOfFiles[i].getName(), filePath+summuryFile);
			 
		    System.out.println("File " + listOfFiles[i].getName());
		  } else if (listOfFiles[i].isDirectory()) {
		    System.out.println("Directory " + listOfFiles[i].getName());
		  }
		}

	}
	
	
	
	
	
	public static  void writeToFile(String fileName, String text) throws IOException {
		FileWriter fStream = new FileWriter(fileName, true);
		PrintWriter out = new PrintWriter(fStream);
		out.println(text);
		out.flush();
		out.close();
		fStream.close();
	}
	
	public static void readFile(String fileName, String toFile) throws FileNotFoundException, IOException {
		int nbrLine = 1;
		String everything = "";
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		     while (line != null) {
		    	if(nbrLine > 1 && line.contains(",")) {
		    		sb.append(line);
			        sb.append(System.lineSeparator());
			   	}
		    	line = br.readLine();
		    	nbrLine++;
	        }
		    everything = sb.toString();
	    
		    writeToFile(toFile, everything); 
		}
		
		
		
		
		System.out.println(everything);
	}
	
	
public static String readTitle(String fileName) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName)) ;
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		return line;	
	}


public static  void resetFile(String fileName) throws FileNotFoundException {
	PrintWriter pw = new PrintWriter(fileName);
	pw.close();
}
	
	
	
	

}



