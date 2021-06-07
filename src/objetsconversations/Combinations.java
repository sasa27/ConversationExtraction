package objetsconversations;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.Sets;

public class Combinations {


	public static ArrayList<ArrayList<String>> getCombinations(ArrayList<String> params) {
		ArrayList<ArrayList<String>> combinations =new ArrayList<ArrayList<String>>();
	    Set<String> productsSet = new HashSet<String>(params);
	    Set<Set<String>> combinationsSet = Sets.powerSet(productsSet);
	    Iterator<Set<String>> combinationsIterator = combinationsSet.iterator();
	    while (combinationsIterator.hasNext()) {
	        ArrayList<String> productsList = new ArrayList<String>(combinationsIterator.next());
	        combinations.add(productsList);
	    }
	    return combinations;
	}
	
	
	/*
	ArrayList<ArrayList<String>> combinations = getCombinations(paramsSess);
    
    combinations.remove(0);
    ArrayList<String> combinationsString= new ArrayList<String>();
    for (ArrayList<String> combination : combinations) {
    	//System.out.println(combination.toString());
    	combinationsString.add(combination.toString());
    	
    }
    //System.out.println(combinationsString);
    paramsSess=combinationsString;
    */
}