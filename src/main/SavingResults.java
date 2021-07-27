package main;

import java.io.*;
import java.util.*;

import objetsconversations.ConversationSet;

public class SavingResults {
	public static void writeArrayListInFile(ArrayList<Set<ArrayList<String>>> menuArray, File dir){
		try {
			ArrayList<Set<String>> newSet= new ArrayList<Set<String>>();
			for(Set<ArrayList<String>> separationOfConvSet : menuArray)
				for (ArrayList<String> fromAssignmentToKey : separationOfConvSet) {
					Set<String> newSetString= new HashSet<String>();
					for (String getOutAssignment : fromAssignmentToKey) {
						String[] parts = getOutAssignment.split("=");
						newSetString.add(parts[0]);
					}
					newSet.add(newSetString);
				}
		    FileOutputStream fos = new FileOutputStream(dir);
		    ObjectOutputStream oos = new ObjectOutputStream(fos);   
		    oos.writeObject(newSet); // write MenuArray to ObjectOutputStream
		    oos.close();
		} catch(Exception ex) {
		    ex.printStackTrace();
		}
	}
	
	
	public static ArrayList<Set<String>> loadArrayListInFile(File file){
		try {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		ArrayList<Set<String>> clubs = (ArrayList<Set<String>>) ois.readObject();
		ois.close();
		return clubs;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public static void writeUnauthorizedArrayListInFile(ConversationSet convSet, ArrayList<Set<String>> keysOfTheSet, File dir){
		try {
			Set<String> newSetString= ConversationSet.getAllAssignments(convSet, keysOfTheSet);
		    FileOutputStream fos = new FileOutputStream(dir);
		    ObjectOutputStream oos = new ObjectOutputStream(fos);   
		    oos.writeObject(newSetString); // write MenuArray to ObjectOutputStream
		    oos.close(); 
		    System.out.println(newSetString);
		} catch(Exception ex) {
		    ex.printStackTrace();
		}
	}
	
	
	public static Set<String> loadUnauthorizedArrayListInFile(File file){
		try {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Set<String> clubs = (Set<String>) ois.readObject();
		System.out.println(clubs);
		ois.close();
		return clubs;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
}
