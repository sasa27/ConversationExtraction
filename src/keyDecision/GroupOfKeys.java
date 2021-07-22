package keyDecision;
import java.util.ArrayList;
import java.util.Comparator;
public class GroupOfKeys {
	public ArrayList<KeysFound> groupOfKeysFound;
	public GroupOfKeys() {
		groupOfKeysFound=new ArrayList<KeysFound>();
		
	}
	public GroupOfKeys(ArrayList<KeysFound> afterMinimize) {
		groupOfKeysFound=afterMinimize;
		
	}
	public KeysFound getgroupOfKeys(int position) {
		return this.groupOfKeysFound.get(position);
	}

	
}
