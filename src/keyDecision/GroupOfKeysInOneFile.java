package keyDecision;
import java.util.ArrayList;
import java.util.Comparator;
public class GroupOfKeysInOneFile {
	public ArrayList<KeysFound> groupOfKeysFound;
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
