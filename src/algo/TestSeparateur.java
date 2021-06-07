package algo;

import java.util.ArrayList;

public class TestSeparateur {
    public static ArrayList<String> Separation(String line){
        ArrayList<String> liste= new ArrayList<String>();
        final String SEPARATEUR = "\\(";
        String[] mots = line.split(SEPARATEUR);
        String str = mots[1];
		str = str.substring(0, str.length()-1);
        final String DEUXIEMESEPARATION = ",";
        String[] parametres = str.split(DEUXIEMESEPARATION);
        for (int i = 0; i < parametres.length; i++) {
            liste.add(parametres[i]);
        }
        return liste;
    }
    public static String nomevent(String line){
        ArrayList<String> liste= new ArrayList<String>();
        final String SEPARATEUR = "\\(";
        String[] mots = line.split(SEPARATEUR);
        String str = mots[0];
        return str;
    }
}
