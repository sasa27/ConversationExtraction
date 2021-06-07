package objetsconversations;

import java.util.ArrayList;

public class KeysPowersets {
	public ArrayList<ArrayList<String>> CreatePowersets(ArrayList<String> ListeParam){
		ArrayList<ArrayList<String>> ListePowerset= new ArrayList<ArrayList<String>>();
		ListePowerset=CreatePowersets(ListeParam);
		for (ArrayList<String> powersets : ListePowerset) {
			for (String param : ListeParam) {
				return ListePowerset;
			}
		}
		return ListePowerset;
	}
}
