package main;

import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;

import keyDecision.MainDecision;
//import split.*;
/**
 * 
 * @author Blot Elliott
 */

public class Main {

	public static String log;
	public static String regex;
	public static String output;
	static boolean timerMode;
	public static String mode;
	public static boolean premier;
	

	public static void main(String[] args) {
		System.out.println("la");
		boolean useRegex = Arrays.stream(args).anyMatch("-r"::equals);
		System.out.println("pb1");
		try {
		    FullOptions.setOptions(args, useRegex);
		} catch (Exception e) {
		    System.err.println("pb option");
		    System.exit(3);
		}
		
		System.out.println("pb");
		boolean firstResult = Arrays.stream(args).anyMatch("-f"::equals);
		//Split the log and detect the sessions and  dependencies
		int compteur= 1;
		File folder = new File("FileCutted/");
		for (File fileEntry : folder.listFiles()) {
			System.out.println( fileEntry);
		}
		
		for (File fileEntry : folder.listFiles()) {
			File tmp = new File("RESULTS/"+output + compteur);
			if ((!tmp.exists()) || (!tmp.isDirectory())){
				tmp.mkdir();
			}
			
			System.out.println(tmp);
			Save count=new Save(tmp);
			if(useRegex){
				String[] argsSplit = {"-r", regex, "-o", tmp.getName(), mode};
				split.MainSplit.main(argsSplit, count, firstResult, useRegex,fileEntry);
			}
			else {
				String[] argsSplit = {log, "-o",  tmp.getName(), mode};
				split.MainSplit.main(argsSplit, count, firstResult, useRegex,fileEntry);
			}
			final long timebuildingTraces1 = System.currentTimeMillis();
			compteur++;
		}
		MainDecision.main();
		
		
		
		//pour couper des fichiers
		
		//coupeurfichiertest.coupeurfichier(new File(log));

		

		return;
	}

	/**
	 *Create the directory that will contain the results
	 **/
	private static String createDir(String tmp) {
		String tmpName = null, fName = "RESULTS/" + tmp;
		int i = 1;
		File x = new File(fName);
		while(x.exists()) {
			tmpName = fName+i;
			x = new File(tmpName);
			i++;
		}
		if (tmpName != null) {
			fName = tmpName;
		}
		output = fName;
		x = new File(fName);
		x.mkdirs();
		return fName;
	}

}
