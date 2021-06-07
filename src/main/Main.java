package main;

import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
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
		boolean reg = true;
		if (Arrays.stream(args).anyMatch("-r"::equals)) {
			
			try {
				FullOptions.setOptions(args);
			} catch (Exception e) {
				System.err.println("pb option");
				System.exit(3);
			}
		}
		else {
			try {
				FullOptions.setOptionsNoRegex(args);
				reg=false;
			} catch (Exception e) {
				System.err.println("pb option");
				System.exit(3);
			}
		}
		
		
		createDir();
		String tmp = output ;
		boolean prem=false;
		if (Arrays.stream(args).anyMatch("-f"::equals)) {
			prem=true;
			System.out.println("match");
		}
		//Split the log and detect the sessions and  dependencies
		sauvegarde compt=new sauvegarde(new File(tmp));
		if(reg){
			String[] argsSplit = {"-i", log, "-r", regex, "-o", tmp, mode};
			split.MainSplit.main(argsSplit, compt, prem);
		}
		else {
			String[] argsSplit = {"-i", log, "-o", tmp, mode};
			split.MainSplit.main(argsSplit, compt, prem);
		}
		final long timebuildingTraces1 = System.currentTimeMillis();
		//coupeurfichiertest.coupeurfichier(new File(log));
		
		
		/*for (int i=0; i<20; i++) {
			
			log="logentrainementlog/log6/trace" + i +".txt";
			String[] argsSplit = {"-i", log, "-r", regex, "-o", tmp, mode};
			split.MainSplit.main(argsSplit, compt);
			
			compt.retourglobal();
			compt.traceglobal();
			compt.compteurtotal=new ArrayList<ArrayList<String>>();
			compte.addnombre();
			compte.setnbConv(0);
		}*/
		
		final long timebuildingTraces2 = System.currentTimeMillis();
		System.out.println("oui");
		System.out.println(timebuildingTraces2-timebuildingTraces1);
		System.out.println("oui");
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
