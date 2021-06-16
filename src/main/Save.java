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
	public ArrayList<Integer> arrayNumberOfApparitions;
	public Set<String> save;
	public int number;
	public int nbconv;
	public int nbfiles;
	public File file;
	public ArrayList<ArrayList<String>> counter; 
	public boolean flag;
	
	//compteur global pour la sauvegarde
	public Save(File newFile){
		arrayNumberOfApparitions= new ArrayList<Integer>();
		save= new LinkedHashSet<>();
		number=0;
		nbconv=0;
		nbfiles=0;
		file=newFile;
		if ((!file.exists()) || (!file.isDirectory())){
			file.mkdir();
		}		
		counter=new ArrayList<ArrayList<String>>();
		flag=true;
	}
	
	
	//a partir d'ici, sert pour les résultats de tests
	public void addToSave(Set<String> setOfKeys, ArrayList<String> singleKeys, ConversationSet ensemble, ArrayList<ArrayList<String>> theKeys) {
		this.arrayNumberOfApparitions= new ArrayList<Integer>();
		this.save= new LinkedHashSet<>();
		int nb=0;
		for (String raj : setOfKeys) {
			 nb=Collections.frequency(singleKeys, raj) ;
			 if (!this.save.contains(raj)) {
				 this.save.add(raj);
				 this.arrayNumberOfApparitions.add(nb);
			 }
			 else {
				 this.arrayNumberOfApparitions.set(new ArrayList<>(this.save).indexOf(raj), this.arrayNumberOfApparitions.get(new ArrayList<>(this.save).indexOf(raj))+nb);
			 }
		}
		String writingInFile="";
		writingInFile+= "for the convset "+ nbconv+ " we have : ";
		nbconv++;
		for (int i=0;i<this.save.size();i++) {
			writingInFile+=new ArrayList<>(this.save).get(i) + "     " ;
			writingInFile+=this.arrayNumberOfApparitions.get(i) + "     time";
			writingInFile+="\n";
			
		}
		ArrayList<ArrayList<String>> verif = new ArrayList<ArrayList<String>>();
		for(ArrayList<String> addition : theKeys) {
			if (!verif.contains(addition)) {
				writingInFile+=addition.toString() + "      "  + Collections.frequency(theKeys, addition)    +  "  time";
				writingInFile+="\n";
				verif.add(addition);
				this.counter.add(addition);
			}
		}
		for (String total : singleKeys) {
			ArrayList<String> retour = new ArrayList<String>();
			retour.add(total);
			this.counter.add(retour);
		}
		
		writingInFile += "for an heuristic of : "+ensemble.getHeuristique(ensemble) +"\n \n \n \n";
		try {
			FileWriter fw = new FileWriter(file+"/trace"+number+".txt",true);
			fw.write(writingInFile);
			fw.close();
		}
	 catch (IOException ea) {
			System.err.println("IOException");
			ea.printStackTrace();
		}
		
		
		
	return;	
	}

	public void globalSave() {
		String writingInFile ="";
		writingInFile = writingInFile + "for the Trace " + number;
		writingInFile=writingInFile+"\n";
				try {
					FileWriter fw = new FileWriter(this.file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(writingInFile);
					bw.close();
				}
		catch(Exception e) {
			System.err.println(e);
		}
	}
	
	public void retourglobal () {
		String renvoi="";
		Set<ArrayList<String>> SetParam= new LinkedHashSet<>();
		for (ArrayList<String> param1 : this.counter) {
			SetParam.add(param1);
		}
		for (ArrayList<String> param : SetParam) {
			renvoi = renvoi+param + " here " + Collections.frequency(counter, param) + " times";
			renvoi=renvoi+"\n";
		}
				try {
					FileWriter fw = new FileWriter(file+"/global"+number+".txt",true);;
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(renvoi);
					bw.close();
				}
		catch(Exception e) {
			System.err.println(e);
		}
	}
	
	public void traceglobal() {
		String writingInFile="";
		Set<ArrayList<String>> SetParam= new LinkedHashSet<>();
		for (ArrayList<String> param1 : this.counter) {
			SetParam.add(param1);
		}
		for (ArrayList<String> param : SetParam) {
			if (param.toString().contains("[session]")) {
			writingInFile = writingInFile+param + " here " + Collections.frequency(counter, param)+"/"+counter.size() + "times";
			writingInFile=writingInFile+"\n";
			}
		}
				try {
					FileWriter fw = new FileWriter(file+"/S1sess.txt",true);;
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(writingInFile);
					bw.close();
				}
		catch(Exception e) {
			System.err.println(e);
		}
	}
	
	public void addnombre() {
		this.number++;
	}
	public void setnbConv(int i) {
		this.nbconv=i;
	}
	public File getFile() {
		return this.file;
	}
}