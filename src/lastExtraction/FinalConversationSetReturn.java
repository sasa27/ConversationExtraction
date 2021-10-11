package lastExtraction;

import java.util.ArrayList;


public class FinalConversationSetReturn{
	private ArrayList<FinalConversationReturn> ConvSet;

	public FinalConversationSetReturn() {
		ConvSet=new ArrayList<FinalConversationReturn>();
	}

	public ArrayList<FinalConversationReturn> getConvSet() {
		return ConvSet;
	}

	public void setConvSet(ArrayList<FinalConversationReturn> convSet) {
		ConvSet = convSet;
	}
	
	public void addConv(FinalConversationReturn conv) {
		ConvSet.add(conv);
	}
	public int getSize() {
		return ConvSet.size();
	}
}