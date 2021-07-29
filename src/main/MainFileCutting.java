package main;

import java.io.File;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;

import cutter.CutAFileInFilesOfLengthThatYouWant;
public class MainFileCutting {
	
	

	public static String log;
	public static String regex;
	public static String output;
	static boolean timerMode;
	public static String mode;
	public static boolean premier;
	


	public static void main(String[] Options) {
		File fileToWrite=new File(Options[0]);
		File f = new File("RESULTS");
		  
        // Check if the specified file
        // Exists or not
        if (f.exists()) {
        	for(File outp : f.listFiles()) {
        		try {
        			FileUtils.deleteDirectory(outp);
        		}
        		catch(Exception e) {
        			System.out.println("la");
        		}
				outp.delete();
			}
        }
        File directoryOutput=new File("FileCutted");
        
		if ((!directoryOutput.exists()) || (!directoryOutput.isDirectory())){
			directoryOutput.mkdir();
		}
		else {
			for(File outp : directoryOutput.listFiles()) {
				outp.delete();
			}
		}
        
		CutAFileInFilesOfLengthThatYouWant.cutAFile(fileToWrite, Options[1],Options[2]);
		Main.main(Options);
	}
}
