package lastExtraction;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import objetsconversations.Event;
import split.Regex;
import split.Trace;

public class ExtractionWithKeys {
	public static void extractionWithAllPossibleKeys(Trace trace1, HashSet<Set<String>> keySet) {
		File log = new File("examples/ex1id/case1");
		Regex regex = new Regex("examples/ex1id/regexcaseid");
		Trace trace=new Trace(log, regex);
		FinalConversationSetReturn convSetFinal= new FinalConversationSetReturn();
		System.out.println(trace.getSize());
		System.out.println(trace.getSize());
		for(int i=0;i<trace.getSize();i++) {
			Event currentEvent=trace.getEvent(i);
			ArrayList<String> paramsOfAnEvent = new ArrayList<String>(currentEvent.getParamsWithoutFromTo());
			ArrayList<String> paramsOfAnEventWithoutAssignments=new ArrayList<String>();
			ArrayList<String> Assignments=new ArrayList<String>();
			for (String param :  paramsOfAnEvent) {
				if(param.contains("=")) {
					String[] parts = param.split("=");
					paramsOfAnEventWithoutAssignments.add(parts[0]);
					Assignments.add(parts[1].toString());
				}
			}
			int biggest=0;
			Set<String> biggestPowerset=new HashSet<String>();
			for(Set<String> keyPowerset : keySet) {
				if (keyPowerset.size()>biggest) {
					if (paramsOfAnEventWithoutAssignments.containsAll(keyPowerset)) {
						biggestPowerset=keyPowerset;
						biggest=keyPowerset.size();
					}
				}
			}
			boolean newConv=true;
			for(FinalConversationReturn conversation : convSetFinal.getConvSet()) {
				int thisConv=0;
				boolean thisConvVerificationStrings=true;
				for(String key : biggestPowerset) {
					if (conversation.getChoosedKeys().containsKey(key)) {
						thisConv++;
						if(!conversation.getChoosedKeys().get(key).toString().equals(Assignments.get(paramsOfAnEventWithoutAssignments.indexOf(key)))) {
							thisConvVerificationStrings=false;
						}
					}
					
					
				}
				if ((thisConv==biggestPowerset.size()) &&(thisConvVerificationStrings)) {
					newConv=false;
					conversation.addEvent(currentEvent);
				}
			}
			if (newConv) {
				convSetFinal.addConv(new FinalConversationReturn(currentEvent));
			}
			
		}
		System.out.println("auwrite");
		WritingAllConversations.writeLastReturn(convSetFinal);
	}
}

/*
Prioriser le plus gros si il y a
Si plusieurs de même taille, tous les faire
*/