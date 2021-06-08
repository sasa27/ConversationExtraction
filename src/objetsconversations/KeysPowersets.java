package objetsconversations;

import java.util.ArrayList;

public class KeysPowersets {
	public ArrayList<ArrayList<String>> CreatePowersets(ArrayList<String> listOfParameters){
		ArrayList<ArrayList<String>> listPowerset= new ArrayList<ArrayList<String>>();
		listPowerset=CreatePowersets(listOfParameters);
		for (ArrayList<String> powersets : listPowerset) {
			for (String param : listOfParameters) {
				return listPowerset;
			}
		}
		return listPowerset;
	}
}
