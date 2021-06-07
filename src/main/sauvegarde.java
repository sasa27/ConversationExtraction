package main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import objetsconversations.ConversationSet;
public class sauvegarde {
	public ArrayList<Integer> Compteur;
	public Set<String> Sauv;
	public int nombre;
	public int nbconv;
	public int nbfichier;
	public File file;
	public ArrayList<ArrayList<String>> compteurtotal; 
	public boolean flag;
	
	
	public sauvegarde(File newFile){
		Compteur= new ArrayList<Integer>();
		Sauv= new LinkedHashSet<>();
		nombre=0;
		nbconv=0;
		nbfichier=0;
		file=newFile;
		if ((!file.exists()) || (!file.isDirectory())){
			file.mkdir();
		}		
		compteurtotal=new ArrayList<ArrayList<String>>();
		flag=true;
	}
	
	public void addtoSauv(Set<String> leset, ArrayList<String> larray, ConversationSet ensemble, ArrayList<ArrayList<String>> plusieurscles) {
		this.Compteur= new ArrayList<Integer>();
		this.Sauv= new LinkedHashSet<>();
		int nb=0;
		for (String raj : leset) {
			 nb=Collections.frequency(larray, raj) ;
			 if (!this.Sauv.contains(raj)) {
				 this.Sauv.add(raj);
				 this.Compteur.add(nb);
			 }
			 else {
				 this.Compteur.set(new ArrayList<>(this.Sauv).indexOf(raj), this.Compteur.get(new ArrayList<>(this.Sauv).indexOf(raj))+nb);
			 }
		}
		String renvoi="";
		renvoi+= "pour le convset numéro"+ nbconv+ " nous avons : ";
		nbconv++;
		for (int i=0;i<this.Sauv.size();i++) {
			renvoi+=new ArrayList<>(this.Sauv).get(i) + "     " ;
			renvoi+=this.Compteur.get(i) + "     fois";
			renvoi+="\n";
			
		}
		ArrayList<ArrayList<String>> verif = new ArrayList<ArrayList<String>>();
		for(ArrayList<String> rajoutplus1 : plusieurscles) {
			if (!verif.contains(rajoutplus1)) {
				renvoi+=rajoutplus1.toString() + "      "  + Collections.frequency(plusieurscles, rajoutplus1)    +  "  fois";
				renvoi+="\n";
				verif.add(rajoutplus1);
				this.compteurtotal.add(rajoutplus1);
			}
		}
		for (String total : larray) {
			ArrayList<String> retour = new ArrayList<String>();
			retour.add(total);
			this.compteurtotal.add(retour);
		}
		
		renvoi += "Le tout pour une heuristique de : "+ensemble.getHeuristique(ensemble) +"\n \n \n \n";
		try {
			FileWriter fw = new FileWriter(file+"/trace"+nombre+".txt",true);
			fw.write(renvoi);
			fw.close();
		}
	 catch (IOException ea) {
			System.err.println("IOException");
			ea.printStackTrace();
		}
		
		
		
	return;	
	}

	public void SauvegardeGlobal() {
		String renvoi ="";
		renvoi = renvoi + "Pour la Trace " + nombre;
		renvoi=renvoi+"\n";
				try {
					FileWriter fw = new FileWriter(this.file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(renvoi);
					bw.close();
				}
		catch(Exception e) {
			System.err.println(e);
		}
	}
	
	public void retourglobal () {
		String renvoi="";
		Set<ArrayList<String>> SetParam= new LinkedHashSet<>();
		for (ArrayList<String> param1 : this.compteurtotal) {
			SetParam.add(param1);
		}
		for (ArrayList<String> param : SetParam) {
			renvoi = renvoi+param + " present " + Collections.frequency(compteurtotal, param);
			renvoi=renvoi+"\n";
		}
				try {
					FileWriter fw = new FileWriter(file+"/globalite"+nombre+".txt",true);;
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(renvoi);
					bw.close();
				}
		catch(Exception e) {
			System.err.println(e);
		}
	}
	
	public void traceglobal() {
		String renvoi="";
		Set<ArrayList<String>> SetParam= new LinkedHashSet<>();
		for (ArrayList<String> param1 : this.compteurtotal) {
			SetParam.add(param1);
		}
		for (ArrayList<String> param : SetParam) {
			if (param.toString().contains("[session]")) {
			renvoi = renvoi+param + " present " + Collections.frequency(compteurtotal, param)+"/"+compteurtotal.size();
			renvoi=renvoi+"\n";
			}
		}
				try {
					FileWriter fw = new FileWriter(file+"/S1sess.txt",true);;
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(renvoi);
					bw.close();
				}
		catch(Exception e) {
			System.err.println(e);
		}
	}
	
	public void addnombre() {
		this.nombre++;
	}
	public void setnbConv(int i) {
		this.nbconv=i;
	}
	public File getFile() {
		return this.file;
	}
}