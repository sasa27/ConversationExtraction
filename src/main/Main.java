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
		
		boolean useRegex = Arrays.stream(args).anyMatch("-r"::equals);
		System.out.println("pb1");
		try {
		    FullOptions.setOptions(args, useRegex);
		} catch (Exception e) {
		    System.err.println("pb option");
		    System.exit(3);
		}
		
		System.out.println("pb");
		createDir();
		String tmp = output ;
		boolean firstResult = Arrays.stream(args).anyMatch("-f"::equals);
		//Split the log and detect the sessions and  dependencies
		Save count=new Save(new File(tmp));
		File folder = new File("FileCutted/");
		for (final File fileEntry : folder.listFiles()) {
			
			if(useRegex){
				String[] argsSplit = {"-r", regex, "-o", tmp, mode};
				split.MainSplit.main(argsSplit, count, firstResult, useRegex,fileEntry);
			}
			else {
				String[] argsSplit = {log, "-o", tmp, mode};
				split.MainSplit.main(argsSplit, count, firstResult, useRegex,fileEntry);
			}
			final long timebuildingTraces1 = System.currentTimeMillis();
		}
		MainDecision.main();
		
		
		//pour couper des fichiers
		
		//coupeurfichiertest.coupeurfichier(new File(log));

		

		return;
	}

	/**
	 *Create the directory that will contain the results
	 **/
	private static String createDir() {
		String tmpName = null, fName = "RESULTS/" + output;
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
