package keyDecision;
import java.util.ArrayList;
import java.util.Comparator;
public class GroupOfKeysInOneFile {
	public ArrayList<KeysFound> groupOfKeysFound;
	/**
	* Represent a group of every keys found in one of the files
	*/
	public GroupOfKeysInOneFile() {
		groupOfKeysFound=new ArrayList<KeysFound>();
		
	}
	public GroupOfKeysInOneFile(ArrayList<KeysFound> afterMinimize) {
		groupOfKeysFound=afterMinimize;
		
	}
	public GroupOfKeysInOneFile(GroupOfKeysInOneFile afterMinimize) {
		groupOfKeysFound=afterMinimize.groupOfKeysFound;
		
	}
	public KeysFound getgroupOfKeys(int position) {
		return this.groupOfKeysFound.get(position);
	}

	
}
