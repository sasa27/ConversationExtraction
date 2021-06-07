package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;

import com.google.common.io.Files;

public class coupeurfichiertest {
	public static void coupeurfichier(File file) {
		for (int i=0;i<20;i++) {
			new File("logentrainementlog/log6/trace"+i+".txt");
		}
		try {
			BufferedReader br = new BufferedReader (new FileReader(file));
			String line = br.readLine()+"\n";
			
			for (int j=0; j<20;j++) {
				FileWriter fw = new FileWriter("logentrainementlog/log6/trace"+j+".txt",true);
				for (int k=0;k<22;k++) {
					fw.write(line);
					line = br.readLine()+"\n";
				}
				fw.close();
			}
		}
		 catch (IOException ea) {
			System.err.println("IOException");
			ea.printStackTrace();
		}
	}
}