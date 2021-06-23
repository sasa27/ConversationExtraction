package main;

import java.io.File;
import java.util.Arrays;

import cutter.CutAFileInFilesOfLengthThatYouWant;
public class MainFileCutting {
	
	

	public static String log;
	public static String regex;
	public static String output;
	static boolean timerMode;
	public static String mode;
	public static boolean premier;
	


	public static void main(String[] Options) {
		for(String i : Options) {
			System.out.println(i);
		}
		File fileToWrite=new File(Options[0]);
		CutAFileInFilesOfLengthThatYouWant.cutAFile(fileToWrite, Options[1],Options[2]);
	}
}
