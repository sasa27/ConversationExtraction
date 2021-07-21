package keyDecision;
import java.util.ArrayList;
public class GroupOfKeys {
	public ArrayList<KeysFound> groupOfKeysFound;
	public GroupOfKeys() {
		groupOfKeysFound=new ArrayList<KeysFound>();
		
	}
	public KeysFound getgroupOfKeys(int position) {
		return this.groupOfKeysFound.get(position);
	}
}
