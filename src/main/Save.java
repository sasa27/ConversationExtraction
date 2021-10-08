package main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import objetsconversations.ConversationSet;
public class Save {
	public int number;
	public File file;
	
	
	public Save(File newFile){
		number=0;
		file=newFile;
		if ((!file.exists()) || (!file.isDirectory())){
			file.mkdir();
		}		
	}
	
	
	
	public void addnombre() {
		this.number++;
	}
	public File getFile() {
		return this.file;
	}
}